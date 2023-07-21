package app.history.event;

import app.history.person.Person;
import app.history.storage.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Event {

    public static int cnt = 0;
    ObservableList<Person> relativePersons = FXCollections.observableArrayList();
    private String name;
    private String time;
    private String destination;
    private String description;
    private List<String> relativePersonsName = new ArrayList<>();
    private String imgPath;
    private int id;

    public Event() {
    }

    public Event(String name, String time, String destination, String description, String imgPath, List<String> relativePersonsName) {
        this.id = cnt++;
        this.time = time;
        this.destination = destination;
        this.description = description;
        this.imgPath = imgPath;
        this.relativePersonsName = relativePersonsName;
        this.name = name;
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

    public String getImgPath() {
        return imgPath;
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

    public List<Person> getRelativePersons() {
        return relativePersons;
    }

    public void setRelativePersons(List<String> relativePersonsName) {
        this.relativePersonsName = relativePersonsName;
    }

    public int getId() {
        return id;
    }

    public void addPerson() {
        for (String name : relativePersonsName) {
            Person person = new Person(name);
            int index = Storage.persons.indexOf(person);
            if (index != -1 && !relativePersons.contains(person)) {
                relativePersons.add(Storage.persons.get(index));
                System.out.println("Them thanh cong " + name);
            } else {
                System.out.println("Khong thanh cong " + name);
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Event t) {
            return this.getName().equals(t.getName());
        }
        return false;
    }
}
