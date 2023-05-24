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
    public static String sanitizeFileName(String fileName) {
        // Define a set of invalid characters not allowed in file names
        String invalidChars = "\\/:*?\"<>|";

        // Replace each invalid character with an empty string
        for (char c : invalidChars.toCharArray()) {
            fileName = fileName.replace(c, '_');
        }

        return fileName;
    }
    public static void seleniumEdge(){
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        JSONObject keywords = new JSONObject();
        JSONArray keywordArray = new JSONArray();

        for(int i=1; i<=1900; i++) {
            driver.get("https://vi.wikipedia.org/w/index.php?title=%C4%90%E1%BA%B7c_bi%E1%BB%87t:T%C3%ACm_ki%E1%BA%BFm&limit=500&offset=0&ns0=1&search=Vi%E1%BB%87t+Nam+deepcat%3A%22M%E1%BA%A5t+n%C4%83m+"+i+"%22&advancedSearch-current={%22fields%22:{%22deepcategory%22:[%22M%E1%BA%A5t%20n%C4%83m%202022%22]}}");
            try {
                String pageSrc = driver.getPageSource();
                Document doc = Jsoup.parse(pageSrc);
                Elements elements = doc.select(".mw-search-results li");
                for (Element element : elements) {
                    Element headingElement = element.select(".mw-search-result-heading").first();
                    if (headingElement != null) {
                        String title = headingElement.select("a").attr("title");

                        JSONObject keyword = new JSONObject();
                        keyword.put("name", title);
                        keywordArray.add(keyword);

                        System.out.println(title);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }

            keywords.put("Keywords", keywordArray);
            try (FileWriter file = new FileWriter("V_List.json")) {
                file.write(keywords.toJSONString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public static void seleniumV1Edge(){
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        JSONObject keywords = new JSONObject();
        JSONArray keywordArray = new JSONArray();

        for(int i=1; i<=19; i++) {
            driver.get("https://vi.wikipedia.org/w/index.php?title=%C4%90%E1%BA%B7c_bi%E1%BB%87t:T%C3%ACm_ki%E1%BA%BFm&limit=500&offset=0&ns0=1&search=Vi%E1%BB%87t+Nam+deepcat%3A%22M%E1%BA%A5t+th%E1%BA%BF+k%E1%BB%B7+"+i+"+TCN%22&advancedSearch-current={%22fields%22:{%22deepcategory%22:[%22M%E1%BA%A5t%20th%E1%BA%BF%20k%E1%BB%B7%202%20TCN%22]}}");
            try {
                String pageSrc = driver.getPageSource();
                Document doc = Jsoup.parse(pageSrc);
                Elements elements = doc.select(".mw-search-results li");
                for (Element element : elements) {
                    Element headingElement = element.select(".mw-search-result-heading").first();
                    if (headingElement != null) {
                        String title = headingElement.select("a").attr("title");

                        JSONObject keyword = new JSONObject();
                        keyword.put("name", title);
                        keywordArray.add(keyword);

                        System.out.println(title);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }


            keywords.put("Keywords", keywordArray);
            try (FileWriter file = new FileWriter("V1_List.json")) {
                file.write(keywords.toJSONString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public static void seleniumV2Edge(){
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        JSONObject keywords = new JSONObject();
        JSONArray keywordArray = new JSONArray();

        for(int i=1; i<=19; i++) {
            driver.get("https://vi.wikipedia.org/w/index.php?title=%C4%90%E1%BA%B7c_bi%E1%BB%87t:T%C3%ACm_ki%E1%BA%BFm&limit=5000&offset=0&ns0=1&search=Vi%E1%BB%87t+Nam+deepcat%3A%22M%E1%BA%A5t+th%E1%BA%BF+k%E1%BB%B7+"+i+"%22&advancedSearch-current={%22fields%22:{%22deepcategory%22:[%22M%E1%BA%A5t%20th%E1%BA%BF%20k%E1%BB%B7%2016%22]}}");
            try {
                String pageSrc = driver.getPageSource();
                Document doc = Jsoup.parse(pageSrc);
                Elements elements = doc.select(".mw-search-results li");
                for (Element element : elements) {
                    Element headingElement = element.select(".mw-search-result-heading").first();
                    if (headingElement != null) {
                        String title = headingElement.select("a").attr("title");

                        JSONObject keyword = new JSONObject();
                        keyword.put("name", title);
                        keywordArray.add(keyword);

                        System.out.println(title);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }


            keywords.put("Keywords", keywordArray);
            try (FileWriter file = new FileWriter("V2_List.json")) {
                file.write(keywords.toJSONString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public static void seleniumVKingEdge(){
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        JSONObject keywords = new JSONObject();
        JSONArray keywordArray = new JSONArray();

        for(int i=1; i<=1; i++) {
            driver.get("https://vi.wikipedia.org/w/index.php?title=%C4%90%E1%BA%B7c_bi%E1%BB%87t:T%C3%ACm_ki%E1%BA%BFm&limit=500&offset=0&ns0=1&search=Vi%E1%BB%87t+Nam+deepcat%3A%22Vua+Vi%E1%BB%87t+Nam%22&advancedSearch-current=%7B%22fields%22%3A%7B%22deepcategory%22%3A%5B%22Vua+Vi%E1%BB%87t+Nam%22%5D%2C%22phrase%22%3A%22Vi%E1%BB%87t+Nam%22%7D%7D&searchToken=a0uiid62cv5d10fcox5hf0zvp");
            try {
                String pageSrc = driver.getPageSource();
                Document doc = Jsoup.parse(pageSrc);
                Elements elements = doc.select(".mw-search-results li");
                for (Element element : elements) {
                    Element headingElement = element.select(".mw-search-result-heading").first();
                    if (headingElement != null) {
                        String title = headingElement.select("a").attr("title");

                        JSONObject keyword = new JSONObject();
                        keyword.put("name", title);
                        keywordArray.add(keyword);

                        System.out.println(title);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }


            keywords.put("Keywords", keywordArray);
            try (FileWriter file = new FileWriter("VK_List.json")) {
                file.write(keywords.toJSONString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public static void seleniumV3Edge(){
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        JSONObject keywords = new JSONObject();
        JSONArray keywordArray = new JSONArray();

        for(int i=1; i<=2000; i++) {
            driver.get("https://vi.wikipedia.org/w/index.php?title=%C4%90%E1%BA%B7c_bi%E1%BB%87t:T%C3%ACm_ki%E1%BA%BFm&limit=500&offset=0&ns0=1&search=Vi%E1%BB%87t+Nam+deepcat%3A%22Sinh+n%C4%83m+"+i+"%22&advancedSearch-current={%22fields%22:{%22phrase%22:%22Vi%E1%BB%87t%20Nam%22,%22deepcategory%22:[%22Sinh%20n%C4%83m%201953%22]}}");
            try {
                String pageSrc = driver.getPageSource();
                Document doc = Jsoup.parse(pageSrc);
                Elements elements = doc.select(".mw-search-results li");
                for (Element element : elements) {
                    Element headingElement = element.select(".mw-search-result-heading").first();
                    if (headingElement != null) {
                        String title = headingElement.select("a").attr("title");

                        JSONObject keyword = new JSONObject();
                        keyword.put("name", title);
                        keywordArray.add(keyword);

                        System.out.println(title);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }


            keywords.put("Keywords", keywordArray);
            try (FileWriter file = new FileWriter("V3_List.json")) {
                file.write(keywords.toJSONString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public static void seleniumV5Edge(){
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        JSONObject keywords = new JSONObject();
        JSONArray keywordArray = new JSONArray();

        for(int i=1; i<=19; i++) {
            driver.get("https://vi.wikipedia.org/w/index.php?search=Vi%E1%BB%87t+Nam+deepcat%3A%22Sinh+th%E1%BA%BF+k%E1%BB%B7+"+i+"+TCN%22&title=%C4%90%E1%BA%B7c+bi%E1%BB%87t%3AT%C3%ACm+ki%E1%BA%BFm&profile=advanced&fulltext=1&advancedSearch-current=%7B%22fields%22%3A%7B%22phrase%22%3A%22Vi%E1%BB%87t+Nam%22%2C%22deepcategory%22%3A%5B%22Sinh+th%E1%BA%BF+k%E1%BB%B7+"+i+"+TCN%22%5D%7D%7D&ns0=1");
            try {
                String pageSrc = driver.getPageSource();
                Document doc = Jsoup.parse(pageSrc);
                Elements elements = doc.select(".mw-search-results li");
                for (Element element : elements) {
                    Element headingElement = element.select(".mw-search-result-heading").first();
                    if (headingElement != null) {
                        String title = headingElement.select("a").attr("title");

                        JSONObject keyword = new JSONObject();
                        keyword.put("name", title);
                        keywordArray.add(keyword);

                        System.out.println(title);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }


            keywords.put("Keywords", keywordArray);
            try (FileWriter file = new FileWriter("V5_List.json")) {
                file.write(keywords.toJSONString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public static void seleniumV4Edge(){
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        JSONObject keywords = new JSONObject();
        JSONArray keywordArray = new JSONArray();

        for(int i=1; i<=21; i++) {
            driver.get("https://vi.wikipedia.org/w/index.php?title=%C4%90%E1%BA%B7c_bi%E1%BB%87t:T%C3%ACm_ki%E1%BA%BFm&limit=500&offset=0&ns0=1&search=Vi%E1%BB%87t+Nam+deepcat%3A%22Sinh+th%E1%BA%BF+k%E1%BB%B7+"+i+"%22&advancedSearch-current={%22fields%22:{%22phrase%22:%22Vi%E1%BB%87t%20Nam%22,%22deepcategory%22:[%22Sinh%20th%E1%BA%BF%20k%E1%BB%B7%2013%22]}}");
            try {
                String pageSrc = driver.getPageSource();
                Document doc = Jsoup.parse(pageSrc);
                Elements elements = doc.select(".mw-search-results li");
                for (Element element : elements) {
                    Element headingElement = element.select(".mw-search-result-heading").first();
                    if (headingElement != null) {
                        String title = headingElement.select("a").attr("title");

                        JSONObject keyword = new JSONObject();
                        keyword.put("name", title);
                        keywordArray.add(keyword);

                        System.out.println(title);
                    }
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
                    keywords.put("Keywords", keywordArray);
            try (FileWriter file = new FileWriter("V4_List.json")) {
                file.write(keywords.toJSONString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public static String seleniumPackup(String url){
        String pageSrc = new String();
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get(url);
        try{
            String pgSrc = driver.getPageSource();
            Document doc = Jsoup.parse(pgSrc);
            Elements elements = doc.select("pre");
            pageSrc = elements.text();
            System.out.println(pageSrc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        driver.close();
        return pageSrc;
    }
    public static DefaultListModel<String> keywordsList() throws IOException, ParseException, JSONException {
        DefaultListModel<String> list = new DefaultListModel<>();
        try{
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("dList.json"));

            JSONArray keyList = (JSONArray) jsonObject.get("Keywords");
            for(Object keyword:keyList){
                JSONObject key = (JSONObject) keyword;
                String word = (String) key.get("name");
                list.addElement(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    public static DefaultListModel<String> keywordsListV1() throws IOException, ParseException, JSONException {
        DefaultListModel<String> list = new DefaultListModel<>();
        try{
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("V_List.json"));

            JSONArray keyList = (JSONArray) jsonObject.get("Keywords");
            for(Object keyword:keyList){
                JSONObject key = (JSONObject) keyword;
                String word = (String) key.get("name");
                list.addElement(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    public static DefaultListModel<String> keywordsListV2() throws IOException, ParseException, JSONException {
        DefaultListModel<String> list = new DefaultListModel<>();
        try{
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("V3_List.json"));

            JSONArray keyList = (JSONArray) jsonObject.get("Keywords");
            for(Object keyword:keyList){
                JSONObject key = (JSONObject) keyword;
                String word = (String) key.get("name");
                list.addElement(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static void main(String[] args) {
        seleniumV3Edge();
    }
}

