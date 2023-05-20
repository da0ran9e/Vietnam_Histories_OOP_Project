package HForm;

import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class WikiList {
        public static void PersonList(){
                try {
                    String link = "https://vi.wikipedia.org/w/index.php?search=m%E1%BA%A5t+sinh+ng%C6%B0%E1%BB%9Di+OR+qu%C3%AA+OR+m%E1%BA%A5t+OR+sinh+OR+t%C3%AAn&title=%C4%90%E1%BA%B7c+bi%E1%BB%87t%3AT%C3%ACm+ki%E1%BA%BFm&profile=advanced&fulltext=1&advancedSearch-current=%7B%22fields%22%3A%7B%22plain%22%3A%5B%22m%E1%BA%A5t%22%2C%22sinh%22%5D%2C%22or%22%3A%5B%22ng%C6%B0%E1%BB%9Di%22%2C%22qu%C3%AA%22%2C%22m%E1%BA%A5t%22%2C%22sinh%22%2C%22t%C3%AAn%22%5D%7D%7D&ns0=1&ns4=1&ns8=1&ns14=1&ns100=1&ns710=1&ns828=1&ns2300=1";
                    Document document = Jsoup.connect(link).get();
                    //Document document = Jsoup.parse(SeleniumTest.seleniumEdge());
                    Elements resultsInfo = document.select(".results-info");
                    System.out.println(resultsInfo.attr("data-mw-num-results-total"));
                    Elements elements = document.select(".mw-search-results li");
                    System.out.println(elements.size());

                    for (Element element : elements) {

//                        System.out.println(element.text());
//                        Element headingElement = element.select(".mw-search-result-heading").first();
//                        if(headingElement!=null){
//                            String title = headingElement.select("a").text();
//                            System.out.println(title);
//                        }

                    }
//                        if (tagName.equals("h2") || tagName.equals("h3")) {
//                            // Nếu là tiêu đề h2 hoặc h3 (các mục chính)
//                            JSONObject section = new JSONObject();
//                            section.put("TieuDe", content);
//                            section.put("DoanVan", new JSONArray());
//                            textArray.add(section);
//                        } else if (tagName.equals("p")) {
//                            // Nếu là đoạn văn bản
//                            if(!textArray.isEmpty()){
//                                JSONArray paragraphsArray = (JSONArray) ((JSONObject) textArray.get(textArray.size() - 1)).get("DoanVan");
//                                paragraphsArray.add(content);
//                            }
//                        }
//                    }
//        JSONParser parser = new JSONParser();
//        try {
//            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(jsonFilePath));
//            JSONArray linkArray = (JSONArray) jsonObject.get("TuKhoaVaLink");
//
//            // Lặp qua danh sách các link
//            for (Object obj : linkArray) {
//                JSONObject linkObj = (JSONObject) obj;
//                String link = (String) linkObj.get("Link");
//                String title = (String) linkObj.get("TuKhoa");
//                System.out.println("Connecting to: " + title + " href: " + link);
//
//                    // Tạo JSON object lưu trữ dữ liệu
//                JSONObject data3 = new JSONObject();
//                JSONArray textArray = new JSONArray();
//                data3.put("TuKhoa", title); // Tiêu đề của trang
//
//                try{
//                    Document document = Jsoup.connect(link).get();
//                    // Chỉ lấy dữ liệu trong thẻ div có class là "mw-parser-output" (chỉ có văn bản)
//                    Elements elements = document.select("div.mw-parser-output");
//                    Elements mainText = elements.select("h2, h3, p");
//
//                    for (Element text : mainText){
//                        String tagName = text.tagName();
//                        String content = text.text();
//                        if (tagName.equals("h2") || tagName.equals("h3")) {
//                            // Nếu là tiêu đề h2 hoặc h3 (các mục chính)
//                            JSONObject section = new JSONObject();
//                            section.put("TieuDe", content);
//                            section.put("DoanVan", new JSONArray());
//                            textArray.add(section);
//                        } else if (tagName.equals("p")) {
//                            // Nếu là đoạn văn bản
//                            if(!textArray.isEmpty()){
//                                JSONArray paragraphsArray = (JSONArray) ((JSONObject) textArray.get(textArray.size() - 1)).get("DoanVan");
//                                paragraphsArray.add(content);
//                            }
//                        }
//                    }
//
//                    data3.put("TuKhoaVaVanBan", textArray); // Danh sách các mục chính và đoạn văn bản
//            // Lưu dữ liệu vào file JSON
//            try (FileWriter file = new FileWriter("ref_"+ title + ".json")) {
//                file.write(data3.toJSONString());
//                System.out.println("Lưu dữ liệu vào file ref_" + title + ".json thành công!");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//                } catch (IOException e){
//                    e.printStackTrace();
//                }
//            }

                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

    public static void main(String[] args) {
            PersonList();
    }
}
