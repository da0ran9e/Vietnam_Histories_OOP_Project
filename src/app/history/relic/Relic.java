package app.history.relic;

import app.history.person.Person;
import app.history.storage.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Relic {
    public static int cnt = 0;
    int id;

    String title;
    String content;
    String address;
    ObservableList<Person> relatedHistoricalPerson = FXCollections.observableArrayList();
    List<String> nameList = new ArrayList<String>();
    String imgUrl;

    public Relic() {
    }

    public Relic(String title, String content, String address, List<String> nameList, String imgUrl) {
        this.id = ++cnt;
        this.title = title;
        this.content = content;
        this.address = address;
        this.nameList = nameList;
        this.imgUrl = imgUrl;
    }

    public static void resetId() {
        cnt = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Relic relic) {
			return relic.getTitle().equals(this.title);
        }
        return false;
    }

    public ObservableList<Person> getRelatedHistoricalPerson() {
        return relatedHistoricalPerson;
    }

    public void setRelatedHistoricalPerson(ObservableList<Person> relatedHistoricalPerson) {
        this.relatedHistoricalPerson = relatedHistoricalPerson;
    }

    public void addPerson() {
        for (String name : nameList) {
            Person person = new Person(name);
            int index = Storage.persons.indexOf(person);
            if (index != -1 && !relatedHistoricalPerson.contains(person)) {
                relatedHistoricalPerson.add(Storage.persons.get(index));
                System.out.println("success add " + name);
            } else {
                System.out.println("failed to add " + name);
            }
        }
    }

    public void addHistoricalPerson(Person person) {
        // check if person not exist in relatedHistoricalPerson. If not I will add
        if (!relatedHistoricalPerson.contains(person)) {
            relatedHistoricalPerson.add(person);
        } else
            System.out.print("This person has existed");
    }
}
