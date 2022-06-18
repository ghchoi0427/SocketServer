package pubandsub;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PubSubServer {

    ServerSocket serverSocket = null;
    Socket socket = null;
    List<Socket> sockets = new ArrayList<>();


    public PubSubServer() {
    }

    public void start() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            new Thread(new Mailman()).start();

            while (true) {
                System.out.println("[연결 기다림]");
                socket = serverSocket.accept();
                sockets.add(socket);

                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("[연결 수락함] " + isa.getHostName());

                new Thread(new Allocator(socket)).start();
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
