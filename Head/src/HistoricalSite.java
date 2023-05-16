import java.util.List;

public class HistoricalSite {
    public String name;
    public String address;
    public GeographicalLocation location;
    public String description;
    public String history;
    public String activities;
    public String price;
    public String operatingTime;
    public List<String> images;
    public List<String> videos;

    public HistoricalSite(String name, String address, GeographicalLocation location, String description, String history, String activities, String price, String operatingTime, List<String> images, List<String> videos) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.description = description;
        this.history = history;
        this.activities = activities;
        this.price = price;
        this.operatingTime = operatingTime;
        this.images = images;
        this.videos = videos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeographicalLocation getLocation() {
        return location;
    }

    public void setLocation(GeographicalLocation location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(String operatingTime) {
        this.operatingTime = operatingTime;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }
}
