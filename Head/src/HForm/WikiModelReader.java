package HForm;

import info.bliki.wiki.model.WikiModel;

import java.io.IOException;

public class WikiModelReader {
    public static void main(String[] args) throws IOException {
        String wikiMarkup = WikipediaAPIRequest.APIRevisionsDataRequest("Hồ Chí Minh");
        System.out.println(Wikipedia_Crawler.Space2Underscores(WikipediaAPIRequest.convertVi2En(WikipediaAPIRequest.removeDiacritics(wikiMarkup))));

        // Create a WikiModel instance
        WikiModel wikiModel = new WikiModel("https://en.wikipedia.org/wiki/${image}", "https://en.wikipedia.org/wiki/${title}");

        // Transform the wiki markup to HTML
        String htmlContent = wikiModel.render(wikiMarkup);

        System.out.println(htmlContent);
    }
}
