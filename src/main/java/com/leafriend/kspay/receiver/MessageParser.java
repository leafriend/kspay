package com.leafriend.kspay.receiver;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 바이트 배열을 읽어서 전문으로 해석한다.
 *
 * @author leafriend
 */
public class MessageParser {

    private static final int TYPE_LEN = 2;

    private static final int REST_LEN = 2;

    private static final Message NOT_PARSED = null;

    /**
     * 전문 리더에서 바이트 배열을 읽어서 전문 인스턴스로 해석한다.
     *
     * @param reader
     *            바이트 배열을 읽을 리더
     *
     * @return 바이트 배열에서 해석한 전문 인스턴스; 해석하지 못한 경우 <code>null</code>
     *
     * @throws EOFException
     *             리더에서 바이트 배열을 읽다 예상하지 못한 끝에 이른 경우 발생
     * @throws IOException
     *             리더에서 바이트 배열을 읽다 예외가 발생한 경우 발생
     */
    public static Message parse(MessageReader reader) throws EOFException, IOException {

        Message message;

        message = parseOldMessage(reader);
        if (message != NOT_PARSED)
            return message;

        message = parseNewMessage(reader);
        if (message != NOT_PARSED)
            return message;

        return NOT_PARSED;

    }

    static Message parseOldMessage(MessageReader reader) throws EOFException, IOException {

        String type = new String(reader.read(TYPE_LEN));

        if (Pattern.matches("^[CIF][BD]$", type))
            return OldCreditCardMessage.parse(reader);

        if ("VR".equals(type))
            return OldVirtualAccountMessage.parse(reader);

        return NOT_PARSED;

    }

    static Message parseNewMessage(MessageReader reader) throws EOFException, IOException {

        reader.read(REST_LEN);
        int length;
        try {
            length = Integer.parseInt(reader.getPreface());
        } catch (NumberFormatException e) {
            return NOT_PARSED;
        }

        byte[] body = reader.readBytes(length);
        MessageReader bodyReader = new MessageReader(new ByteArrayInputStream(body), MessageReader.DEFAULT_CHARSET);

        Header header = Header.parse(bodyReader);

        switch (body[0]) {

        case '1':
        case 'I':
            return CreditCardMessage.parse(header, bodyReader);

        case '2':
            return BankTransferMessage.parse(header, bodyReader);

        case '4':
            return PointMessage.parse(header, bodyReader);

        case '5':
            return VirtualAccountMessage.parse(header, bodyReader);
        case '6':
            if (body[1] != '0')
                return VirtualAccountMessage.parse(header, bodyReader);
            break;

        case '7':
            return WorldPassMessage.parse(header, bodyReader);

        }

        return NOT_PARSED;

    }

}
