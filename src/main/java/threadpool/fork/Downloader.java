package threadpool.fork;

import java.io.*;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class Downloader {
    private final QueueObject queueObject = QueueObject.getInstance();
    private final ExecutorService service;
    URL url;
    InputStream in;
    OutputStream out;
    private final String path;

    public Downloader(ExecutorService service, String path) {
        this.service = service;
        this.path = path;
    }

    public void run() throws IOException {
        while (queueObject.getQueue().size() != 0) {
            service.submit(() -> {
                try {
                    downloadImage(queueObject.poll());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        service.shutdown();
        closeStream();
    }


    private void downloadImage(String urlString) throws IOException {
        url = new URL(urlString);
        in = url.openStream();
        File file = new File(path, UUID.randomUUID() + ".jpg");
        out = new FileOutputStream(file);
        while (true) {
            int data = in.read();
            if (data == -1) {
                break;
            }
            out.write(data);
        }
    }

    private void closeStream() throws IOException {
        if (in != null && out != null) {
            in.close();
            out.close();
        }
    }
}
