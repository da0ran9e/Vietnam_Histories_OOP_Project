package HForm;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.luaj.vm2.ast.Str;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParameterCount {
    public static void FirstCheck(){
        File file = new File("CustomParameters//Objects.json");
        JSONParser parser = new JSONParser();

        try{
            JSONObject objects = (JSONObject) parser.parse(new FileReader(file));
            JSONObject artifact = (JSONObject) objects.get("artifact");
	        JSONObject human = (JSONObject) objects.get("human");
            JSONObject customer = (JSONObject) objects.get("customer");
            JSONObject location = (JSONObject) objects.get("location");
            JSONObject figure = (JSONObject) objects.get("figure");
            JSONObject policy = (JSONObject) objects.get("policy");
            JSONObject temple = (JSONObject) objects.get("temple");
            JSONObject war = (JSONObject) objects.get("war");
            JSONObject group = (JSONObject) objects.get("group");
            JSONObject art = (JSONObject) objects.get("art");
            JSONObject park = (JSONObject) objects.get("park");
            JSONObject politicalEntity = (JSONObject) objects.get("political entity");
            JSONObject touristLocation = (JSONObject) objects.get("tourist location");
            JSONObject holiday = (JSONObject) objects.get("holiday");
            JSONObject historicalLocation = (JSONObject) objects.get("historical location");
            JSONObject dynasty = (JSONObject) objects.get("dynasty");
            JSONObject building = (JSONObject) objects.get("building");

            List<String> keyList = new ArrayList<>();
            for(Object object:objects.keySet()){
                JSONObject obj = (JSONObject) objects.get(object);
                for(Object key:obj.keySet()){
                    String keyString = key.toString();
                    if(!keyList.contains(keyString)){
                        keyList.add(keyString);
                    }else {
                        System.out.println("\""+keyString+"\": \"\",");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("EOF");
        } catch (ParseException e) {
            System.out.println("ParseException");
        }

    }

    public static void sortingByParameters() {
        File file = new File("CustomParameters//Objects.json");
        JSONParser parser = new JSONParser();

        try {
            JSONObject objects = (JSONObject) parser.parse(new FileReader(file));

            for (Object object : objects.keySet()) {
                String objectName = object.toString();

                File newFolder = new File("CustomParameters/SortByPara//" + objectName);
                if (!newFolder.exists()) {
                    boolean created = newFolder.mkdir();
                }
                JSONObject newFileObj = new JSONObject();

                JSONObject obj = (JSONObject) objects.get(object);
                List<String> keyList = new ArrayList<>();

                for (Object key : obj.keySet()) {
                    String keyString = key.toString();
                    keyList.add(keyString);
                }

                File dataFile = new File("Prefiltered_templates_data2f.json");
                JSONObject dataObjs = (JSONObject) parser.parse(new FileReader(dataFile));
                for(Object dataObj:dataObjs.keySet()){
                    String dataName = dataObj.toString();
                    JSONObject dataObjName = (JSONObject) dataObjs.get(dataName);
                    label:for(Object data: dataObjName.keySet()){
                        String dataId = data.toString();
                        JSONObject parameters = (JSONObject) dataObjName.get(dataId);
                        for(Object parameter:parameters.keySet()){
                            String parameterString = parameter.toString();
                            if(keyList.contains(parameterString)) {
                                JSONObject newObj = new JSONObject();
                                newObj.put(dataName, dataObjName);
                                newFileObj.put(dataName, dataObjName);
                                System.out.println(parameterString);
                                break label;
                            }
                        }
                    }
                }
                File newFile = new File("CustomParameters/SortByPara/" + objectName+"//"+objectName+".json");
                System.out.println(newFile.getAbsolutePath());
                try(FileWriter fileWriter = new FileWriter(newFile)) {
                    fileWriter.write(newFileObj.toJSONString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        FirstCheck();
        sortingByParameters();
    }
}
