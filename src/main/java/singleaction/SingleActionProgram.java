package singleaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleActionProgram {
    public static void main(String[] args) {
        String baseUrl = "https://www.shutterstock.com/search/";
        int endPage = 10;
        String keyword = "cat";
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 1; i < endPage; i++) {
            service.submit(new CrawlAndDownload(baseUrl, keyword, i, "C:\\temp"));
        }
        service.shutdown();
    }
}
