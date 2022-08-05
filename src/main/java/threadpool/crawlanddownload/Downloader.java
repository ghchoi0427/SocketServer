package threadpool.crawlanddownload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.UUID;

/**
 * image downloader
 * for shutterstock.com
 */
public class Downloader {

    private final String path;

    public Downloader(String path) {
        this.path = path;
    }

    public void downloadImage(String urlString) {
        URL url;
        InputStream in;
        OutputStream out;
        try {
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
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
