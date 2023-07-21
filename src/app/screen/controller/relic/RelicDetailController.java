package app.screen.controller.relic;

import app.history.relic.Relic;
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

public class RelicDetailController extends DetailBaseController {
    private final Relic relicData;
    @FXML
    private VBox mainContent;
    @FXML
    private VBox sideBar;
    private Button currentSideBarBtn;

    public RelicDetailController(Relic relicData) {
        this.relicData = relicData;
    }

    @FXML
    public void initialize() {
        initSideBar();
        initMainContent(this.relicData);
        Storage.filteredRelics.addListener((ListChangeListener<Relic>) c -> {
            initSideBar();
        });
    }

    public void initSideBar() {
        // clear old data
        sideBar.getChildren().clear();

        if (Storage.filteredRelics.isEmpty()) {
            Label emptyLabel = new Label();
            emptyLabel.getStyleClass().add("empty-label");
            emptyLabel.setText("Danh sách trống ><!");
            sideBar.getChildren().add(emptyLabel);
        }
        for (Relic item : Storage.filteredRelics) {
            Button sideBarBtn = new Button();
            sideBarBtn.setText("> " + item.getTitle());
            sideBarBtn.getStyleClass().add("side-bar-btn");
            if (Objects.equals(item.getId(), relicData.getId())) {
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

    public void initMainContent(Relic relicData) {
        //CREATE UI FROM DATA
        ImageView relicImage = new ImageView();
        Image image = null;
        try {
            image = new Image(Objects.requireNonNull(getClass().getResource("/app/data/img/relic/" + relicData.getImgUrl())).openStream());
        } catch (Exception e) {
            image = null;
        }
        relicImage.setImage(image);
        relicImage.setFitWidth(400);
        relicImage.setFitHeight(550);

        Label relicTitle = new Label(relicData.getTitle());
        relicTitle.getStyleClass().add("title");
        relicTitle.setPadding(new Insets(20, 0, 20, 0));
        relicTitle.setWrapText(true);
        relicTitle.setStyle("-fx-text-fill: gray");

        Label relicContent = new Label(relicData.getContent());
        relicContent.getStyleClass().add("content");
        relicContent.setPadding(new Insets(0, 0, 10, 0));
        relicContent.setWrapText(true);
        relicContent.setStyle("-fx-text-fill: gray");

        Label relicDestination = new Label("Địa điểm: " + relicData.getAddress());
        relicDestination.getStyleClass().add("content");
        relicDestination.setPadding(new Insets(0, 0, 10, 0));
        relicDestination.setWrapText(true);
        relicDestination.setStyle("-fx-text-fill: gray");

        GridPane personList = Components.personList(relicData.getRelatedHistoricalPerson());

        mainContent.getChildren().clear();
        mainContent.getChildren().addAll(
                relicTitle,
                relicDestination,
                relicImage,
                relicContent,
                personList
        );
    }
}