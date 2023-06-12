package HForm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.luaj.vm2.ast.Str;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.Stack;

public class CategoriesLoader {
    public static void FoldersLoader() throws IOException, ParseException {

        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        File folder = new File("Pages");
        if(folder.exists()&&folder.isDirectory()){
            File[] files = folder.listFiles(); // Get an array of all files in the folder

            if (files != null) {
                // Iterate over the files and print their names
                for (File file : files) {
                    if (file.isFile()) {
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

                        String fileName = file.getName();
                        String categoryName = fileName.substring(9, fileName.length()-5);
                        JSONArray pagesList = (JSONArray) jsonObject.get(categoryName);

                        Stack<String> pageStack = new Stack<>();
                        Stack<String> linkStack = new Stack<>();
                        for(Object page:pagesList){
                            JSONObject name = (JSONObject) page;
                            String word = (String) name.get("page");
                            if (word!=null) pageStack.push(word);
                            String link = (String) name.get("link");
                            if (link!=null) linkStack.push(link);
                        }

                        label:while(!pageStack.empty()&&!linkStack.empty()) {
                            String pageTitle = pageStack.pop();
                            String pageLink = linkStack.pop();

                            if (pageTitle.contains(":") || pageTitle.contains("\\")) continue label;

                            File pageSrcFile = new File("PagesSrc//" + pageTitle + ".json");
                            if(!pageSrcFile.exists()) continue label;
                            System.out.println("loading: " + pageTitle + "\n" + pageLink);
                            if (pageSrcFile.exists()) {
                                System.out.println("The file " + pageTitle + ".json is already exist!");
                                continue label;
                            }
                            try {
                                driver.get(pageLink);
                                String pageSrc = driver.getPageSource();
                                Document doc = Jsoup.parse(pageSrc);

                                JSONObject content = new JSONObject();
                                content.put(pageTitle, doc.text());

                                try (FileWriter fileWriter = new FileWriter(pageSrcFile)) {
                                    fileWriter.write(content.toJSONString());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } catch (RuntimeException e) {
                                throw new RuntimeException(e);
                            }
                        }


                    }
                }
            }
        }
    }
    public static void FoldersLoaderV1() throws IOException, ParseException {

        JSONObject listFile = new JSONObject();
        JSONArray listPg = new JSONArray();

        File folder = new File("Pages");
        if(folder.exists()&&folder.isDirectory()){
            File[] files = folder.listFiles(); // Get an array of all files in the folder

            if (files != null) {
                // Iterate over the files and print their names
                for (File file : files) {
                    if (file.isFile()) {
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

                        String fileName = file.getName();
                        String categoryName = fileName.substring(9, fileName.length()-5);
                        JSONArray pagesList = (JSONArray) jsonObject.get(categoryName);

                        Stack<String> pageStack = new Stack<>();
                        Stack<String> linkStack = new Stack<>();
                        for(Object page:pagesList){
                            JSONObject name = (JSONObject) page;
                            String word = (String) name.get("page");
                            if (word!=null) pageStack.push(word);
                            String link = (String) name.get("link");
                            if (link!=null) linkStack.push(link);
                        }

                        while(!pageStack.empty()&&!linkStack.empty()) {
                            String pageTitle = pageStack.pop();
                            String pageLink = linkStack.pop();

                            JSONObject obj = new JSONObject();
                            obj.put(pageTitle, pageLink);
                            listPg.add(obj);
                        }
                    }
                }
            }
        }
        listFile.put("pages", listPg);
        try (FileWriter fileWriter = new FileWriter("Pages.json")) {
                                    fileWriter.write(listFile.toJSONString());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
    }
    public static void FoldersLoaderV2() throws IOException, ParseException {

        JSONObject listFile = new JSONObject();
        JSONArray listPg = new JSONArray();

        File folder = new File("Pages");
        if(folder.exists()&&folder.isDirectory()){
            File[] files = folder.listFiles(); // Get an array of all files in the folder

            if (files != null) {
                // Iterate over the files and print their names
                for (File file : files) {
                    if (file.isFile()) {
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

                        String fileName = file.getName();
                        String categoryName = fileName.substring(9, fileName.length()-5);
                        JSONArray pagesList = (JSONArray) jsonObject.get(categoryName);

                        Stack<String> pageStack = new Stack<>();
                        Stack<String> linkStack = new Stack<>();
                        for(Object page:pagesList){
                            JSONObject name = (JSONObject) page;
                            String word = (String) name.get("page");
                            if (word!=null) pageStack.push(word);
                            String link = (String) name.get("link");
                            if (link!=null) linkStack.push(link);
                        }

                        while(!pageStack.empty()&&!linkStack.empty()) {
                            String pageTitle = pageStack.pop();
                            String pageLink = linkStack.pop();

                            JSONObject obj = new JSONObject();
                            obj.put("name", pageTitle);
                            listPg.add(obj);
                        }
                    }
                }
            }
        }
        listFile.put("pages", listPg);
        try (FileWriter fileWriter = new FileWriter("PagesName.json")) {
                                    fileWriter.write(listFile.toJSONString());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
    }
    public static void FoldersLoaderV3() throws IOException, ParseException {
        int count = 0;
        JSONObject listFile = new JSONObject();
        JSONArray listPg = new JSONArray();

        File folder = new File("Pages");
        if(folder.exists()&&folder.isDirectory()){
            File[] files = folder.listFiles(); // Get an array of all files in the folder

            if (files != null) {
                // Iterate over the files and print their names
                for (File file : files) {
                    if (file.isFile()) {
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

                        String fileName = file.getName();
                        String categoryName = fileName.substring(9, fileName.length()-5);
                        JSONArray pagesList = (JSONArray) jsonObject.get(categoryName);

                        Stack<String> pageStack = new Stack<>();
                        Stack<String> linkStack = new Stack<>();
                        for(Object page:pagesList){
                            JSONObject name = (JSONObject) page;
                            String word = (String) name.get("page");
                            if (word!=null) pageStack.push(word);
                            String link = (String) name.get("link");
                            if (link!=null) linkStack.push(link);
                        }

                        while(!pageStack.empty()&&!linkStack.empty()) {
                            String pageTitle = pageStack.pop();
                            String pageLink = linkStack.pop();

                            JSONObject obj = new JSONObject();
                            if(pageTitle.contains(":")||pageTitle.contains("\\")||pageTitle.contains("/")||
                                    pageTitle.contains("truyền hình")||pageTitle.contains("băng nhạc")||
                                    pageTitle.contains("diễn viên")||pageTitle.contains("Giải")||
                                    pageTitle.contains("Đội tuyển")||pageTitle.contains("Truyền hình")||
                                    pageTitle.contains("thực phẩm")||pageTitle.contains("nhóm nhạc")||
                                    pageTitle.contains("2020")||pageTitle.contains("2021")||
                                    pageTitle.contains("2022")||pageTitle.contains("2023")||
                                    pageTitle.contains("Rap")||pageTitle.contains("Vụ án")||
                                    pageTitle.contains("ca sĩ")|| pageTitle.length()==0)
                                continue;
                            obj.put("name", pageLink.substring(30));
                            if(listPg.contains(obj))continue;
                            listPg.add(obj);
                            count++;
                            System.out.println("Total "+count+" objects added!");
                        }
                    }
                }
            }
        }
        listFile.put("pages", listPg);
        try (FileWriter fileWriter = new FileWriter("PagesName.json")) {
                                    fileWriter.write(listFile.toJSONString());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
    }
    public static void FoldersLoaderV4() throws IOException, ParseException {

        JSONObject listFile = new JSONObject();
        JSONArray listPg = new JSONArray();
        DefaultListModel<String> list = new DefaultListModel<>();
        DefaultListModel<String> linksList = new DefaultListModel<>();
        try{
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("PagesName.json"));

            JSONArray keyList = (JSONArray) jsonObject.get("pages");
            for(Object keyword:keyList){
                JSONObject key = (JSONObject) keyword;
                String word = (String) key.get("name");
                list.addElement(word);
            }
            jsonObject = (JSONObject) parser.parse(new FileReader("Pages.json"));

            JSONObject pageList = (JSONObject) jsonObject.get("pages");
            for (int i = 0; i < list.getSize(); i++) {
                String word = (String) pageList.get(list.getElementAt(i));
                linksList.addElement(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

            if (!list.isEmpty()) {
                // Iterate over the files and print their names
                for (int i = 0; i < linksList.getSize(); i++) {
                    System.out.println(linksList.getElementAt(i));
                }
            }

//        listFile.put("pages", listPg);
//        try (FileWriter fileWriter = new FileWriter("Pages.json")) {
//                                    fileWriter.write(listFile.toJSONString());
//                                } catch (IOException e) {
//                                    throw new RuntimeException(e);
//                                }
    }

    public static void FoldersLoaderV5() throws IOException, ParseException {
        JSONObject listFile = new JSONObject();

        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("PagesLink.json"));

            JSONArray keyList = (JSONArray) jsonObject.get("pages");
            for (Object keyword : keyList) {
                JSONObject key = (JSONObject) keyword;
                String link = (String) key.get("name");

                driver.get(link);
                String pageSrc = driver.getPageSource();
                Document doc = Jsoup.parse(pageSrc);
                String title = doc.select(".mw-page-title-main").text();
                String pgSrc = doc.text();

                listFile.put(title, pgSrc);
                listFile.put("pages", listFile);

                File path = new File("pageData//"+title+".json");
                try (FileWriter fileWriter = new FileWriter(path)) {
                    fileWriter.write(listFile.toJSONString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void FoldersLoaderV6() throws IOException, ParseException {
        int count = 0;
        JSONObject listFile = new JSONObject();
        JSONArray listPg = new JSONArray();

        File folder = new File("Pages");
        if(folder.exists()&&folder.isDirectory()){
            File[] files = folder.listFiles(); // Get an array of all files in the folder

            if (files != null) {
                // Iterate over the files and print their names
                for (File file : files) {
                    if (file.isFile()) {
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

                        String fileName = file.getName();
                        String categoryName = fileName.substring(9, fileName.length()-5);
                        JSONArray pagesList = (JSONArray) jsonObject.get(categoryName);

                        Stack<String> pageStack = new Stack<>();
                        Stack<String> linkStack = new Stack<>();
                        for(Object page:pagesList){
                            JSONObject name = (JSONObject) page;
                            String word = (String) name.get("page");
                            if (word!=null) pageStack.push(word);
                            String link = (String) name.get("link");
                            if (link!=null) linkStack.push(link);
                        }

                        while(!pageStack.empty()&&!linkStack.empty()) {
                            String pageTitle = pageStack.pop();
                            String pageLink = linkStack.pop();

                            JSONObject obj = new JSONObject();
                            if(pageTitle.contains("Bản mẫu")){

                            obj.put("name", pageLink.substring(30));
                            if(listPg.contains(obj))continue;
                            listPg.add(obj);
                            count++;
                            System.out.println("Total "+count+" objects added!");
                            }
                        }
                    }
                }
            }
        }
        listFile.put("pages", listPg);
        try (FileWriter fileWriter = new FileWriter("PagesTemplates.json")) {
                                    fileWriter.write(listFile.toJSONString());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
    }


    public static void main(String[] args) throws IOException, ParseException {
        FoldersLoaderV6();
    }

}
