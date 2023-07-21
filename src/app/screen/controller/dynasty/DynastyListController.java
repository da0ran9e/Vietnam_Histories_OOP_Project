package app.screen.controller.dynasty;

import app.history.dynasty.Dynasty;
import app.history.storage.Storage;
import app.screen.controller.components.ContentController;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DynastyListController {
    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        initContent();
        Storage.filteredDynasties.addListener((ListChangeListener<Dynasty>) c -> {
            initContent();
        });
    }

    private void initContent() {
        // clear old data
        gridPane.getChildren().clear();

        if (Storage.filteredDynasties.isEmpty()) {
            Label emptyLabel = new Label();
            emptyLabel.getStyleClass().add("text-title");
            emptyLabel.setText("Không có kết quả nào ><!");
            gridPane.getChildren().add(emptyLabel);
        } else {
            int gridCol = 0;
            int gridRow = 0;
            for (Dynasty item : Storage.filteredDynasties) {
                VBox vBox = new VBox();
                vBox.setMinWidth(200);

                Label dynastyName = new Label(item.getName());
                dynastyName.getStyleClass().add("text-title");
                dynastyName.setCursor(Cursor.HAND);

                vBox.getChildren().addAll(dynastyName);

                //constraint grid pane col and row index
                GridPane.setColumnIndex(vBox, gridCol);
                GridPane.setRowIndex(vBox, gridRow);

                gridPane.getChildren().add(vBox);
                gridCol++;
                if (gridCol == 4) {
                    gridCol = 0;
                    gridRow++;
                }

                // xu ly su kien click
                dynastyName.setOnMouseClicked(e -> {
                    DynastyDetailController dynastyDetailController = new DynastyDetailController(item);
                    ContentController.goToDetail(dynastyDetailController);
                });
                dynastyName.setStyle("-fx-text-fill: gray");
            }
        }
    }
}
