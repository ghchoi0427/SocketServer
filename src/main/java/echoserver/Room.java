package echoserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Room implements Runnable {
    Socket socket;
    byte[] bytes = null;
    String message = null;

    public Room(Socket socket) {
        this.socket = socket;
        System.out.println("remote host: " + socket.getRemoteSocketAddress());
    }

    @Override
    public void run() {
        while (true) {
            try {

                InputStream is = socket.getInputStream();
                bytes = new byte[100];
                int readByteCount = is.read(bytes);
                message = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);

                OutputStream os = socket.getOutputStream();
                //message = new BufferedReader(new InputStreamReader(System.in)).readLine();
                bytes = message.getBytes(StandardCharsets.UTF_8);
                os.write(bytes);

                if (socket.isClosed()) {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
