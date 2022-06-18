package pubandsub;

import pubandsub.entity.UserType;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Allocator implements Runnable {
    Socket socket;
    byte[] bytes = null;
    private final String question = "who are you? type [p] when you're publisher, [s] when you're subscriber\n";


    public Allocator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            UserType type = ask();
            if (type.equals(UserType.PUBLISHER)) {
                //TODO: register publisher
            }
            if (type.equals(UserType.SUBSCRIBER)) {
                //TODO: register subscriber
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private UserType ask() throws IOException {
        socket.getOutputStream().write(question.getBytes(StandardCharsets.UTF_8));
        bytes = new byte[100];
        int len = socket.getInputStream().read(bytes);
        String input = new String(bytes, 0, len, StandardCharsets.UTF_8);
        input = input.replace("\n", "");
        if (input.startsWith("s")) {
            return UserType.SUBSCRIBER;
        } else
            return UserType.PUBLISHER;

    }
}

