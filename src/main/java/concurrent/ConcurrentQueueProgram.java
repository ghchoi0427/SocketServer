package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcurrentQueueProgram {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        ExecutorService service2 = Executors.newCachedThreadPool();
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        String baseUrl = "https://www.shutterstock.com/search/";
        int endPage = 10;
        String keyword = "cat";

        for (int i = 1; i < endPage; i++) {
            service.submit(new Crawler(queue, baseUrl, keyword, i));
        }

        while (!service.isTerminated()) {
            service2.submit(new Downloader(queue.take(), "C:\\temp"));
        }

        service.shutdown();
        service2.shutdown();
    }
}
