package com.leafriend.kspay.sendingtester;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Sender {

    public static byte send(String host, int port, byte[] bytes) throws IOException {

        Socket socket = new Socket(host, port);

        DataOutputStream out = null;
        DataInputStream in = null;

        try {

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.write(bytes);
            int res = in.read();
            if (res == 0x06) {
                System.out.println("Response: ACK");
            } else {
                System.err.println("Response: 0x" + String.format("%02x", res));
            }

            return (byte) res;

        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException nested) {
                    nested.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException nested) {
                    nested.printStackTrace();
                }
            }
            socket.close();
        }

    }

}
