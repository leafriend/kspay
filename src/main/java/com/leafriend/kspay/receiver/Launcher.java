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

    private int port = DEFAULT_PORT;

    private boolean isShutdownCommand = false;

    private boolean isHelpCommand = false;

    public static void main(String[] args) {
        Launcher launcher = parse(args);
        if (launcher == null)
            return;

        if (launcher.isHelpCommand) {
            // TODO call help
            return;
        }

        if (launcher.isShutdownCommand) {
            // TODO call shutdown
            return;
        }

        launcher.launch();
    }

    public static Launcher parse(String[] args) {
        Launcher instance = new Launcher();
        if (args.length > 0) {
            try {
                instance.port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                LOGGER.error("Illegal port value: {}", args[0]);
                return null;
            }
        }
        return instance;
    }

    public void launch() {
        Daemon daemon = new Daemon(port);
        new Thread(daemon).start();
    }

}
