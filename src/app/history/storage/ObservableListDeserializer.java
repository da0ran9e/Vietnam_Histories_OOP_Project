package app.history.storage;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ObservableListDeserializer<T> implements JsonDeserializer<ObservableList> {

    public ObservableList deserialize(JsonElement json, Type typeOff, JsonDeserializationContext context)
            throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        ArrayList<T> list = context.deserialize(jsonArray, new TypeToken<ArrayList<T>>() {
        }.getType());
        return FXCollections.observableArrayList(list);
    }
}
