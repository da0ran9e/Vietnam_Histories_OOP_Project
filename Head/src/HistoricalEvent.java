import java.util.List;

public class HistoricalEvent {
    public String name;
    public Time time;
    public Location locatiom;
    public String description;
    public String eventImpact;
    public List<String> relatedEvents;
    public List<String> relatedPersonalities;
    public List<String> relatedLocation;

    public HistoricalEvent(String name, Time time, Location locatiom, String description, String eventImpact, List<String> relatedEvents, List<String> relatedPersonalities, List<String> relatedLocation) {
        this.name = name;
        this.time = time;
        this.locatiom = locatiom;
        this.description = description;
        this.eventImpact = eventImpact;
        this.relatedEvents = relatedEvents;
        this.relatedPersonalities = relatedPersonalities;
        this.relatedLocation = relatedLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Location getLocatiom() {
        return locatiom;
    }

    public void setLocatiom(Location locatiom) {
        this.locatiom = locatiom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventImpact() {
        return eventImpact;
    }

    public void setEventImpact(String eventImpact) {
        this.eventImpact = eventImpact;
    }

    public List<String> getRelatedEvents() {
        return relatedEvents;
    }

    public void setRelatedEvents(List<String> relatedEvents) {
        this.relatedEvents = relatedEvents;
    }

    public List<String> getRelatedPersonalities() {
        return relatedPersonalities;
    }

    public void setRelatedPersonalities(List<String> relatedPersonalities) {
        this.relatedPersonalities = relatedPersonalities;
    }

    public List<String> getRelatedLocation() {
        return relatedLocation;
    }

    public void setRelatedLocation(List<String> relatedLocation) {
        this.relatedLocation = relatedLocation;
    }
}
