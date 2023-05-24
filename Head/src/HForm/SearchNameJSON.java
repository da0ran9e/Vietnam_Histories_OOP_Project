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
            File filePath = new File("data_categories//"+SeleniumTest.sanitizeFileName(WikipediaAPIRequest.APIBestMatchedSearchRequest(keywordsList.getElementAt(i)).toString())+"_categories.json");
            if (filePath.exists()) System.out.println(filePath+" is already exist!");
            else {
                JSONObject result = new JSONObject();

                String keyword = (String) keywordsList.getElementAt(i);
                try{
                JSONArray foundedValues = WikipediaAPIRequest.APICategoriesDataRequest(WikipediaAPIRequest.APIBestMatchedSearchRequest(Wikipedia_Crawler.Space2Underscores(WikipediaAPIRequest.removeDiacritics(keyword))));
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
        private static void SaveJSONV1() throws JSONException, IOException, ParseException {

        DefaultListModel<String> keywordsList = SeleniumTest.keywordsListV1();
        for (int i = 0; i < keywordsList.getSize(); i++) {
            File filePath = new File("data//"+SeleniumTest.sanitizeFileName(keywordsList.getElementAt(i)).toString()+"_revisions.json");
            System.out.println(filePath);
            if (filePath.exists()) System.out.println(filePath+" is already exist!");
            else {
                JSONObject result = new JSONObject();

                String keyword = (String) keywordsList.getElementAt(i);
                try{
                String foundedValues = WikipediaAPIRequest.APIRevisionsDataRequestFinal(keyword);//WikipediaAPIRequest.APIRevisionsDataRequestV2(keyword);
                if(foundedValues.length()<100)
                {
                         foundedValues = WikipediaAPIRequest.APIRevisionsDataRequestV1(keyword);
                         if(foundedValues.length()<100){
                             foundedValues = WikipediaAPIRequest.APIRevisionsDataRequestFinal(keyword);
                             if(foundedValues.length()<100){
                                foundedValues = WikipediaAPIRequest.APIRevisionsDataRequestFinal(Wikipedia_Crawler.Space2Underscores(keyword));
                                     if(foundedValues.length()<100){
                                         foundedValues = WikipediaAPIRequest.APIRevisionsDataRequest(WikipediaAPIRequest.APIBestMatchedSearchRequest(Wikipedia_Crawler.Space2Underscores(WikipediaAPIRequest.removeDiacritics(keyword))));
                                     }
                            }
                        }
                }
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

    public static void SaveJSONV2() throws JSONException, IOException, ParseException {

        DefaultListModel<String> keywordsList = SeleniumTest.keywordsListV2();
        for (int i = 0; i < keywordsList.getSize(); i++) {
            File filePath = new File("data//"+SeleniumTest.sanitizeFileName(keywordsList.getElementAt(i)).toString()+"_revisions.json");
            System.out.println(filePath);
            if (filePath.exists()) System.out.println(filePath+" is already exist!");
            else {
                JSONObject result = new JSONObject();

                String keyword = (String) keywordsList.getElementAt(i);
                try{
                String foundedValues = WikipediaAPIRequest.APIRevisionsDataRequestFinal(keyword);//WikipediaAPIRequest.APIRevisionsDataRequestV2(keyword);
                if(foundedValues.length()<100)
                {
                         foundedValues = WikipediaAPIRequest.APIRevisionsDataRequestV1(keyword);
                         if(foundedValues.length()<100){
                             foundedValues = WikipediaAPIRequest.APIRevisionsDataRequestFinal(keyword);
                             if(foundedValues.length()<100){
                                foundedValues = WikipediaAPIRequest.APIRevisionsDataRequestFinal(Wikipedia_Crawler.Space2Underscores(keyword));
                                     if(foundedValues.length()<100){
                                         foundedValues = WikipediaAPIRequest.APIRevisionsDataRequest(WikipediaAPIRequest.APIBestMatchedSearchRequest(Wikipedia_Crawler.Space2Underscores(WikipediaAPIRequest.removeDiacritics(keyword))));
                                     }
                            }
                        }
                }
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
        SaveJSONV1();
    }
}
