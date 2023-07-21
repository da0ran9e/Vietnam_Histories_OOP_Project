package app.crawler.event;

import app.crawler.base.BaseWebsiteCrawler;
import app.crawler.base.ICrawler;
import app.history.event.Event;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EventCrawler extends BaseWebsiteCrawler implements ICrawler {

    public EventCrawler() {
        super("https://nguoikesu.com", "src/app/data/json", "src/app/data/img/event");
    }

    private void saveImg(String src_image, String name, String dir) {
        try {
            URL url;
            if (!src_image.equals("")) {
                url = new URL(getUrl() + src_image);
            } else {
                url = new URL("https://www.ncenet.com/wp-content/uploads/2020/04/No-image-found.jpg");
            }
            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(dir + "\\" + name));
            for (int b; (b = in.read()) != -1; ) {
                out.write(b);
            }
            out.close();
            in.close();
            System.out.println("Download sucessfull");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not Dowload File !");
            e.printStackTrace();
        }
    }

    public void crawl() {
        List<Event> list = new ArrayList<Event>();
        try {
            int endIndex = 70;
            int index = 0;

            for (int i = 0; i <= endIndex; i += 5) {
                String url;
                if (i == 0) {
                    url = getUrl() + "/tu-lieu/quan-su";
                } else {
                    url = getUrl() + "/tu-lieu/quan-su?start=" + i;
                }
                Document document = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
                Elements elms = document.getElementsByClass("page-header");
                Elements elms_descs = document.getElementsByAttributeValue("itemprop", "blogPost");
                for (int j = 0; j < elms.size(); ++j) {
                    ++index;
                    int imgIndex;
                    if (j == 1) {
                        imgIndex = 3;
                    } else if (j > 1) {
                        imgIndex = j + 2;
                    } else {
                        imgIndex = j + 1;
                    }
                    // Lay ten cua su kien
                    Elements elm_row = elms.get(j).getElementsByTag("a");
                    String name = elm_row.text();

                    // Lay mo ta cua su kien
                    Elements elms_desc = elms_descs.get(j).getElementsByTag("p");
                    String description = elms_desc.text().substring(0, elms_desc.text().length() - 1 - 11); // Dung subString loc "Xem cuoi..."

                    // Lay link anh cua su kien
                    String imgUrl;
                    Element elms_img = document
                            .selectFirst(
                                    "#content > div.com-content-category-blog.blog > div.com-content-category-blog__items.blog-items.items-leading > div:nth-child("
                                            + imgIndex + ") > figure > img");
                    if (elms_img == null) {
                        imgUrl = "";
                    } else {
                        imgUrl = elms_img.attr("data-src");
                    }

                    // Luu anh cua su kien
                    saveImg(imgUrl, index + ".png", getImgStoreUrls());

                    // Lay link chi tiet cua su kien
                    String link_event = elm_row.first().absUrl("href");
                    Document document2 = Jsoup.parse(new URL(link_event).openStream(), "UTF-8", link_event);

                    // Lay thoi gian cua su kien
                    String time = document2.select(
                                    "#content > div.com-content-article.item-page > div.com-content-article__body > div.infobox > table > tbody > tr > td > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(1) > td:nth-child(2)")
                            .text();
                    String destination = document2.select(
                                    "#content > div.com-content-article.item-page > div.com-content-article__body > div.infobox > table > tbody > tr > td > table > tbody > tr:nth-child(4) > td > table > tbody > tr:nth-child(2) > td:nth-child(2)")
                            .text();

                    // Lay danh sach nhung nguoi lien quan den su kien
                    List<String> relativePersons = new ArrayList<String>();
                    Elements first_col = document2.select(
                            "#content > div.com-content-article.item-page > div.com-content-article__body > div.infobox > table > tbody > tr > td > table > tbody > tr:nth-child(8) > td:nth-child(1)");
                    Elements second_col = document2.select(
                            "#content > div.com-content-article.item-page > div.com-content-article__body > div.infobox > table > tbody > tr > td > table > tbody > tr:nth-child(8) > td:nth-child(2)");
                    if (first_col.size() > 0) {
                        for (int k = 0; k < first_col.size(); k++) {
                            Elements nameList = first_col.get(k).getElementsByTag("a");
                            for (int l = 0; l < nameList.size(); l++) {
                                String personName = nameList.get(l).text();
                                relativePersons.add(personName);
                            }
                        }
                    }
                    if (second_col.size() > 0) {
                        for (int k = 0; k < second_col.size(); k++) {
                            Elements nameList = second_col.get(k).getElementsByTag("a");
                            for (int l = 0; l < nameList.size(); l++) {
                                String personName = nameList.get(l).text();
                                relativePersons.add(personName);
                            }
                        }
                    }

                    // Tao su kien
                    Event event = new Event(name, time, destination, description,
                            index + ".png", relativePersons);

                    // Them su kien vao danh sach su kien
                    if (!list.contains(event)) {
                        list.add(event);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeJsonFile(list);
    }

    public void writeJsonFile(List<Event> list) {
        Gson gson = new Gson();
        try (FileWriter file = new FileWriter(getJsonStoreUrls() + "/event.json")) {
            file.write(gson.toJson(list));
            file.flush();
            System.out.println("Convert to json complete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}