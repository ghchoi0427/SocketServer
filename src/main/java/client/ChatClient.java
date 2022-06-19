package client;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    public void start() {
        try {
            Socket socket = new Socket("127.0.0.1", 8080); // 소켓 서버에 접속
            System.out.println("socket 서버에 접속 성공!");

            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            writer.println("CLIENT TO SERVER");

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            System.out.println(reader.readLine());
            System.out.println("CLIENT SOCKET CLOSE");
            socket.close(); // 소켓 종료

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

