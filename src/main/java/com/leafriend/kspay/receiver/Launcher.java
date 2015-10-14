package com.leafriend.kspay.receiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 서버 데몬을 시작하는 실행기 클래스다.
 *
 * @author leafriend
 */
public class Launcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    private static final String CLASSPATH_PREFIX = "classpath:";

    private static final String DEFAULT_CONFIG_PATH = CLASSPATH_PREFIX + "kspay-receiver.properties";

    private Properties properties = new Properties();

    private boolean isShutdownCommand = false;

    private boolean isHelpCommand = false;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {

            Launcher launcher = parse(args);

            if (launcher.isHelpCommand) {
                // TODO call help
                return;
            }

            if (launcher.isShutdownCommand) {
                // TODO call shutdown
                return;
            }

            launcher.launch();

        } catch (Exception e) {
            LOGGER.error("Failed to launch daemon");
            return;
        }
    }

    public static Launcher parse(String[] args) throws FileNotFoundException, IOException {
        String configPath;
        if (args.length > 0) {
            configPath = args[0];
        } else {
            configPath = DEFAULT_CONFIG_PATH;
        }
        return new Launcher(configPath);
    }

    public Launcher(String configPath) throws FileNotFoundException, IOException {
        InputStream input;
        if (configPath.startsWith(CLASSPATH_PREFIX)) {
            ClassLoader classLoader = Launcher.class.getClassLoader();
            String pathInClassPath = configPath.substring(CLASSPATH_PREFIX.length());
            input = classLoader.getResourceAsStream(pathInClassPath);
            if (input == null)
                throw new FileNotFoundException(configPath);
        } else {
            input = new FileInputStream(new File(configPath));
        }
        properties.load(input);
    }

    public void launch() {
        int port = Integer.parseInt(properties.getProperty("server.port"));
        Daemon daemon = new Daemon(port);
        List<MessageHandler> handlers = loadHandlers();
        daemon.setHandlers(handlers);
        new Thread(daemon).start();
    }

    public List<MessageHandler> loadHandlers() {
        List<MessageHandler> list = new ArrayList<MessageHandler>();
        String[] handerlNames = properties.getProperty("server.handlers", "").split(",");
        for (String handerlName : handerlNames) {
            MessageHandler handler = loadHandler(handerlName);
            if (handler != null)
                list.add(handler);
        }
        return list;
    }

    public MessageHandler loadHandler(String handlerName) {
        handlerName = handlerName.trim();
        try {
            Class<?> handlerClass = Class.forName(handlerName);
            MessageHandler handler = (MessageHandler) handlerClass.newInstance();
            handler.setProperties(properties);
            handler.init();
            return handler;
        } catch (ClassNotFoundException e) {
            LOGGER.warn("Failed to load handler class '" + handlerName + "'", e);
            return null;
        } catch (ClassCastException e) {
            LOGGER.warn("Handler class ''" + handlerName + "' is not a '" + MessageHandler.class.getName() + "", e);
            return null;
        } catch (InstantiationException e) {
            LOGGER.warn("Failed to instantiate handler ''" + handlerName + "'", handlerName);
            return null;
        } catch (IllegalAccessException e) {
            LOGGER.warn("Failed to instantiate handler ''" + handlerName + "'", handlerName);
            return null;
        } catch (Exception e) {
            LOGGER.warn("Failed to instantiate handler ''" + handlerName + "'", handlerName);
            return null;
        }
    }

}
