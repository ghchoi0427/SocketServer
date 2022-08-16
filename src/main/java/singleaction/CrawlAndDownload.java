package singleaction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrawlAndDownload implements Runnable{

    private final String url;
    private final String keyword;
    private final int page;
    private final String folderName;
    private final ExecutorService service;

    public CrawlAndDownload(String url, String keyword, int page, String folderName) {
        this.url = url;
        this.keyword = keyword;
        this.page = page;
        this.folderName = folderName;
        service = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        Document document;
        try {
            document = Jsoup.connect(url + keyword + "?page=" + page).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get();
            Elements images = document.select("img[src~=(?i)\\.(jpe?g)]");
            images.forEach(img -> downloadImage(img.attr("src"), folderName));
            service.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadImage(String urlString, String folderName) {
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
