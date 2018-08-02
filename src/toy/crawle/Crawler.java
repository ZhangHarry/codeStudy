package toy.crawle;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.FileProcesser;

import java.io.IOException;

/**
 * Created by zhanghr on 2017/3/15.
 */
public class Crawler {
    private static void crawleIssue(String url, String dir) {
        while (true) {
            try {
                Connection connection = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                        .timeout(50000)
                        .ignoreContentType(true);
                Document doc = connection.get();
                StringBuffer sb = new StringBuffer();
                if (doc != null) {
                    String title = doc.select("h3").first().text();
                    String name = title.substring(title.lastIndexOf("//"));
                    Elements lines = doc.select("source").get(0).children();
                    for (Element issue : lines) {
                        String content = issue.select("pre").toString();
                        sb.append(content);
                    }
                    FileProcesser.saveFile(dir+"//"+name, sb.toString());
                }
            } catch (java.net.SocketTimeoutException e) {
                System.err.println(" time out try ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Crawler.crawleIssue("http://babelfish.arc.nasa.gov/hg/jpf/eclipse-jpf/file/900fb8cd8191/src/gov/nasa/runjpf/wizard/NewJPFProjectPage.java","E:/");
    }
}
