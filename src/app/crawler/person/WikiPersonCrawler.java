package app.crawler.person;

import app.history.person.Person;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class WikiPersonCrawler extends PersonCrawler {

    private Document getWikiDoc(String name) {
        String url = "https://vi.wikipedia.org/wiki/" + name;
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            return null;
        }
    }

    private String getData(Document doc, String query) {
        try {
            return doc.select(query).first().nextElementSibling().text();
        } catch (Exception e) {
            return "Không rõ";
        }
    }

    public boolean checkImageExists(String destinationFile) {
        File file = new File(destinationFile);
        return file.exists();
    }

    public void crawl() {
        for (int i = 0; i < PersonCrawler.list.size(); i++) {
            Person person = PersonCrawler.list.get(i);
            Document docWiki = getWikiDoc(person.getName());

            if (person.getGivenName().equals("Không rõ")) {
                person.setGivenName(getData(docWiki, "th:contains(Húy)"));
            }

            if (person.getDateOfBirth().equals("Không rõ") || person.getDateOfBirth().equals("?")) {
                person.setDateOfBirth(getData(docWiki, "th:contains(Sinh)"));
            }

            if (person.getDateOfDeath().equals("Không rõ") || person.getDateOfDeath().equals("?")) {
                person.setDateOfDeath(getData(docWiki, "th:contains(Mất)"));
            }

            if (person.getFather().equals("Không rõ")) {
                person.setFather(getData(docWiki, "th:contains(Cha)"));
            }

            if (person.getReign().equals("Không rõ")) {
                person.setReign(getData(docWiki, "th:containsOwn(Trị vì), th:containsOwn(Tại vị)"));
            }

            if (!checkImageExists("src/app/data/img/person/" + person.getId() + ".png")) {
                try {
                    String src = docWiki.select("table.infobox td > span > a > img").first().attr("src");
                    saveImg("https:" + src, getImgStoreUrls() + person.getId() + ".png");
                } catch (Exception e) {
                }
            }
        }
    }
}
