package HForm;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sweble.wikitext.engine.*;
import org.sweble.wikitext.engine.config.WikiConfig;
import org.sweble.wikitext.engine.nodes.EngProcessedPage;
import org.sweble.wikitext.engine.utils.DefaultConfigEnWp;
import org.sweble.wikitext.parser.ParserConfig;
import org.sweble.wikitext.parser.WikitextParser;
import org.sweble.wikitext.parser.parser.LinkTargetException;
import org.w3c.dom.Node;

import javax.swing.*;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import static HForm.SeleniumTest.keywordsListV3;

public class WikiModelTemplatesExtract {
     public static String Underscore2Space(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '_') {
                sb.append(' ');
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
    public static List<String> extractSubstrings(String input) {
        List<String> substrings = new ArrayList<>();

        int startIndex = input.indexOf("{{");
        if (startIndex != -1) {
            int extractBeginIndex = startIndex;
            int extractEndIndex = startIndex;
            do{
                extractBeginIndex = input.indexOf("{{",extractBeginIndex+2);
                extractEndIndex = input.indexOf("}}", extractEndIndex+2);
                if(extractBeginIndex>extractEndIndex){
                    substrings.add(input.substring(startIndex,extractEndIndex+2));
                    startIndex = extractBeginIndex;
                } else if (extractBeginIndex==-1) {
                    substrings.add(input.substring(startIndex,extractEndIndex+2));
                    break;
                }
            }while(extractBeginIndex!=-1||extractEndIndex!=-1);
        }
        return substrings;
    }

    public static List<String> extractSubstringsV1(String input) {
        List<String> substrings = new ArrayList<>();
        List<Integer> beginIndexes = new ArrayList<>(1000);
        List<Integer> endIndexes = new ArrayList<>(1000);

        int startIndex = -2;
        int endIndex = -2;
        while (startIndex!=-1) {
            startIndex = input.indexOf("{{", startIndex+2);
            endIndex = input.indexOf("}}", endIndex+2);
            if (startIndex != -1) {
                beginIndexes.add(startIndex);
                //System.out.println(startIndex);
            }
            if (endIndex != -1) {
                endIndexes.add(endIndex);
                //System.out.println(endIndex);
            }
        }
        System.out.println(beginIndexes.size() + " " + endIndexes.size());
//        for (int i = 0; i < endIndexes.size(); i++) {
//            for (int j = 0; j < beginIndexes.size(); j++) {
//                if (endIndexes.get(i) < beginIndexes.get(j)) {
//                    //System.out.println(beginIndexes.get(j-1) + " " +endIndexes.get(i)+2);
//                    //System.out.println(beginIndexes.get(j-1) + " " + endIndexes.get(i) + " "
//                    //        + input.substring(beginIndexes.get(j-1),  endIndexes.get(i)+2));
//                    substrings.add(input.substring(beginIndexes.get(j-1),  endIndexes.get(i)+2));
//                    endIndexes.remove(i);
//                    beginIndexes.remove(j-1);
//                    break;
//                }
//            }
//        }
        return substrings;
    }

    public static List<String> extractSubstringsV2(String input) {
        StringBuilder tb = new StringBuilder();
        List<String> substrings = new ArrayList<>();
        Stack<Integer> startIndexes = new Stack<>();

        tb.append(' ');
        tb.append(input);
        tb.append(' ');
        input = tb.toString();

        for (int i=1; i<input.length()-1; i++) {
            if (input.charAt(i-1) != '{' && input.charAt(i) == '{' && input.charAt(i+1) == '{' && input.charAt(i+2) != '}') {
                startIndexes.push(i);
            }
            if (input.charAt(i-1) != '}'&& input.charAt(i) == '}' && input.charAt(i+1) == '}' && input.charAt(i+2) != '}') {
                try{
                    int startIndex = startIndexes.pop();
                    substrings.add(input.substring(startIndex, i+2));
                } catch (Exception e) {
                    continue;
                }
            }
        }


        return substrings;
    }

    public static String removeUnnecessarySpaces(String input) {
        input = input.trim();
        input = input.replaceAll("\\s+", " ");

        return input;
    }

    public static Map<String, String> parseInputString(String input) {
        Map<String, String> resultMap = new HashMap<>(100);
        String[] lines = input.split("\\n\\|");
        for (String line : lines) {
            //System.out.println(line);
            if (line.contains("=")) {
                //line = line.substring(1).trim();
                String key = removeUnnecessarySpaces(line.substring(0, line.indexOf("=")));
                String value = removeUnnecessarySpaces(line.substring(line.indexOf("=")+1));

                resultMap.put(key, value);
                //System.out.println(key +" -> "+ value );
            }
        }

        return resultMap;
    }

    public static void filtering() throws IOException, ParseException {
        File folder = new File("Category_data");
        if(folder.exists()&&folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));
                String pageId = (String) jsonObject.keySet().iterator().next();
                String pageContent = jsonObject.get(pageId).toString();

                List<String> templates = extractSubstringsV2(pageContent);
                JSONObject resultJson = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                for (String template : templates) {
                    //jsonArray.put(template);
                }
                resultJson.put(pageId, jsonArray);

                File resultFile = new File("Templates_data//" + file.getName()+".json");
                System.out.println(resultFile.getAbsolutePath());
                try(FileWriter fileWriter = new FileWriter(resultFile)) {
                    fileWriter.write(resultJson.toJSONString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
public static void filteringV1() throws IOException, ParseException, JSONException {
        File folder = new File("Category_data");
        List<String> files = new ArrayList<>();
        if(folder.exists()&&folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                String name = file.getName();
                files.add(name);
                //System.out.println(name);
            }
        }
        DefaultListModel<String> list = keywordsListV3();
        for(int i=0; i< list.getSize(); i++){
            String name = Underscore2Space(URLDecoder.decode(list.getElementAt(i), "UTF-8")+".json");
            //System.out.println(name);
            if(files.contains(name)){
                files.remove(name);
            }
        }
        System.out.println(files.size());
        for(String file : files) {
            System.out.println(file);
        }
    }
    public static void filteringV2() throws IOException, ParseException {
        File folder = new File("Templates_data");
        if(folder.exists()&&folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));
                String pageId = (String) jsonObject.keySet().iterator().next();
                JSONArray templates = (JSONArray) jsonObject.get(pageId);

                JSONObject resultJson = new JSONObject();
                JSONObject templateJson = new JSONObject();
                for (Object template : templates) {
                    String temp = template.toString();
                    if(temp.contains("|")){
                        JSONArray jsonArray = new JSONArray();
                        Map<String, String> map = parseInputString(temp);
                        String tempName = temp.substring(temp.indexOf("{{")+2, temp.indexOf("|"));
                        System.out.println(tempName);
                        JSONObject json = new JSONObject();
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            json.put(entry.getKey(), entry.getValue());
                        }
                        jsonArray.add(json);
                        templateJson.put(tempName, jsonArray);
                    }
                }
                resultJson.put(pageId, templateJson);


                File resultFile = new File("Prefiltered_templates_data//" + file.getName());
                //System.out.println(resultFile.getAbsolutePath());
                try(FileWriter fileWriter = new FileWriter(resultFile)) {
                    fileWriter.write(resultJson.toJSONString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    public static void main(String[] args) {
        String input = "";
        //List<String> substrings = extractSubstrings(input);
        List<Map<String, String>> mappedTemplates = new ArrayList<>();
//        for (String substring : substrings) {
//            System.out.println(substring);
//        }
        //System.out.println(input);
//        System.out.println(extractSubstringsV2(input).size());
        try{
            filteringV2();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
