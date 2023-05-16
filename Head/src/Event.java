public class Event {
    public String eventName;
    public int eventYear;

    public Event(String eventName, int eventYear) {
        this.eventName = eventName;
        this.eventYear = eventYear;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventYear() {
        return eventYear;
    }

    public void setEventYear(int eventYear) {
        this.eventYear = eventYear;
    }
}
