package HForm;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SeleniumTest {
    public static void seleniumEdge(){
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        JSONObject keywords = new JSONObject();
        JSONArray keywordArray = new JSONArray();

        for(int i=1; i<=1900; i++) {
            driver.get("https://vi.wikipedia.org/w/index.php?title=%C4%90%E1%BA%B7c_bi%E1%BB%87t:T%C3%ACm_ki%E1%BA%BFm&limit=500&offset=0&ns0=1&search=deepcat%3A%22M%E1%BA%A5t+n%C4%83m+"+i+"%22&advancedSearch-current={%22fields%22:{%22deepcategory%22:[%22M%E1%BA%A5t%20n%C4%83m%202010%22]}}");
            try {
                String pageSrc = driver.getPageSource();
                Document doc = Jsoup.parse(pageSrc);
                Elements elements = doc.select(".mw-search-results li");
                for (Element element : elements) {
                    Element headingElement = element.select(".mw-search-result-heading").first();
                    if (headingElement != null) {
                        String title = headingElement.select("a").attr("title");

                        JSONObject keyword = new JSONObject();
                        keyword.put(i, title);
                        keywordArray.add(keyword);

                        System.out.println(title);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }

            keywords.put("Keywords", keywordArray);
            try (FileWriter file = new FileWriter("b_years.json")) {
                file.write(keywords.toJSONString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public static DefaultListModel<String> keywordsList() throws IOException, ParseException, JSONException {
        DefaultListModel<String> list = new DefaultListModel<>();
        try{
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("most_relevant_keywords.json"));

            JSONArray keyList = (JSONArray) jsonObject.get("Keywords");
            for(Object keyword:keyList){
                JSONObject key = (JSONObject) keyword;
                String word = (String) key.get("key");
                list.addElement(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}

