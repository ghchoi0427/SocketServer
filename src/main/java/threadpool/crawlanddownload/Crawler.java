package threadpool.crawlanddownload;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler extends Thread {

    public List<String> getImageSource(String url) {
        List<String> results = new ArrayList<>();
        Document document;
        try {
            document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get();
            Elements images = document.select("img[src~=(?i)\\.(jpe?g)]");
            for (Element image : images) {
                results.add(image.attr("src"));
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public String[] getImageSourceArray(String url, int endPage) {
        List<String> results = new ArrayList<>();
        Document document;

        for (int page = 1; page <= endPage; page++) {
            try {
                document = Jsoup.connect(url+"?page="+page).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36").get();
                Elements images = document.select("img[src~=(?i)\\.(jpe?g)]");
                for (Element image : images) {
                    results.add(image.attr("src"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return results.toArray(new String[0]);
    }
}
