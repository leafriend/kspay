package com.leafriend.kspay.receiver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Daemon implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Daemon.class);

    private final int port;

    private boolean isRunning;

    private List<MessageHandler> handlers;

    private List<Runnable> shutdownCallback;

    public Daemon(int port) {
        this.port = port;
        handlers = new ArrayList<MessageHandler>();
        shutdownCallback = new ArrayList<Runnable>();
    }

    @Override
    public void run() {
        isRunning = true;
        ServerSocketChannel ssc;
        try {
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(port));
            ssc.configureBlocking(false);
            LOGGER.info("Server is started - port: {}", port);
        } catch (IOException e) {
            LOGGER.error("Failed to start server", e);
            isRunning = false;
            return;
        }

        while (isRunning) {
            try {
                SocketChannel sc = ssc.accept();
                if (sc != null) {
                    Socket client = sc.socket();
                    InputStream input = client.getInputStream();
                    OutputStream output = client.getOutputStream();
                    MessageHandler[] handlers = this.handlers.toArray(new MessageHandler[0]);
                    boolean parseOpCode = isLocalClient(client);
                    Receiver receiver = new Receiver(parseOpCode, this, input, output, handlers);
                    new Thread(receiver).start();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        LOGGER.info("Server is stopped.");
        for (Runnable callback : shutdownCallback) {
            LOGGER.debug("Running shutdown callback: {}", callback);
            new Thread(callback).start();
        }
    }

    private boolean isLocalClient(Socket client) {
        boolean isLocalClient = false;
        SocketAddress remoteSocketAddress = client.getRemoteSocketAddress();
        if (remoteSocketAddress instanceof InetSocketAddress) {
            InetSocketAddress remote = (InetSocketAddress) remoteSocketAddress;
            isLocalClient = "127.0.0.1".equals(remote.getAddress().getHostAddress());
        }
        return isLocalClient;
    }

    public void stop() {
        LOGGER.info("Stopping server...");
        isRunning = false;
    }

    public void setHandlers(List<MessageHandler> handlers) {
        this.handlers = handlers;
    }

    public void addShutdownCallback(Runnable callback) {
        shutdownCallback.add(callback);
    }

}
