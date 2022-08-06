package threadpool.fork;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class Crawler {

    private final QueueObject queueObject = QueueObject.getInstance();
    private final ExecutorService service;

    public Crawler(ExecutorService service) {
        this.service = service;
    }

    public void run(String url, String keyword, int endPage) {
        for (int page = 1; page <= endPage; page++) {
            int finalPage = page;
            service.submit(() -> getSinglePageSrc(url + keyword, finalPage));
        }
//        service.shutdown();
    }

    private void getSinglePageSrc(String url, int page) {
        Document document;
        try {
            document = Jsoup.connect(url + "?page=" + page).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get();
            Elements images = document.select("img[src~=(?i)\\.(jpe?g)]");
            for (Element image : images) {
                queueObject.add(image.attr("src"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
