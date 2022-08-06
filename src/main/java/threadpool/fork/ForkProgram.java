package threadpool.fork;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForkProgram {

    public static void main(String[] args) throws IOException, InterruptedException {
        String baseUrl = "https://www.shutterstock.com/search/";
        String keyword = "shrek";
        ExecutorService service = Executors.newCachedThreadPool();

        Crawler crawler = new Crawler(service);
        Downloader downloader = new Downloader(service, "C:\\temp");
        crawler.run(baseUrl, keyword, 10);
        Thread.sleep(2000);
        downloader.run();
    }
}
