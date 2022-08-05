package threadpool;

import threadpool.crawlanddownload.Crawler;
import threadpool.crawlanddownload.Downloader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPool implements DownloadImage{

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
        String[] imageSourceArray = crawler.getImageSourceArray(baseUrl + keyword, page);
        downloader = new Downloader("C:\\temp");

        for (int i = 0; i < imageSourceArray.length; i++) {
            int finalI = i;
            Runnable runnable = () -> downloader.downloadImage(imageSourceArray[finalI]);
            service.submit(runnable);
        }
        service.shutdown();
    }
}
