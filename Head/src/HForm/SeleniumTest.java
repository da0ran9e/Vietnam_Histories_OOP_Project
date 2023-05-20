package HForm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileWriter;
import java.io.IOException;

public class SeleniumTest {
    public static void seleniumEdge(){
        boolean stop = false;
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        driver.get("https://vi.wikipedia.org/w/index.php?title=%C4%90%E1%BA%B7c_bi%E1%BB%87t:T%C3%ACm_ki%E1%BA%BFm&limit=500&offset=0&ns0=1&search=m%E1%BA%A5t+sinh+ng%C6%B0%E1%BB%9Di+OR+qu%C3%AA+OR+m%E1%BA%A5t+OR+sinh+OR+t%C3%AAn+OR+%C3%B4ng+OR+b%C3%A0&advancedSearch-current={%22fields%22:{%22plain%22:[%22m%E1%BA%A5t%22,%22sinh%22],%22or%22:[%22ng%C6%B0%E1%BB%9Di%22,%22qu%C3%AA%22,%22m%E1%BA%A5t%22,%22sinh%22,%22t%C3%AAn%22,%22%C3%B4ng%22,%22b%C3%A0%22]}}");

        JSONObject keywords = new JSONObject();
        JSONArray keywordArray = new JSONArray();

        do{
            try {
                String pageSrc = driver.getPageSource();

                Document doc = Jsoup.parse(pageSrc);
                Elements elements = doc.select(".mw-search-results li");

                Elements resultsInfo = doc.select(".results-info");
                if (Integer.parseInt(resultsInfo.attr("data-mw-num-results-offset")) >= 9001) stop = true;
                for (Element element : elements) {
                    Element headingElement = element.select(".mw-search-result-heading").first();
                    if (headingElement != null) {
                        String title = headingElement.select("a").attr("title");

                        JSONObject keyword = new JSONObject();
                        keyword.put("key", title);
                        keywordArray.add(keyword);

                        System.out.println(title);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

            driver.findElement(By.className("mw-nextlink")).click();
        } while(!stop);

        keywords.put("Keywords", keywordArray);
        try(FileWriter file = new FileWriter("most_relevant_keywords.json")){
            file.write(keywords.toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        seleniumEdge();
    }
}

