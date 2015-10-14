package com.leafriend.kspay.receiver;

import java.io.IOException;

public class UnknownMessage extends AbstractMessage {

    public UnknownMessage(MessageReader reader) {
        while (true) {
            try {
                reader.read(1024);
            } catch (IOException ioe) {
                break;
            }
        }
        setBytes(reader.getPrefaceBytes());
    }

}
