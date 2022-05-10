package async;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class AsyncRead implements Runnable {
    Socket socket;
    byte[] bytes = null;
    String message = null;

    public AsyncRead(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            InputStream is = socket.getInputStream();
            bytes = new byte[100];

            while (true) {
                int readByteCount = is.read(bytes);
                message = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);
                System.out.println(message);

                if (socket.isClosed()) {
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
