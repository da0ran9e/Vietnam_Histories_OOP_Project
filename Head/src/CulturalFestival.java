public class CulturalFestival {
    public String name;
    public String location;
    public Time time;
    public String duration;
    public String organizer;
    public String description;
    public String significance;
    public String clothing;
    public String art;
    public String rituals;
    public String historyLinks;

    public CulturalFestival(String name, String location, Time time, String duration, String organizer, String description, String significance, String clothing, String art, String rituals, String historyLinks) {
        this.name = name;
        this.location = location;
        this.time = time;
        this.duration = duration;
        this.organizer = organizer;
        this.description = description;
        this.significance = significance;
        this.clothing = clothing;
        this.art = art;
        this.rituals = rituals;
        this.historyLinks = historyLinks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSignificance() {
        return significance;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }

    public String getClothing() {
        return clothing;
    }

    public void setClothing(String clothing) {
        this.clothing = clothing;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getRituals() {
        return rituals;
    }

    public void setRituals(String rituals) {
        this.rituals = rituals;
    }

    public String getHistoryLinks() {
        return historyLinks;
    }

    public void setHistoryLinks(String historyLinks) {
        this.historyLinks = historyLinks;
    }
}
