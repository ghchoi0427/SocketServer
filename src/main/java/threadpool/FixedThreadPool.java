package threadpool;

import threadpool.crawlanddownload.Crawler;
import threadpool.crawlanddownload.Downloader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool implements DownloadImage {
    private final int nThreads;
    private final String baseUrl = "https://www.shutterstock.com/search/";

    public FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
    }

    @Override
    public void run(String keyword, int page) {
        Crawler crawler = new Crawler();
        String[] imageSourceArray = crawler.getImageSourceArray(baseUrl + keyword, page);

        ExecutorService service = Executors.newFixedThreadPool(nThreads);
        int length = imageSourceArray.length;

        for (int index = 0; index < nThreads; index++) {
            int finalIndex = index;
            service.submit(() -> {
                task(imageSourceArray, (length * finalIndex / nThreads), (length * (finalIndex + 1) / nThreads));
            });
        }

        service.shutdownNow();
    }

    void task(String[] sources, int start, int end) {
        for (int i = start; i < end; i++) {
            new Downloader("C:\\temp").downloadImage(sources[i]);
        }
    }
}
