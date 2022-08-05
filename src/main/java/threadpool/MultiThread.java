package threadpool;

import threadpool.crawlanddownload.Crawler;
import threadpool.crawlanddownload.Downloader;

import java.util.Arrays;

public class MultiThread implements DownloadImage {
    private final String baseUrl = "https://www.shutterstock.com/search/";
    @Override
    public void run(String keyword, int page) {
        Crawler crawler = new Crawler();
        String[] imageSourceArray = crawler.getImageSourceArray(baseUrl + keyword, page);

        Arrays.stream(imageSourceArray).forEach(src -> {
            Runnable runnable = () -> new Downloader("C:\\temp").downloadImage(src);
            runnable.run();
        });
    }
}
