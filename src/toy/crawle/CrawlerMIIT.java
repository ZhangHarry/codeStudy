package toy.crawle;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhanghr on 2017/3/15.
 */
public class CrawlerMIIT {
    static List<Car> cars = new LinkedList<>();
    private static void crawleIssue(String url, String dir, int pageNum) {
        for (int page = 1; page <= pageNum; page++) {
            try {
                Connection connection = Jsoup.connect(url+page)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                        .timeout(50000)
                        .ignoreContentType(true);
                Document doc = connection.get();
                StringBuffer sb = new StringBuffer();
                if (doc != null) {
                    Elements lines = doc.getElementsByClass("list-table").select("tbody").first().children();
                    String company = "", brand = "", productName = "";
                    for (int i = 1; i < lines.size(); i++) {
                        Car car = new Car();
                        Element element = lines.get(i);
                        Elements grids = element.select("td");
                        car.index = Integer.parseInt(grids.get(0).text());
                        if (grids.get(1).attr("style").contains("hidden")) {
                            car.company = company;
                        } else {
                            car.company = skipLast(grids.get(1).text());
                            company = car.company;
                        }
                        if (grids.get(3).attr("style").contains("hidden")) {
                            car.brand = brand;
                        } else {
                            car.brand = skipLast(grids.get(3).text());
                            brand = car.brand;
                        }
                        if (grids.get(4).attr("style").contains("hidden")) {
                            car.productName = productName;
                        } else {
                            car.productName = skipLast(grids.get(4).text());
                            productName = car.productName;
                        }
                        car.typeNo = skipLast(grids.get(5).text());
                        String href = grids.get(6).select("a").first().attr("href");
                        String[] attrs = href.split("'");
                        car.cp_month = skipLast(attrs[1]);
                        car.cp_id = skipLast(attrs[3]);
                        if (car.index>=1582)
                            System.out.format("%d,%s,%s,%s,%s,%s%n",
                                    car.index, car.company, car.brand, car.productName, car.typeNo,
                                    String.format("http://data.miit.gov.cn/datainfo/miit/cpsb_gsDetails.jsp?cp_month=%s&cp_id=%s", car.cp_month, car.cp_id));
//                        sb.append(car.string());
                        cars.add(car);
                    }
//                    FileProcesser.saveFile(dir + "//carPage"+page+".txt", sb.toString());
                }
            } catch (java.net.SocketTimeoutException e) {
                System.err.println(" time out try ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getDetail(Car car){
        String url = String.format("http://data.miit.gov.cn/datainfo/miit/cpsb_gsDetails.jsp?cp_month=%s&cp_id=%s", car.cp_month, car.cp_id);
        try {
            Connection connection = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .timeout(50000)
                    .ignoreContentType(true);
            Document doc = connection.get();
            StringBuffer sb = new StringBuffer();
            if (doc != null) {
                Elements lines = doc.getElementsByClass("list-table").select("tbody").first().children();
                String typeNo = lines.select("tr").get(0).select("td").get(3).text();
                String company = lines.select("tr").get(1).select("td").get(3).text();
                if (!typeNo.equals(car.typeNo))
                    System.err.print("typeNo error!"+typeNo+","+car.typeNo+"\t");
                else if (!company.equals(car.company))
                    System.err.print("company error!"+company+","+car.company);
                else
                    System.out.print("right=====");
                Elements content = doc.getElementsByClass("list-table").select("tbody").get(2).children();
                Element heightContainer = content.get(0);
                String size = heightContainer.select("td").toString();
                Element otherContainer = content.get(15);
                String other = otherContainer.select("td").toString();

            }
        } catch (java.net.SocketTimeoutException e) {
            System.err.println(" time out try ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String skipLast(String s){
        return s.replace("\\s+","");
    }

    public static void main(String[] args){
        CrawlerMIIT.crawleIssue("http://data.miit.gov.cn/datainfo/miit/cpsb_gs295.jsp?page=", "E:/", 69);
    }
}
