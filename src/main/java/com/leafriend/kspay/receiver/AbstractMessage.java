package com.leafriend.kspay.receiver;

public class AbstractMessage implements Message {

    private byte[] bytes;

    protected void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public byte[] getBytes() {
        return bytes;
    }

}
