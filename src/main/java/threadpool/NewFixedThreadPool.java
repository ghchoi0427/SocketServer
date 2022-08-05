package threadpool;

import threadpool.crawlanddownload.Crawler;
import threadpool.crawlanddownload.Downloader;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPool implements DownloadImage {

    private final int nThread;
    private final String baseUrl = "https://www.shutterstock.com/search/";
    private Downloader downloader = new Downloader("C:\\temp");

    public NewFixedThreadPool(int nThread) {
        this.nThread = nThread;
    }

    @Override
    public void run(String keyword, int page) {
        ExecutorService service = Executors.newFixedThreadPool(nThread);
        Crawler crawler = new Crawler();
        List<String> imageSourceList = crawler.getImageSourceList(baseUrl + keyword, page);
        downloader = new Downloader("C:\\temp");

        imageSourceList.forEach(src -> {
            Runnable runnable = () -> downloader.downloadImage(src);
            service.submit(runnable);
        });
        service.shutdown();
    }
}
