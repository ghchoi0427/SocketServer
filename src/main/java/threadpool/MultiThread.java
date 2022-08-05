package threadpool;

import threadpool.crawlanddownload.Crawler;
import threadpool.crawlanddownload.Downloader;

import java.util.List;

public class MultiThread implements DownloadImage {
    private final String baseUrl = "https://www.shutterstock.com/search/";

    @Override
    public void run(String keyword, int page) {
        Crawler crawler = new Crawler();
        Downloader downloader = new Downloader("C:\\temp");
        List<String> imageSourceList = crawler.getImageSourceList(baseUrl + keyword, page);

        imageSourceList.forEach(src->{
            Runnable runnable = () -> downloader.downloadImage(src);
            new Thread(runnable).start();
        });
    }
}
