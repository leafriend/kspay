package com.leafriend.kspay.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogginMessageHandler implements MessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogginMessageHandler.class);

    @Override
    public boolean isHandleable(Message message) {
        return true;
    }

    @Override
    public void handle(Message message) {
        LOGGER.debug("Handle message: {}", message);
    }

}
