package concurrent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class Crawler implements Runnable {

    private final LinkedBlockingQueue<String> queueObject;
    private final String url;
    private final String keyword;

    private final int page;

    public Crawler(LinkedBlockingQueue<String> queueObject, String url, String keyword, int page) {
        this.queueObject = queueObject;
        this.url = url;
        this.keyword = keyword;
        this.page = page;
    }

    @Override
    public void run() {
        getSinglePageSrc(url, page);
    }

    private void getSinglePageSrc(String url, int page) {
        Document document;
        try {
            document = Jsoup.connect(url + keyword + "?page=" + page).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get();
            Elements images = document.select("img[src~=(?i)\\.(jpe?g)]");
            for (Element image : images) {
                queueObject.offer(image.attr("src"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
