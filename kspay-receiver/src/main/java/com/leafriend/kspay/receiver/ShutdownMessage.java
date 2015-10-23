package com.leafriend.kspay.receiver;

public class ShutdownMessage extends AbstractMessage {

    public static final int SHUTDOWN_OPCODE = 0x1B;

    public ShutdownMessage() {
        byte[] bytes = new byte[] { (byte) SHUTDOWN_OPCODE };
        setBytes(bytes);
    }

}
