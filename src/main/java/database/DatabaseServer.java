package database;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseServer {
    public static void main(String[] args) throws IOException {
//        start();
        RequestParser parser = new RequestParser();
        parser.client();

    }

    private static void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        List<Socket> sockets = new ArrayList<>();
        Thread thread;

        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            while (true) {
                System.out.println("waiting for connection");
                socket = serverSocket.accept();
                sockets.add(socket);

                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("connection set");




            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
