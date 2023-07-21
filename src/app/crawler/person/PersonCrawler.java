package app.crawler.person;

import app.crawler.base.BaseWebsiteCrawler;
import app.crawler.base.ICrawler;
import app.history.person.Person;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PersonCrawler extends BaseWebsiteCrawler implements ICrawler {
    public static List<Person> list = new ArrayList<>();

    public PersonCrawler() {
        super("https://nguoikesu.com/nhan-vat", "src/app/data/json/person.json",
                "src/app/data/img/person/");
    }

    public static void main(String[] args) {
        try {
            new PersonCrawler().crawl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The crawl() method is used to scrape information about Vietnamese kings from
     * the website
     * and store it in a list of Person objects.
     *
     * @throws IOException if there is an error connecting to the website
     */
    public void crawl() throws IOException {
        list.clear();
        new NguoikesuPersonCrawler().crawl();
        new WikiPersonCrawler().crawl();
        new GooglePersonCrawler().crawl();
        store();
    }

    public void store() {
        Gson gson = new Gson();
        try (FileWriter file = new FileWriter(getJsonStoreUrls())) {
            file.write(gson.toJson(list));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImg(String imageUrl, String destinationFile) {
        try {
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();

            BufferedImage img = ImageIO.read(is);
            if (img.getWidth() < 150 || img.getHeight() < 150) {
                System.out.println("Image too small, skipping...");
                return;
            }

            is.close();
            File outputfile = new File(destinationFile);
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
