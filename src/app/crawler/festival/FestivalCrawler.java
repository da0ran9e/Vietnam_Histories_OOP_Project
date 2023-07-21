package app.crawler.festival;

import app.crawler.base.BaseWebsiteCrawler;
import app.crawler.base.ICrawler;
import app.history.festival.Festival;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FestivalCrawler extends BaseWebsiteCrawler implements ICrawler {

    public FestivalCrawler() {
        super("https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam", "src/app/data/json");
    }

    public void crawl() {
        List<Festival> list = new ArrayList<Festival>();
        try {
            Document document = Jsoup.parse(new URL(getUrl()).openStream(), "UTF-8", getUrl());
            Element fesBody = document.selectFirst("#mw-content-text > div.mw-parser-output > table.prettytable.wikitable > tbody");

            // Lay danh sach cac le hoi
            Elements fesList = fesBody.getElementsByTag("tr");

            for (int i = 1; i < fesList.size(); i++) {
                // Lay thong tin le hoi
                Elements fesDetail = fesList.get(i).getElementsByTag("td");

                String name = "", time = "", destination = "", description = "";

                // Lay ten le hoi
                name = fesDetail.get(2).text();

                // Lay thoi gian le hoi
                time = fesDetail.get(0).text();

                // Lay dia diem le hoi
                destination = fesDetail.get(1).text();

                // Lay mo ta cua le hoi
                if (fesDetail.size() >= 5) {
                    description = fesDetail.get(4).text();
                }

                // Tao le hoi
                Festival festival = new Festival(name, time, destination, description);

                // Them le hoi vao dnah sach
                if (!list.contains(festival)) {
                    list.add(festival);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeJsonFile(list);
    }

    public void writeJsonFile(List<Festival> list) {
        Gson gson = new Gson();
        try (FileWriter file = new FileWriter(getJsonStoreUrls() + "festival.json")) {
            file.write(gson.toJson(list));
            file.flush();
            System.out.println("Convert to json complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
