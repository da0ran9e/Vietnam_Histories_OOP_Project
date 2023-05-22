package HForm;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;

public class Test{
    public static void main(String[] args) throws JSONException, IOException, ParseException {

    //String content = WikipediaAPIRequest.APIRevisionsDataRequest("Hồ Chí Minh");
       // System.out.println(WikipediaAPIRequest.APISearchRequest("Hồ Chí Minh"));
//        System.out.println("go");
//        SeleniumTest.seleniumEdge();
        DefaultListModel<String> keywordsList = SeleniumTest.keywordsList();
        for (int i = 0; i < keywordsList.getSize(); i++) {
            System.out.println(WikipediaAPIRequest.APIBestMatchedSearchRequest(keywordsList.getElementAt(i)));
        }
    }
}