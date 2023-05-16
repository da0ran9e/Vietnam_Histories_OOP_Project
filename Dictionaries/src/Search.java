import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Search {

    public static String Space2Underscores(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ') {
                sb.append('-');
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }


          public static List<String> Go(String word) {
        List<String> data = new ArrayList<String>(50);
        try {
            String url = "https://dictionary.cambridge.org/vi/dictionary/english/"+Space2Underscores(word);

            Document document = Jsoup.connect(url).get();

            Elements title = document.getElementsByClass("di-title");
            Elements dprons = document.getElementsByClass("pron dpron");
            Elements def = document.getElementsByClass("def ddef_d db");

            data.add(title.text());
            for (Element dpron:dprons){
                data.add(dpron.text());
            }
            data.add(def.text());
            System.out.println(data);


            return data;
            } catch(Exception e){
                e.printStackTrace();
            }
        return data;
        }
}
