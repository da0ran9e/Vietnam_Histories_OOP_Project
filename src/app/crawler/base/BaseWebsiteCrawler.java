package app.crawler.base;

public class BaseWebsiteCrawler {
    String url;
    String jsonStoreUrls;
    String imgStoreUrls;

    public BaseWebsiteCrawler(String url, String jsonStoreUrls, String imgStoreUrls) {
        this.url = url;
        this.imgStoreUrls = imgStoreUrls;
        this.jsonStoreUrls = jsonStoreUrls;
    }

    public BaseWebsiteCrawler(String url, String jsonStoreUrls) {
        this.url = url;
        this.jsonStoreUrls = jsonStoreUrls;
    }

    public String getUrl() {
        return url;
    }

    public String getJsonStoreUrls() {
        return jsonStoreUrls;
    }

    public String getImgStoreUrls() {
        return imgStoreUrls;
    }
}
