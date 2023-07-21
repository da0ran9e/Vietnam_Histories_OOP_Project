package app.history.festival;

public class Festival {

    public static int cnt = 0;
    private String name;
    private String time;
    private String destination;
    private String description;
    private int id;

    public Festival() {
    }

    public Festival(String name, String time, String destination, String description) {
        this.name = name;
        this.time = time;
        this.destination = destination;
        this.description = description;
        this.id = cnt++;
    }

    public static void resetId() {
        cnt = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Festival t) {
            return this.getName().equals(t.getName());
        }
        return false;
    }
}
