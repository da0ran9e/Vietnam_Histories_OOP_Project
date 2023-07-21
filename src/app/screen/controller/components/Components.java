package app.screen.controller.components;

import app.history.person.Person;
import app.screen.controller.person.PersonDetailController;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Components {

    /**
     * Hàm trả về 1 gridPane chứa danh sách nhân vật gồm 1 image và tên bên dưới
     */
    public static GridPane personList(List<Person> personObservableList) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        int row = 0;
        int column = 0;
        for (Person item : personObservableList) {
            VBox vBox = new VBox();
            vBox.setMinWidth(200);
            GridPane.setRowIndex(vBox, row);
            GridPane.setColumnIndex(vBox, column);

            Label kingName = new Label(item.getName());
            kingName.getStyleClass().add("king-name");
            kingName.setCursor(Cursor.HAND);

            kingName.setOnMouseClicked(e -> {
                PersonDetailController personDetailController = new PersonDetailController(item);
                ContentController.goToDetail(personDetailController);
            });

            ImageView avatar = new ImageView();
            Image image = null;
            try {
                image = new Image(Objects.requireNonNull(Components.class.getResource("/app/data/img/person/" + item.getId() + ".png")).openStream());
            } catch (Exception e) {
                image = null;
            }
            avatar.setImage(image);
            avatar.setFitWidth(150);
            avatar.setFitHeight(200);

            vBox.getChildren().addAll(avatar, kingName);
            gridPane.getChildren().add(vBox);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        return gridPane;
    }
}
