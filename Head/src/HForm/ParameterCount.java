package HForm;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.luaj.vm2.ast.Str;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParameterCount {
    public static void FirstCheck(){
        File file = new File("CustomeParameters//Objects.json");
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

    public static void main(String[] args) {
        FirstCheck();
    }
}
