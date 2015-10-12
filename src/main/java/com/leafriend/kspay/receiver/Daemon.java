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

    private static final int DEFAULT_PORT = 29992;

    private static final Logger LOGGER = LoggerFactory.getLogger(Daemon.class);

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

    private final int port;

    private boolean isRunning;

    private List<MessageHandler> handlers;

    public Daemon(int port) {
        this.port = port;
        handlers = new ArrayList<MessageHandler>();
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

}