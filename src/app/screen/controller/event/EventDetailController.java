package app.screen.controller.event;

import app.history.event.Event;
import app.history.storage.Storage;
import app.screen.controller.base.DetailBaseController;
import app.screen.controller.components.Components;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class EventDetailController extends DetailBaseController {
    private final Event eventData;
    @FXML
    private VBox mainContent;
    @FXML
    private VBox sideBar;
    private Button currentSideBarBtn;

    public EventDetailController(Event eventData) {
        this.eventData = eventData;
    }

    @FXML
    public void initialize() {
        initSideBar();
        initMainContent(this.eventData);
        Storage.filteredEvents.addListener((ListChangeListener<Event>) c -> {
            initSideBar();
        });
    }

    public void initSideBar() {
        // clear old data
        sideBar.getChildren().clear();

        if (Storage.filteredEvents.isEmpty()) {
            Label emptyLabel = new Label();
            emptyLabel.getStyleClass().add("empty-label");
            emptyLabel.setText("Danh sách trống ><!");
            sideBar.getChildren().add(emptyLabel);
        }
        for (Event item : Storage.filteredEvents) {
            Button sideBarBtn = new Button();
            sideBarBtn.setText("> " + item.getName());
            sideBarBtn.getStyleClass().add("side-bar-btn");
            if (Objects.equals(item.getId(), eventData.getId())) {
                currentSideBarBtn = sideBarBtn;
                sideBarBtn.getStyleClass().add("current-content-btn");
            }

            sideBarBtn.setOnAction(event -> {
                currentSideBarBtn.getStyleClass().remove("current-content-btn");
                sideBarBtn.getStyleClass().add("current-content-btn");
                currentSideBarBtn = sideBarBtn;
                initMainContent(item);
            });

            sideBar.getChildren().add(sideBarBtn);
        }
    }

    //CREATE UI FROM DATA
    public void initMainContent(Event eventData) {
        ImageView eventImage = new ImageView();
        Image image = null;
        try {
            image = new Image(Objects.requireNonNull(getClass().getResource("/app/data/img/event/" + eventData.getImgPath())).openStream());
        } catch (Exception e) {
            image = null;
        }
        eventImage.setImage(image);
        eventImage.setFitWidth(400);
        eventImage.setFitHeight(550);

        Label eventName = new Label(eventData.getName());
        eventName.getStyleClass().add("title");
        eventName.setPadding(new Insets(20, 0, 20, 0));
        eventName.setWrapText(true);
        eventName.setStyle("-fx-text-fill: gray");

        Label eventTime = new Label("Thời gian: " + eventData.getTime());
        eventTime.getStyleClass().add("content");
        eventTime.setPadding(new Insets(0, 0, 10, 0));
        eventTime.setWrapText(true);
        eventTime.setStyle("-fx-text-fill: gray");

        Label eventDestination = new Label("Địa điểm: " + eventData.getDestination());
        eventDestination.getStyleClass().add("content");
        eventDestination.setPadding(new Insets(0, 0, 10, 0));
        eventDestination.setWrapText(true);
        eventDestination.setStyle("-fx-text-fill: gray");

        Label eventDescription = new Label("Thông tin chi tiết: " + eventData.getDescription());
        eventDescription.getStyleClass().add("content");
        eventDescription.setPadding(new Insets(0, 0, 10, 0));
        eventDescription.setWrapText(true);
        eventDescription.setStyle("-fx-text-fill: gray");

        GridPane personList = Components.personList(eventData.getRelativePersons());

        mainContent.getChildren().clear();
        mainContent.getChildren().addAll(
                eventName,
                eventTime,
                eventDestination,
                eventImage,
                eventDescription,
                personList
        );
    }
}