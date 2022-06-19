package multithread;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ReadAndWrite implements Runnable {
    Socket from;
    Socket to;
    byte[] bytes = null;

    public ReadAndWrite(Socket from, Socket to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        try {
            InputStream is = from.getInputStream();
            OutputStream os = to.getOutputStream();

            while (true) {
                if (from.isClosed() || to.isClosed()) {
                    break;
                }
                bytes = new byte[100];
                int readByteCount = is.read(bytes);
                os.write(new String(bytes, 0, readByteCount, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8));

                if (from.isClosed() || to.isClosed()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
