package com.leafriend.kspay.receiver;

import static com.leafriend.kspay.receiver.MessageReader.DEFAULT_CHARSET;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 전문을 수신하고 처리한 다음 응답을 송신하는 수신기다. {@link Runnable}을 구현하여 각 요청을 별도의 쓰레드로 처리할 수
 * 있으며, 수신/송신은 각각 입력스트림({@link InputStream})과 출력스트림({@link OutputStream})을 이용한다.
 *
 * <p>
 * 한 번 사용한 수신기는 재사용할 수 없다.
 *
 * @author leafriend
 */
public class Receiver implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    static final byte ACK = 0x06;

    static final byte NAK = 0x15;

    private final boolean parseOpCode;

    private final Daemon daemon;

    private final InputStream input;

    private final OutputStream output;

    private List<MessageHandler> handlers;

    /**
     * 주어진 입/출력스트림으로 수/송신하고, 주어진 처리기로 해석한 전문을 처리하는 인스턴스를 생성한다.
     *
     * @param parseOpCode
     *            운영코드(서버 종료 등)을 해석할지 여부
     * @param daemon
     *            운영코드를 실행항 기동 중인 서버 인스턴스
     * @param input
     *            수신용 입력스트림
     * @param output
     *            송신용 출력스트림
     * @param handlers
     *            전문을 처리할 처리기 배열
     */
    public Receiver(boolean parseOpCode, Daemon daemon, InputStream input, OutputStream output, MessageHandler... handlers) {
        if (input == null)
            throw new IllegalArgumentException("Argument input can't be null");
        if (output == null)
            throw new IllegalArgumentException("Argument output can't be null");
        this.parseOpCode = parseOpCode;
        this.daemon = daemon;
        this.input = input;
        this.output = output;
        this.handlers = Arrays.asList(handlers);
    }

    /**
     * 전문을 수신하고 해석한 다음 처리기로 처리한 결과를 송신한다.
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        int response = ACK;
        try {
            parseAndHandle();
        } catch (Exception e) {
            LOGGER.error("Failed to parse request", e);
            response = NAK;
        } finally {
            respond(response);
        }
    }

    void parseAndHandle() throws EOFException, IOException {
        MessageReader reader = new MessageReader(input, DEFAULT_CHARSET);
        Message message = MessageParser.parse(reader, parseOpCode);
        if (message instanceof ShutdownMessage) {
            daemon.stop();
            return;
        }
        for (MessageHandler handler : handlers) {
            try {
                if (handler.isHandleable(message))
                    handler.handle(message);
            } catch (RuntimeException e) {
                LOGGER.error("Failed to handle", e);
            }
        }
    }

    void respond(int response) {
        try {
            output.write(response);
        } catch (IOException e) {
            LOGGER.error("Failed to respond", e);
        }
    }

}
