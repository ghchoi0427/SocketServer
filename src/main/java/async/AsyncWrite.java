package async;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class AsyncWrite implements Runnable {
    Socket socket;
    byte[] bytes = null;
    String message = null;

    public AsyncWrite(Socket socket) {
        this.socket = socket;
        System.out.println("remote host: " + socket.getRemoteSocketAddress());
    }

    @Override
    public void run() {
        try {
            OutputStream os = socket.getOutputStream();

            while (true) {
                message = new BufferedReader(new InputStreamReader(System.in)).readLine() + "\n";
                bytes = message.getBytes(StandardCharsets.UTF_8);
                os.write(bytes);

                if (socket.isClosed()) {
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
