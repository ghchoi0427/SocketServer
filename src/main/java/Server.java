import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int BUFFER_SIZE = 1024;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void startServer(int port) {
        try (ServerSocket server = new ServerSocket()) {
            InetSocketAddress address = new InetSocketAddress(port);
            server.bind(address);

            print("Initialize complete");
            ExecutorService receiver = Executors.newCachedThreadPool();

            while (true) {
                try {
                    Socket clientSocket = server.accept();
                    print("Client connected IP address =" + clientSocket.getRemoteSocketAddress().toString());
                    receiver.execute(() -> {
                        startConnection(clientSocket);
                    });
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void startConnection(Socket clientSocket) {
        try {
            ExecutorService reader = Executors.newCachedThreadPool();

            reader.execute(() -> {
                try {
                    readMessage(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            while (true) {
                sendMessage(clientSocket, br.readLine());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            print("Client disconnected IP address =" + clientSocket.getRemoteSocketAddress().toString());
        }
    }

    private void readMessage(Socket socket) throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        while (socket.getInputStream().read(bytes, 0, bytes.length) != -1) {
            print(socket.getRemoteSocketAddress() + ":" + new String(bytes));
            bytes = new byte[BUFFER_SIZE];
        }
    }

    private void sendMessage(Socket socket, String message) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = (message + "\n").getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes);
    }

    private static void print(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[hh:mm:ss]");
        System.out.println(simpleDateFormat.format(new Date()) + message);
    }
}