package com.leafriend.kspay.receiver;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 입력스트림({@link InputStream})에서 바이트 배열을 임의의 길이로 읽는다. 이미 읽어들인 바이트 배열은 서문(
 * {@link #getPreface()})에 저장하여 필요할 때 꺼내 쓸 수 있다.
 *
 * <p>
 * 바이트 배열을 읽는 기능({@link #read(int)})은 인스턴스의 상태를 변경(statefull)한다. 또한 이 클래스는 쓰레드에
 * 대한 안정성을 보장하지 않는다(not thread-safety).
 *
 * @author leafriend
 */
public class MessageReader implements Closeable {

    private final InputStream input;

    private byte[] preface;

    private int cursor;

    /**
     * 주어진 입력스트림에서 바이트 배열을 읽는 인스턴스를 생성한다.
     *
     * @param input
     *            바이트 배열을 읽을 입력스트림; <code>null</code>이 되어서는 안된다.
     *
     * @throws IllegalArgumentException
     *             input 인수가 <code>null</code>일 경우 발생
     */
    public MessageReader(InputStream input) throws IllegalArgumentException {
        if (input == null)
            throw new IllegalArgumentException("Argument input can't be null");
        this.input = input;
        preface = new byte[2];
        cursor = 0;
    }

    /**
     * 여태까지 읽은 모든 바이트를 포함한 배열을 반환한다.
     *
     * @return 여태가지 읽은 바이트 배열
     */
    public byte[] getPreface() {
        return Arrays.copyOf(this.preface, cursor);
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
    public byte[] read(int length) throws IOException, EOFException {
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
     * 입력스트림을 닫는다.
     *
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws IOException {
        input.close();
    }

}
