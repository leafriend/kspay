package com.leafriend.kspay.receiver;

public class ShutdownMessage extends AbstractMessage {

    public ShutdownMessage() {
        byte[] bytes = new byte[] { (byte) 0x1B };
        setBytes(bytes);
    }

}
