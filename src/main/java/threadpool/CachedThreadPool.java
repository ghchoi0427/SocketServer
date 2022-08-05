package threadpool;

import threadpool.crawlanddownload.Crawler;
import threadpool.crawlanddownload.Downloader;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool implements DownloadImage {
    private final String baseUrl = "https://www.shutterstock.com/search/";

    @Override
    public void run(String keyword, int page) {
        ExecutorService service = Executors.newCachedThreadPool();
        Crawler crawler = new Crawler();
        Downloader downloader = new Downloader("C:\\temp");
        List<String> imageSourceList = crawler.getImageSourceList(baseUrl + keyword, page);
        imageSourceList.forEach(src -> service.submit(() -> downloader.downloadImage(src)));
        service.shutdown();
    }
}
