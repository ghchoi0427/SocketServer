import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int BUFFER_SIZE = 1024;

    public void startServer(int port) {
        try (ServerSocket server = new ServerSocket()) {
            InetSocketAddress ipep = new InetSocketAddress(port);
            server.bind(ipep);

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
        try (OutputStream outputStream = clientSocket.getOutputStream();
             InputStream inputStream = clientSocket.getInputStream()) {
            byte[] bytes = new byte[BUFFER_SIZE];

            while (true) {
                inputStream.read(bytes, 0, bytes.length);
                print(new String(bytes));
                outputStream.write(bytes);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            print("Client disconnected IP address =" + clientSocket.getRemoteSocketAddress().toString());
        }
    }

    private static void print(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[hh:mm:ss]");
        System.out.println(simpleDateFormat.format(new Date()) + message);
    }
}