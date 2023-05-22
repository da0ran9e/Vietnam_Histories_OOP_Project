package HForm;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SearchNameJSON {

    private static void SaveJSON() throws JSONException, IOException, ParseException {

        DefaultListModel<String> keywordsList = SeleniumTest.keywordsList();
        for (int i = 0; i < keywordsList.getSize(); i++) {
            File filePath = new File("data//"+SeleniumTest.sanitizeFileName(Wikipedia_Crawler.Space2Underscores(WikipediaAPIRequest.removeDiacritics(keywordsList.getElementAt(i).toString())))+"_revisions.json");
            if (filePath.exists()) System.out.println(filePath+" is already exist!");
            else {
                JSONObject result = new JSONObject();
                String keyword = (String) keywordsList.getElementAt(i);
                try{
                String foundedValues = WikipediaAPIRequest.APIRevisionsDataRequest(Wikipedia_Crawler.Space2Underscores(WikipediaAPIRequest.removeDiacritics(keyword)));
                if(foundedValues==null) continue;
                result.put(keywordsList.getElementAt(i).toString(),foundedValues);

                try(FileWriter file = new FileWriter(filePath)) {
                    file.write(result.toJSONString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                } catch (RuntimeException e) {
                    continue;
                }
            }

        }

    }

    public static void main(String[] args) throws JSONException, IOException, ParseException {
        System.out.println(1);
        SaveJSON();
    }
}
