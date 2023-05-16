package HForm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONreader {
    public static void main(String[] args) throws JSONException {
        // JSON response from the Wikipedia API
        String jsonResponse = WikipediaAPIRequest.testDateAnalyze("Ho_Chi_Minh", 67377); // Replace with the actual JSON response

        // Parse the JSON response
        JSONObject response = new JSONObject(jsonResponse);
        JSONObject query = response.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");
        JSONObject pageID = pages.getJSONObject("67377");



        // Check if the page has an infobox section
        if (pageID.has("revisions")) {
            // Extract the infobox section from the pageID content
            String content = pageID.getString("revisions");
            String infobox = extractInfobox(content);
            // Parse the infobox JSON
            //JSONObject infoboxObject = new JSONObject(infobox);

            String infoboxTable = createInfoboxTable(infobox);
            System.out.println(infoboxTable);

        }
    }

    // Function to extract the infobox section from the page content
    private static String extractInfobox(String content) {
        int startIndex = content.indexOf("{{Infobox")+22;

        if (startIndex != -1) {
            // Find the end of the infobox section
            int endIndex = content.indexOf("'''{{", startIndex)-8;

            if (endIndex != -1) {
                // Extract the infobox section
                return content.substring(startIndex, endIndex + 2);
            }
        }

        return null;
    }
    private static String createInfoboxTable(String infobox) {
        StringBuilder tableBuilder = new StringBuilder();
        // Create the table header
        tableBuilder.append("<table>\n");
        tableBuilder.append("<thead>\n");
        tableBuilder.append("<tr><th colspan=\"2\">Infobox</th></tr>\n");
        tableBuilder.append("</thead>\n");
        tableBuilder.append("<tbody>\n");

//        System.out.println(infobox);

        String[] splitArray = infobox.split("\\\\n\\|");
        // Convert the array to a list
        List<String> resultList = Arrays.asList(splitArray);

        // Print the resulting list
        System.out.println(resultList.size());
        for (String line:resultList) {
            if (line!=null){
                String[] splitLine = line.split("\\s*=\\s*");
                tableBuilder.append("<tr>");
                List<String> tableRow = Arrays.asList(splitLine);
                for(String key:tableRow) {
                    tableBuilder.append("<td>"+key+"</td>");
                }
                tableBuilder.append("</tr>\n");
            }
        }
        return tableBuilder.toString();
    }
}
