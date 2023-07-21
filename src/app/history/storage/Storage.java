package app.history.storage;

import app.crawler.base.ICrawler;
import app.crawler.dynasty.DynastyCrawler;
import app.crawler.event.EventCrawler;
import app.crawler.festival.FestivalCrawler;
import app.crawler.person.PersonCrawler;
import app.crawler.relic.RelicCrawler;
import app.history.dynasty.Dynasty;
import app.history.event.Event;
import app.history.festival.Festival;
import app.history.person.Person;
import app.history.relic.Relic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Storage<T> {
    public static ObservableList<Person> persons = FXCollections.observableArrayList();
    public static ObservableList<Relic> relics = FXCollections.observableArrayList();
    public static ObservableList<Event> events = FXCollections.observableArrayList();
    public static ObservableList<Festival> festivals = FXCollections.observableArrayList();
    public static ObservableList<Dynasty> dynasties = FXCollections.observableArrayList();
    public static FilteredList<Person> filteredPersons = new FilteredList<>(persons, p -> true);
    public static FilteredList<Relic> filteredRelics = new FilteredList<>(relics, p -> true);
    public static FilteredList<Event> filteredEvents = new FilteredList<>(events, p -> true);
    public static FilteredList<Festival> filteredFestivals = new FilteredList<>(festivals, p -> true);
    public static FilteredList<Dynasty> filteredDynasties = new FilteredList<>(dynasties, p -> true);

    public static void searchPerson(String value) {
        filteredPersons.setPredicate(person -> {
            String newValue = value.toLowerCase();
            return person.getName().toLowerCase().contains(newValue);
        });
    }

    public static void searchRelic(String value) {
        filteredRelics.setPredicate(relic -> {
            String newValue = value.toLowerCase();
            return relic.getTitle().toLowerCase().contains(newValue);
        });
    }

    public static void searchEvent(String value) {
        filteredEvents.setPredicate(event -> {
            String newValue = value.toLowerCase();
            return event.getName().toLowerCase().contains(newValue);
        });
    }

    public static void searchFestival(String value) {
        filteredFestivals.setPredicate(festival -> {
            String newValue = value.toLowerCase();
            return festival.getName().toLowerCase().contains(newValue);
        });
    }

    public static void searchDynasty(String value) {
        filteredDynasties.setPredicate(dynasty -> {
            String newValue = value.toLowerCase();
            return dynasty.getName().toLowerCase().contains(newValue);
        });
    }

    public static void crawl() throws IOException {
        // Person.resetId();
        // Relic.resetId();
        // Event.resetId();
        // Festival.resetId();
        Dynasty.resetId();

        // new PersonCrawler().crawl();
        // new RelicCrawler().crawl();
        // new EventCrawler().crawl();
        // new FestivalCrawler().crawl();
        new DynastyCrawler().crawl();
    }

    public static void init() throws IOException {
        File directoryDynasty = new File("src/app/data/json/dynasty.json");
        File directoryPerson = new File("src/app/data/json/person.json");
        File directoryRelic = new File("src/app/data/json/relic.json");
        File directoryFestival = new File("src/app/data/json/festival.json");
        File directoryEvent = new File("src/app/data/json/event.json");

        if (!directoryDynasty.exists()) {
            ICrawler dynastyCrawler = new DynastyCrawler();
            dynastyCrawler.crawl();
        }

        if (!directoryPerson.exists()) {
            ICrawler personCrawler = new PersonCrawler();
            personCrawler.crawl();
        }
        if (!directoryRelic.exists()) {
            ICrawler relicCrawler = new RelicCrawler();
            relicCrawler.crawl();
        }
        if (!directoryFestival.exists()) {
            ICrawler festivalCrawler = new FestivalCrawler();
            festivalCrawler.crawl();
        }
        if (!directoryEvent.exists()) {
            ICrawler eventCrawler = new EventCrawler();
            eventCrawler.crawl();
        }

        dynasties = readFromFile("src/app/data/json/dynasty.json", Dynasty[].class);
        persons = readFromFile("src/app/data/json/person.json", Person[].class);
        events = readFromFile("src/app/data/json/event.json", Event[].class);
        relics = readFromFile("src/app/data/json/relic.json", Relic[].class);
        festivals = readFromFile("src/app/data/json/festival.json", Festival[].class);

        for (int i = 0; i < dynasties.size(); i++) {
            dynasties.get(i).addKing();
        }
        for (int i = 0; i < persons.size(); i++) {
            persons.get(i).setDynasty();
        }
        for (int i = 0; i < events.size(); i++) {
            events.get(i).addPerson();
        }
        for (int i = 0; i < relics.size(); i++) {
            relics.get(i).addPerson();
        }

        filteredDynasties = new FilteredList<>(dynasties, dynasty -> true);
        filteredEvents = new FilteredList<>(events, event -> true);
        filteredFestivals = new FilteredList<>(festivals, festival -> true);
        filteredPersons = new FilteredList<>(persons, person -> true);
        filteredRelics = new FilteredList<>(relics, relic -> true);
    }

    public static <T> ObservableList<T> readFromFile(String fileName, Class<T[]> clazz) {
        FileReader reader;
        try {
            reader = new FileReader(fileName);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(ObservableList.class, new ObservableListDeserializer<T>());
            Gson gson = gsonBuilder.create();
            T[] arr = gson.fromJson(reader, clazz);
            return FXCollections.observableArrayList(arr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
