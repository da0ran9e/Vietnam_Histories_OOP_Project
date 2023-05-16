package Jsoup;

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

/**
*This class contains methods to crawl Wikipedia pages and extract their content in JSON format. The class uses the JSoup library to connect to Wikipedia pages and extract their HTML content. The extracted content is then parsed and transformed into a JSON object, which is saved to a file in the local file system.
The class contains three methods: Href_loader, Space2Underscores, and Jsoup_find. The Href_loader method loads a list of Wikipedia page links from a JSON file and iterates over the links to extract their content. The Space2Underscores method replaces spaces in a string with underscores. The Jsoup_find method connects to a Wikipedia page and extracts its content.
The class uses the JSON.simple library to parse and create JSON objects. The class relies on the JSoup library to extract HTML content from Wikipedia pages, and it assumes that the content is well-formed and follows the expected structure of Wikipedia pages.
*/
public class Wikipedia_Crawler {

    /**
     * This function loads the links and keywords from a JSON file and crawls each link to extract the text content
     * @param filePath - path to the JSON file
     *                  The JSON file contains a list of links and keywords
     * @see Jsoup_find
     * @see Space2Underscores
     * @version 1.0
     *
     */
    public static void Href_loader(String filePath){
        //Đọc file JSON
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
            JSONArray linkArray = (JSONArray) jsonObject.get("TuKhoaVaLink");

            // Lặp qua danh sách các link
            for (Object obj : linkArray) {
                JSONObject linkObj = (JSONObject) obj;
                String link = (String) linkObj.get("Link");
                String title = (String) linkObj.get("TuKhoa");
                System.out.println("Connecting to: " + title + " href: " + link);

                    // Tạo JSON object lưu trữ dữ liệu
                JSONObject data3 = new JSONObject();
                JSONArray textArray = new JSONArray();
                data3.put("TuKhoa", title); // Tiêu đề của trang

                try{
                    Document document = Jsoup.connect(link).get();
                    // Chỉ lấy dữ liệu trong thẻ div có class là "mw-parser-output" (chỉ có văn bản)
                    Elements elements = document.select("div.mw-parser-output");
                    Elements mainText = elements.select("h2, h3, p");

                    for (Element text : mainText){
                        String tagName = text.tagName();
                        String content = text.text();
                        if (tagName.equals("h2") || tagName.equals("h3")) {
                            // Nếu là tiêu đề h2 hoặc h3 (các mục chính)
                            JSONObject section = new JSONObject();
                            section.put("TieuDe", content);
                            section.put("DoanVan", new JSONArray());
                            textArray.add(section);
                        } else if (tagName.equals("p")) {
                            // Nếu là đoạn văn bản
                            if(!textArray.isEmpty()){
                                JSONArray paragraphsArray = (JSONArray) ((JSONObject) textArray.get(textArray.size() - 1)).get("DoanVan");
                                paragraphsArray.add(content);
                            }
                        }
                    }

                    data3.put("TuKhoaVaVanBan", textArray); // Danh sách các mục chính và đoạn văn bản
            // Lưu dữ liệu vào file JSON
            try (FileWriter file = new FileWriter("ref_"+ title + ".json")) {
                file.write(data3.toJSONString());
                System.out.println("Lưu dữ liệu vào file ref_" + title + ".json thành công!");
            } catch (IOException e) {
                e.printStackTrace();
            }

                } catch (IOException e){
                    e.printStackTrace();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     *This function replaces spaces in a string with underscores
     * @param input - string to be processed
     *               The input string is a string containing spaces
     * @return output - processed string
     */
    public static String Space2Underscores(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ') {
                sb.append('_');
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * This function crawls a single Wikipedia page to extract the main sections and paragraphs
     * @param url - link to the Wikipedia page
     * @see Jsoup_find
     * @see Space2Underscores
     * @version 1.0
     *
     */
    private static void Jsoup_find(String url) {
        try {
            // Kết nối và tải nội dung của trang Wikipedia
            Document document = Jsoup.connect(url).get();
            Element bodyContentDiv = document.select("div#bodyContent").first();

            // Lấy tiêu đề của trang
            String title = document.title();
            System.out.println("Tiêu đề: " + title);

            // Tạo JSON object lưu trữ dữ liệu
            JSONObject data1 = new JSONObject();
            JSONArray mainSectionsArray = new JSONArray();
            data1.put("TieuDe", title); // Tiêu đề của trang

            // Lấy các mục chính và đoạn văn bản
            Elements mainSections = bodyContentDiv.select("h2, h3, p");
            for (Element mainSection : mainSections) {
                String tagName = mainSection.tagName();
                String content = mainSection.text();
                if (tagName.equals("h2") || tagName.equals("h3")) {
                    // Nếu là tiêu đề h2 hoặc h3 (các mục chính)
                    JSONObject section = new JSONObject();
                    section.put("TieuDe", content);
                    section.put("DoanVan", new JSONArray());
                    mainSectionsArray.add(section);
                } else if (tagName.equals("p")) {
                    // Nếu là đoạn văn bản
                    if(!mainSectionsArray.isEmpty()){
                        JSONArray paragraphsArray = (JSONArray) ((JSONObject) mainSectionsArray.get(mainSectionsArray.size() - 1)).get("DoanVan");
                        paragraphsArray.add(content);
                    }
                }
            }
            data1.put("MucChinhVaDoanVan", mainSectionsArray); // Danh sách các mục chính và đoạn văn bản

            // Lưu dữ liệu vào file JSON
            try (FileWriter file = new FileWriter(title + ".json")) {
                file.write(data1.toJSONString());
                System.out.println("Lưu dữ liệu vào file " + title + ".json thành công!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Tạo JSON object lưu trữ dữ liệu từ khóa và link
            JSONObject data2 = new JSONObject();
            JSONArray keywordsArray = new JSONArray();

            // Lấy các từ khóa và link đính kèm
            Elements links = document.select("a[href]");
            for (Element link : links) {
                String keyword = link.text();
                String linkUrl = "";
                if(!keyword.isEmpty() && Character.isDigit(keyword.charAt(0))){
                    linkUrl = link.attr("abs:href");
                }
                else {
                    linkUrl = "https://vi.wikipedia.org/wiki/"+Space2Underscores(keyword);
                }
                if (keyword.length()>0 && !linkUrl.isEmpty()) {
                    JSONObject keywordObj = new JSONObject();
                    keywordObj.put("TuKhoa", keyword);
                    keywordObj.put("Link", linkUrl);
                    keywordsArray.add(keywordObj);
                }
            }
            data2.put("TuKhoaVaLink", keywordsArray); // Danh sách từ khóa và link

            // Lưu dữ liệu vào file JSON
            try (FileWriter file = new FileWriter("links_" + title + ".json")) {
                file.write(data2.toJSONString());
                System.out.println("Lưu dữ liệu vào file " + title + "_keywords.json thành công!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void JsoupCollect(String pageUrl){

        try {
            // Connect to the Wikipedia page and retrieve the HTML content
            Document doc = Jsoup.connect(pageUrl).get();

            // Extract the page title
            String pageTitle = doc.title();
            System.out.println("Page title: " + pageTitle);

            // Extract the introduction section text
            Element introSection = doc.select("#mw-content-text > div > p").first();
            String introText = introSection.text();
            System.out.println("Introduction text: " + introText);

            // Extract the table of contents links (if it exists)
            Element toc = doc.select("#toc").first();
            if (toc != null) {
                Elements tocLinks = toc.select("a[href^=\"#\"]");
                System.out.println("Table of contents links:");
                for (Element tocLink : tocLinks) {
                    String linkText = tocLink.text();
                    String linkUrl = tocLink.attr("href");
                    System.out.println(linkText + " - " + linkUrl);
                }
            }

            // Extract the references section
            Element referencesSection = doc.select("div.reflist").first();
            String referencesHtml = referencesSection.html();
            System.out.println("References section HTML: " + referencesHtml);

            // Extract any images on the page
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            System.out.println("Images on the page:");
            for (Element image : images) {
                String imageUrl = image.attr("src");
                System.out.println(imageUrl);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String url = "https://vi.wikipedia.org/wiki/Lịch_sử_Việt_Nam";
        String path = "links_Lịch sử Việt Nam – Wikipedia tiếng Việt.json";
//        Jsoup_find(url);
//        Href_loader(path);
        JsoupCollect(url);
    }
}
