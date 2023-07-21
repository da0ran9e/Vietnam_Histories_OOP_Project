package app.screen.controller.event;

import app.history.event.Event;
import app.history.storage.Storage;
import app.screen.controller.components.ContentController;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class EventListController {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox paginationContainer;

    @FXML
    public void initialize() {
        initContent();
        Storage.filteredEvents.addListener((ListChangeListener<Event>) c -> {
            initContent();
        });
    }

    // CREATE UI FROM DATA
    private void initContent() {
        // clear old data
        paginationContainer.getChildren().clear();

        if (Storage.filteredEvents.isEmpty()) {
            Label emptyLabel = new Label();
            emptyLabel.getStyleClass().add("text-title");
            emptyLabel.setText("Không có kết quả nào ><!");
            gridPane.getChildren().clear();
            gridPane.getChildren().add(emptyLabel);
            paginationContainer.getChildren().add(gridPane);
        } else {
            //Create pagination
            Pagination pagination = new Pagination();
            pagination.setPageCount(Storage.filteredEvents.size() / 12 + 1);
            pagination.setCurrentPageIndex(0);
            pagination.setMaxPageIndicatorCount(5);

            pagination.setPageFactory((pageIndex) -> {
                gridPane.getChildren().clear();

                int gridCol = 0;
                int gridRow = 0;

                int startItemIndex = 12 * pagination.getCurrentPageIndex();
                int endItemIndex = startItemIndex + 12;
                if (endItemIndex > Storage.filteredEvents.size()) endItemIndex = Storage.filteredEvents.size();

                for (Event item : Storage.filteredEvents.subList(startItemIndex, endItemIndex)) {
                    VBox vBox = new VBox();
                    vBox.setMinWidth(200);

                    Label eventName = new Label(item.getName());
                    eventName.getStyleClass().add("text-title");
                    eventName.setCursor(Cursor.HAND);
                    eventName.setMaxWidth(200);
                    eventName.setWrapText(true);
                    eventName.setStyle("-fx-text-fill: gray");

                    ImageView eventImage = new ImageView();
                    Image image = null;
                    try {
                        image = new Image(Objects.requireNonNull(getClass().getResource("/app/data/img/event/" + item.getImgPath())).openStream());
                    } catch (Exception e) {
                        image = null;
                    }
                    eventImage.setImage(image);
                    eventImage.setFitWidth(200);
                    eventImage.setFitHeight(250);

                    Text eventTime = new Text(item.getTime());
                    eventTime.setWrappingWidth(200);
                    eventTime.getStyleClass().add("text-description");

                    vBox.getChildren().addAll(eventImage, eventName, eventTime);
                    vBox.setMaxWidth(200);

                    //constrait grid pane col and row index
                    GridPane.setColumnIndex(vBox, gridCol);
                    GridPane.setRowIndex(vBox, gridRow);

                    gridPane.getChildren().add(vBox);
                    gridCol++;
                    if (gridCol == 4) {
                        gridCol = 0;
                        gridRow++;
                    }

                    // xu ly su kien click
                    eventName.setOnMouseClicked(e -> {
                        EventDetailController eventDetailController = new EventDetailController(item);
                        ContentController.goToDetail(eventDetailController);
                    });
                }
                return new VBox(gridPane);
            });
            VBox paginationVBox = new VBox(pagination);
            paginationContainer.getChildren().add(paginationVBox);
        }
    }
}