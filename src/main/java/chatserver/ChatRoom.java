package chatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatRoom implements Runnable {
    Socket sck1;

    Socket sck2;
    byte[] bytes1 = null;
    byte[] bytes2 = null;
    String message1 = null;
    String message2 = null;

    public ChatRoom(Socket sck1, Socket sck2) {
        this.sck1 = sck1;
        this.sck2 = sck2;
    }

    @Override
    public void run() {
        while (true) {
            try {

                InputStream is1 = sck1.getInputStream();
                InputStream is2 = sck2.getInputStream();

                bytes1 = bytes2 = new byte[100];

                int readByteCount1 = is1.read(bytes1);
                message1 = new String(bytes1, 0, readByteCount1, StandardCharsets.UTF_8);

                int readByteCount2 = is2.read(bytes2);
                message2 = new String(bytes2, 0, readByteCount2, StandardCharsets.UTF_8);

                System.out.println(message1 + ":" + message2);

                OutputStream os1 = sck1.getOutputStream();
                OutputStream os2 = sck2.getOutputStream();

                bytes1 = message1.getBytes(StandardCharsets.UTF_8);
                bytes2 = message2.getBytes(StandardCharsets.UTF_8);

                os1.write(bytes2);
                os2.write(bytes1);

                if (sck1.isClosed() || sck2.isClosed()) {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
