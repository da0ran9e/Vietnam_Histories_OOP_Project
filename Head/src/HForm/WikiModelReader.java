package HForm;

import info.bliki.wiki.model.AbstractWikiModel;
import info.bliki.wiki.model.InterWikiMap;
import info.bliki.wiki.model.WikiModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WikiModelReader {
    public static String wikimarkupReader(String fileName) throws IOException, ParseException {
        File filePath = new File("data//"+fileName+"_revisions.json");
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
        String wikiMarkup = (String) jsonObject.get(fileName);
        //System.out.println(wikiMarkup);

        // Create a WikiModel instance
        WikiModel wikiModel = new WikiModel("https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");

        // Transform the wiki markup to HTML
        String htmlContent = wikiModel.render(wikiMarkup);

        System.out.println(htmlContent);
        return htmlContent;
    }
    public static void main(String[] args) throws IOException, ParseException {
//        File filePath = new File("data//"+);
//        FileReader wikiMarkup = new FileReader()
//        System.out.println(wikiMarkup);
//
//        // Create a WikiModel instance
//        WikiModel wikiModel = new WikiModel("https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");
//
//        // Transform the wiki markup to HTML
//        String htmlContent = wikiModel.render(wikiMarkup);
//
//        System.out.println(htmlContent);
        JFrame app = new JFrame();
        app.setSize(new Dimension(500, 800));
        JLabel label = new JLabel("<html>"+wikimarkupReader("Nhạc Phi")+"</html>");
        JScrollPane scroll = new JScrollPane(label);
        app.add(scroll);
        app.setVisible(true);
    }
}
