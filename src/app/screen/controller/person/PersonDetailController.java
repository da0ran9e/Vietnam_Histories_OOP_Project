package app.screen.controller.person;

import app.history.dynasty.Dynasty;
import app.history.person.Person;
import app.history.storage.Storage;
import app.screen.controller.base.DetailBaseController;
import app.screen.controller.components.ContentController;
import app.screen.controller.dynasty.DynastyDetailController;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class PersonDetailController extends DetailBaseController {

    private final Person personData;
    @FXML
    private VBox mainContent;
    @FXML
    private VBox sideBar;
    private Button currentSideBarBtn;

    public PersonDetailController(Person personData) {
        this.personData = personData;
    }

    @FXML
    public void initialize() {
        initSideBar();
        initMainContent(this.personData);
        Storage.filteredPersons.addListener((ListChangeListener<Person>) c -> {
            initSideBar();
        });
    }

    public void initSideBar() {
        // clear old data
        sideBar.getChildren().clear();

        if (Storage.filteredPersons.isEmpty()) {
            Label emptyLabel = new Label();
            emptyLabel.getStyleClass().add("empty-label");
            emptyLabel.setText("Danh sách trống ><!");
            sideBar.getChildren().add(emptyLabel);
        }
        for (Person item : Storage.filteredPersons) {
            Button sideBarBtn = new Button();
            sideBarBtn.setText("> " + item.getName());
            sideBarBtn.getStyleClass().add("side-bar-btn");
            if (Objects.equals(item.getId(), personData.getId())) {
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
    public void initMainContent(Person currentPerson) {
        ImageView avatar = new ImageView();
        Image image = null;
        try {
            image = new Image(Objects.requireNonNull(getClass().getResource("/app/data/img/person/" + currentPerson.getId() + ".png")).openStream());
        } catch (Exception e) {
            image = null;
        }
        avatar.setImage(image);
        avatar.setFitWidth(400);
        avatar.setFitHeight(550);

        Label personName = new Label(currentPerson.getName());
        personName.getStyleClass().add("title");
        personName.setPadding(new Insets(20, 0, 20, 0));
        personName.setWrapText(true);
        personName.setStyle("-fx-text-fill: white");

        Label personGivenName = new Label("Tên gọi khác: " + currentPerson.getGivenName());
        personGivenName.getStyleClass().add("content");
        personGivenName.setPadding(new Insets(0, 0, 10, 0));
        personGivenName.setWrapText(true);
        personGivenName.setStyle("-fx-text-fill: white");

        Label personFather = new Label("Tên cha: " + currentPerson.getFather());
        personFather.getStyleClass().add("content");
        personFather.setPadding(new Insets(0, 0, 10, 0));
        personFather.setWrapText(true);
        personFather.setStyle("-fx-text-fill: white");

        Label personReign = new Label("Thời gian cai trị: " + currentPerson.getReign());
        personReign.getStyleClass().add("content");
        personReign.setPadding(new Insets(0, 0, 10, 0));
        personReign.setWrapText(true);
        personReign.setStyle("-fx-text-fill: white");

        Label personDateOfBirth = new Label("Sinh: " + currentPerson.getDateOfBirth());
        personDateOfBirth.getStyleClass().add("content");
        personDateOfBirth.setPadding(new Insets(0, 0, 10, 0));
        personDateOfBirth.setWrapText(true);
        personDateOfBirth.setStyle("-fx-text-fill: white");

        Label personDateOfDeath = new Label("Mất: " + currentPerson.getDateOfDeath());
        personDateOfDeath.getStyleClass().add("content");
        personDateOfDeath.setPadding(new Insets(0, 0, 10, 0));
        personDateOfDeath.setWrapText(true);
        personDateOfDeath.setStyle("-fx-text-fill: white");

        Text personDescription = new Text(currentPerson.getDescription());
        personDescription.getStyleClass().add("description");
        personDescription.setWrappingWidth(500);
        personDescription.setFill(Color.GRAY);
        System.out.println(currentPerson.getDescription());


        Label personDynasty = new Label();
        Dynasty dynasty = currentPerson.getDynasty();
        String text = "Triều Đại: ";
        if (dynasty != null) {
            text += currentPerson.getDynasty().getName();
            personDynasty.setOnMouseClicked(event -> {
                DynastyDetailController dynastyDetailController = new DynastyDetailController(dynasty);
                ContentController.goToDetail(dynastyDetailController);
            });
        } else {
            text += "Không rõ";
        }

        personDynasty.setText(text);
        personDynasty.getStyleClass().add("dynasty");
        personDynasty.setPadding(new Insets(10, 0, 0, 0));
        personDynasty.setWrapText(true);
        personDynasty.setStyle("-fx-text-fill: white");

        mainContent.getChildren().clear();
        mainContent.getChildren().addAll(
                personName,
                personGivenName,
                personFather,
                personReign,
                personDateOfBirth,
                personDateOfDeath,
                avatar,
                personDescription,
                personDynasty
        );
    }
}
