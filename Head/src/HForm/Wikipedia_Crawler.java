package HForm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
*This class contains methods to crawl Wikipedia pages and extract their content in JSON format. The class uses the JSoup library to connect to Wikipedia pages and extract their HTML content. The extracted content is then parsed and transformed into a JSON object, which is saved to a file in the local file system.
The class contains three methods: Href_loader, Space2Underscores, and Jsoup_find. The Href_loader method loads a list of Wikipedia page links from a JSON file and iterates over the links to extract their content. The Space2Underscores method replaces spaces in a string with underscores. The Jsoup_find method connects to a Wikipedia page and extracts its content.
The class uses the JSON.simple library to parse and create JSON objects. The class relies on the JSoup library to extract HTML content from Wikipedia pages, and it assumes that the content is well-formed and follows the expected structure of Wikipedia pages.
*/
public class Wikipedia_Crawler {
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
      public static List<String> Go(String name) {
        List<String> data = new ArrayList<String>(50);
        try {
            // Kết nối tới trang web chứa thông tin về thời kì
            String url = "https://vi.wikipedia.org/wiki/"+Space2Underscores(name);
            Document document = Jsoup.connect(url).get();
            //Lấy dữ liệu của bảng thông tin
            Elements tables = document.select("table.infobox tbody");
            // Lấy các hàng trong bảng thông tin
            Elements rows = tables.select("tr");
            for (Element row : rows) {
                    data.add(row.text());
            }
                    System.out.println(data);
            return data;
            } catch(Exception e){
                e.printStackTrace();
            }
        return data;
        }
        public static List<String> mainContents(String name){
        List<String> data = new ArrayList<String>(50);
        try{
            String url = "https://vi.wikipedia.org/wiki/"+Space2Underscores(name);
                    Document document = Jsoup.connect(url).get();
                    // Chỉ lấy dữ liệu trong thẻ div có class là "mw-parser-output" (chỉ có văn bản)
                    Elements elements = document.select("div.mw-parser-output");
                    Elements mainText = elements.select("h2, h3, p");

                    for (Element text : mainText){
                        String tagName = text.tagName();
                        String content = text.text();
                        if (tagName.equals("h2") || tagName.equals("h3") || tagName.equals("p")) {
                            data.add(content);
                        }
                    }
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;

        }
        public static List<String> getImage(String name){
         List<String> data = new ArrayList<String>(50);
        try {
            // Kết nối tới trang web chứa thông tin về thời kì https://vi.wikipedia.org/wiki/Tập_tin:Northern_and_Southern_Dynasties_3.png
            String url = "https://vi.wikipedia.org/wiki/"+Space2Underscores(name);
            Document document = Jsoup.connect(url).get();
            Elements images = document.select("img[src]");
            for (Element image:images){
                if(image.attr("abs:src").charAt(8) == 'u'){
                    data.add(image.attr("abs:src"));
                    System.out.println("https:" + image.attr("abs:src"));
                }
            }
            } catch(Exception e){
                e.printStackTrace();
            }
        return data;
        }
    }
