package HForm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Pattern;

public class WikipediaAPIRequest {
    public static List<String> prop = List.of(
            "categories", //List all categories the pages belong to
            "extlinks", //Returns all external URLs (not interwikis) from the given pages.
            "extracts", //Returns plain-text or limited HTML extracts of the given pages.
            "info", //Get basic page information.
            "iwlinks",//Returns all interwiki links from the given pages.
            "links", //Returns all links from the given pages.
            "linkshere", //Find all pages that link to the given pages.
            "pageimages", //Returns information about images on the page, such as thumbnail and presence of photos
            "pageprops", //Get various page properties defined in the page content.
            "redirects", //Returns all redirects to the given pages.
            "transcludedin", //Find all pages that transclude the given pages.
            "mapdata" //Request all Kartographer map data for the given pages
    );
    public static List<String> list = List.of(
            "search",
            "backlinks",  //Find all pages that link to the given page.
            //Eg: api.php ? action=query & list=backlinks & format=json & bltitle=Ho%20Chi%20Minh
            "categorymembers", //List all pages in a given category.
            "imageusage", //Find all pages that use the given image title.
            "iwbacklinks" //Find all pages that link to the given interwiki link.
    );
    public static List<String> generator = List.of(
            "allcategories" //Enumerate all categories
    );

    public static String removeDiacritics(String text) {
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String removedSignsText = pattern.matcher(normalizedText).replaceAll("");
        return removedSignsText;
    }

    public static void APISearchRequest(String query) {
        String apiUrl = "https://en.wikipedia.org/w/api.php?action=query&list=search&prop=info&format=json&srsearch=" + Wikipedia_Crawler.Space2Underscores(removeDiacritics(query));
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Print the response
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response.toString());
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject results = (JSONObject) jsonObject.get("query");
                JSONArray rows = (JSONArray) results.get("search");
                for (Object row : rows) {
                    JSONObject result = (JSONObject) row;
                    long pageID = (long) result.get("pageid");
                    String title = (String) result.get("title");
                    String snippet = (String) result.get("snippet");
                    System.out.println(pageID + " : " + title + " : " + snippet);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void APIImageRequest(String title, long pageid) {
        String apiUrl = "https://en.wikipedia.org/w/api.php?action=query&prop=pageimages&format=json&piprop=original&titles=" + Wikipedia_Crawler.Space2Underscores(removeDiacritics(title)) + "&origin=*";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Print the response
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response.toString());
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject results = (JSONObject) jsonObject.get("query");
                JSONObject pages = (JSONObject) results.get("pages");
                JSONObject pageId = (JSONObject) pages.get("" + pageid);
                JSONObject original = (JSONObject) pageId.get("original");
                String src = (String) original.get("source");
                long width = (long) original.get("width");
                long height = (long) original.get("height");
                System.out.println(src + " : " + width + "x" + height);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void APIExtractsDataRequest(String title, long pageid) {
        String apiUrl = "https://en.wikipedia.org/w/api.php?action=query&prop=extracts&rvprop=content&format=json&exintro=&titles=" + Wikipedia_Crawler.Space2Underscores(removeDiacritics(title));
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                // Print the response
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response.toString());
                JSONObject jsonObject = (JSONObject) obj;

                JSONObject results = (JSONObject) jsonObject.get("query");
                JSONObject pages = (JSONObject) results.get("pages");
                JSONObject pageId = (JSONObject) pages.get("" + pageid);
                String extract = (String) pageId.get("extract");
                System.out.println(extract.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void APIRevisionsDataRequest(String title, long pageid) {
        String apiUrl = "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&rvprop=content&format=json&titles=" + Wikipedia_Crawler.Space2Underscores(removeDiacritics(title));
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                // Print the response
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(response.toString());
                JSONObject jsonObject = (JSONObject) obj;

                JSONObject results = (JSONObject) jsonObject.get("query");
                JSONObject pages = (JSONObject) results.get("pages");
                JSONObject pageId = (JSONObject) pages.get("" + pageid);
                JSONArray revisions = (JSONArray) pageId.get("revisions");
                for (Object row : revisions) {
                    JSONObject revision = (JSONObject) row;
                    String mainContent = (String) revision.get("*");
                    System.out.println(mainContent);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String testDateAnalyze(String title, long pageid) {
        String apiUrl = "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&rvprop=content&format=json&titles=" + Wikipedia_Crawler.Space2Underscores(removeDiacritics(title));
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            }
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
