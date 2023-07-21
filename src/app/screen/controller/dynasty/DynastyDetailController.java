package app.screen.controller.dynasty;

import app.history.dynasty.Dynasty;
import app.history.storage.Storage;
import app.screen.controller.base.DetailBaseController;
import app.screen.controller.components.Components;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class DynastyDetailController extends DetailBaseController {

    private final Dynasty dynastyData;
    @FXML
    private VBox mainContent;
    @FXML
    private VBox sideBar;
    private Button currentSideBarBtn;

    public DynastyDetailController(Dynasty dynastyData) {
        this.dynastyData = dynastyData;
    }

    @FXML
    public void initialize() {
        initSideBar();
        initMainContent(this.dynastyData);
        Storage.filteredDynasties.addListener((ListChangeListener<Dynasty>) c -> {
            initSideBar();
        });
    }

    public void initSideBar() {
        // clear old data
        sideBar.getChildren().clear();

        if (Storage.filteredDynasties.isEmpty()) {
            Label emptyLabel = new Label();
            emptyLabel.getStyleClass().add("empty-label");
            emptyLabel.setText("Danh sách trống ><!");
            sideBar.getChildren().add(emptyLabel);
        }
        for (Dynasty item : Storage.filteredDynasties) {
            Button sideBarBtn = new Button();
            sideBarBtn.setText("> " + item.getName());
            sideBarBtn.getStyleClass().add("side-bar-btn");
            if (Objects.equals(item.getId(), dynastyData.getId())) {
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

    public void initMainContent(Dynasty dynastyData) {
        Label dynastyName = new Label(dynastyData.getName());
        dynastyName.getStyleClass().add("title");
        dynastyName.setPadding(new Insets(10, 0, 10, 0));
        dynastyName.setWrapText(true);

        Label exitedTime = new Label(dynastyData.getExitedTime());
        exitedTime.getStyleClass().add("sub-title");
        exitedTime.setPadding(new Insets(0, 0, 10, 0));
        exitedTime.setWrapText(true);

        Label capital = new Label(dynastyData.getCapital());
        capital.getStyleClass().add("description");
        capital.setPadding(new Insets(0, 0, 10, 0));
        capital.setWrapText(true);

        GridPane personList = Components.personList(dynastyData.getKing());

        mainContent.getChildren().clear();
        mainContent.getChildren().addAll(
                dynastyName,
                exitedTime,
                capital,
                personList
        );
    }
}
