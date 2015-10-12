package com.leafriend.kspay.receiver;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;

/**
 * 입력스트림({@link InputStream})에서 바이트 배열을 임의의 길이로 읽는다. 이미 읽어들인 바이트 배열은 서문(
 * {@link #getPrefaceBytes()}, {@link #getPreface()})에 저장하여 필요할 때 꺼내 쓸 수 있다.
 *
 * <p>
 * 바이트 배열을 읽는 기능({@link #readBytes(int)}, {@link #read(int)})은 인스턴스의 상태를
 * 변경(statefull)한다. 또한 이 클래스는 쓰레드에 대한 안정성을 보장하지 않는다(not thread-safety).
 *
 * @author leafriend
 */
public class MessageReader implements Closeable {

    public static final Charset DEFAULT_CHARSET = Charset.forName("KSC5601");

    private final InputStream input;

    private Charset charset;

    private byte[] preface;

    private int cursor;

    /**
     * 주어진 입력스트림에서 바이트 배열을 읽고, 주어진 문자셋의 문자열로 변환하는 인스턴스를 생성한다.
     *
     * @param input
     *            바이트 배열을 읽을 입력스트림; <code>null</code>이 되어서는 안된다.
     * @param charsetName
     *            바이트 배열을 문자열로 변환할 때 사용할 문자셋 이름
     *
     * @throws IllegalArgumentException
     *             input 인수가 <code>null</code>일 경우 발생
     * @throws UnsupportedCharsetException
     *             charsetName 인수가 지원하지 않는 문자셋 이름일 경우 발생
     */
    public MessageReader(InputStream input, String charsetName)
            throws IllegalArgumentException, UnsupportedCharsetException {
        this(input, Charset.forName(charsetName));
    }

    /**
     * 주어진 입력스트림에서 바이트 배열을 읽고, 주어진 문자셋의 문자열로 변환하는 인스턴스를 생성한다.
     *
     * @param input
     *            바이트 배열을 읽을 입력스트림; <code>null</code>이 되어서는 안된다.
     * @param charset
     *            바이트 배열을 문자열로 변환할 때 사용할 문자셋
     *
     * @throws IllegalArgumentException
     *             input 인수가 <code>null</code>일 경우 발생
     */
    public MessageReader(InputStream input, Charset charset) throws IllegalArgumentException {
        if (input == null)
            throw new IllegalArgumentException("Argument input can't be null");
        this.input = input;
        this.charset = charset;
        preface = new byte[2];
        cursor = 0;
    }

    /**
     * 여태까지 읽은 모든 바이트를 포함한 배열을 반환한다.
     *
     * @return 여태까지 읽은 바이트 배열
     */
    public byte[] getPrefaceBytes() {
        return Arrays.copyOf(this.preface, cursor);
    }

    /**
     * 여태까지 읽은 모든 바이트를 변환한 문자열을 반환한다. 변환에 사용하는 문자셋은 생성자에서 지정한 문자열을 사용한다.
     *
     * @return 여태까지 읽은 모든 바이트를 변환한 문자열
     */
    public String getPreface() {
        return new String(getPrefaceBytes(), charset);
    }

    /**
     * 주어진 길이만큼 입력스트림에서 바이트를 읽어 배열로 반환한다. 주어진 길이만큼 읽지 못하면 EOF 예외가 발생한다. 따라서 이
     * 메소드는 반드시 주어질 길이의 배열을 반환하거나 예외를 던지거나 둘 중 하나의 동작만 한다.
     *
     * @param length
     *            읽을 바이트 배열의 길이
     *
     * @return 읽은 바이트 배열
     *
     * @throws IOException
     *             입력스트림에서 바이트를 읽다 예외가 발생한 경우 발생
     * @throws EOFException
     *             주어진 길이만큼 읽지 못한 경우 발생
     */
    public byte[] readBytes(int length) throws IOException, EOFException {
        if (cursor + length > preface.length) {
            int newLength = preface.length;
            do {
                newLength = newLength * 2;
            } while (cursor + length > newLength);
            byte[] newPreface = new byte[newLength];
            System.arraycopy(preface, 0, newPreface, 0, cursor);
            preface = newPreface;
        }
        int readLength = input.read(preface, cursor, length);
        cursor += readLength;
        if (readLength != length)
            throw new EOFException("Failed to read " + length + " byte(s)");

        byte[] readBytes = new byte[length];
        System.arraycopy(preface, cursor - readLength, readBytes, 0, length);
        return readBytes;
    }

    /**
     * 주어진 길이만큼 입력스트림에서 바이트를 읽어 배열로 반환한다. 주어진 길이만큼 읽지 못하면 EOF 예외가 발생한다. 따라서 이
     * 메소드는 반드시 주어질 길이의 배열을 반환하거나 예외를 던지거나 둘 중 하나의 동작만 한다. 변환에 사용하는 문자셋은 생성자에서
     * 지정한 문자열을 사용한다.
     *
     *
     * @param length
     *            읽을 바이트 배열의 길이
     *
     * @return 읽은 바이트 배열
     *
     * @throws IOException
     *             입력스트림에서 바이트를 읽다 예외가 발생한 경우 발생
     * @throws EOFException
     *             주어진 길이만큼 읽지 못한 경우 발생
     */
    public String read(int length) throws IOException, EOFException {
        return new String(readBytes(length), charset);
    }

    /**
     * 입력스트림을 닫는다.
     *
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws IOException {
        input.close();
    }

}
