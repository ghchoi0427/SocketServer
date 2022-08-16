package concurrent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.UUID;

public class Downloader implements Runnable {
    private final String urlString;
    private final String folderName;

    public Downloader(String urlString, String folderName) {
        this.urlString = urlString;
        this.folderName = folderName;
    }

    @Override
    public void run() {
        downloadImage(urlString, folderName);
    }

    public void downloadImage(String urlString, String folderName) {
        URL url;
        InputStream in;
        OutputStream out;
        try {
            url = new URL(urlString);
            in = url.openStream();
            File file = new File(folderName, UUID.randomUUID() + ".jpg");
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
