package pubandsub;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Mailman implements Runnable {
    Topic topic;
    byte[] bytes;

    public Mailman() {
        topic = Topic.getInstance();
        bytes = new byte[100];
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (topic.getMessageQueue().isEmpty()) {
                continue;
            }

            try {
                bytes = topic.pollMessage().getBytes(StandardCharsets.UTF_8);

                for (Socket s : topic.getSubscribers()) {
                    s.getOutputStream().write(bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
