package pubandsub;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Channel implements Runnable {
    Socket socket;
    byte[] bytes;
    InputStream is;
    Topic topic;
    String message = null;

    public Channel(Socket socket) {
        this.socket = socket;
        topic = Topic.getInstance();
        bytes = new byte[100];
    }

    @Override
    public void run() {
        while (true) {
            int readByteCount = 0;
            try {
                is = socket.getInputStream();
                readByteCount = is.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            message = new String(bytes, 0, readByteCount, StandardCharsets.UTF_8);
            topic.offerMessage(message);

            if (socket.isClosed()) {
                break;
            }
        }
    }
}
