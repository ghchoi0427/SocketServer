package chatserver;

import server.SocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer implements SocketServer {


    ServerSocket serverSocket = null;
    Socket socket = null;
    List<Socket> sockets = new ArrayList<>();
    Thread t;

    public void start() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            while (true) {
                System.out.println("[연결 기다림]. 현재접속 = " + sockets.size());
                socket = serverSocket.accept();
                sockets.add(socket);

                if (sockets.size() != 2) {
                    continue;
                }

                new Thread(new ChatRoom(sockets.get(0), sockets.get(1))).start();

                //t = new Thread(new ChatRoom(socket));
                //t.start();

            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        if (!serverSocket.isClosed()) {
            try {
                for (Socket s : sockets) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
