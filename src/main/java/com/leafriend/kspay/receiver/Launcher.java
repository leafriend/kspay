package com.leafriend.kspay.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 서버 데몬을 시작하는 실행기 클래스다.
 *
 * @author leafriend
 */
public class Launcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    private static final int DEFAULT_PORT = 29992;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                LOGGER.error("Illegal port value: {}", args[0]);
                return;
            }
        }
        Daemon daemon = new Daemon(port);
        new Thread(daemon).start();
    }

}
