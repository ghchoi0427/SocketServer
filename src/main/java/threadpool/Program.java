package threadpool;

public class Program {
    public static void main(String[] args) {
//        DownloadImage downloadImage = ThreadContainer.singleThread();
//        DownloadImage downloadImage  = ThreadContainer.multiThread();
//        DownloadImage downloadImage = ThreadContainer.cachedThreadPool();
        DownloadImage downloadImage = ThreadContainer.fixedThreadPool(100);
        downloadImage.run("shrek", 10);
    }
}

