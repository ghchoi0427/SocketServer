package threadpool;

public class Program {
    public static void main(String[] args) {
        DownloadImage downloadImage = ThreadContainer.getCustom(64);
        downloadImage.run("cat", 1);
    }
}

