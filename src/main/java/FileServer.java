import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileServer {

    private final int BUFFER_SIZE = 1024;

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

            sendFile(clientSocket, FileHandler.getByteFromResourceFile("pepe.jpg"));
            print("file sent");

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

    private void sendFile(Socket socket, byte[] fileStream) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(fileStream);
    }

    private static void print(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[hh:mm:ss]");
        System.out.println(simpleDateFormat.format(new Date()) + message);
    }
}
