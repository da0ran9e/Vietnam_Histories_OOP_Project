package app.screen.controller;

import app.screen.controller.components.ContentController;
import app.screen.controller.components.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class LayoutController {

    @FXML
    private StackPane contentArea;

    @FXML
    private Button dynastyBtn;

    @FXML
    private BorderPane layoutPane;

    @FXML
    private Button eventBtn;

    @FXML
    private Button festivalBtn;

    @FXML
    private Button personBtn;

    @FXML
    private Button relicBtn;

    @FXML
    private TextField tfSearch;

    @FXML
    void handleBackToHome(ActionEvent event) {
        Transition.startFadeTransition(layoutPane, "/app/screen/fxml/home.fxml");
    }

    @FXML
    void handleDynastyBtnClicked(ActionEvent event) {
        ContentController.goToDynasty();
    }

    @FXML
    void handleEventBtnClicked(ActionEvent event) {
        ContentController.goToEvent();
    }

    @FXML
    void handleFestivalBtnClicked(ActionEvent event) {
        ContentController.goToFestival();
    }

    @FXML
    void handlePersonBtnClicked(ActionEvent event) {
        ContentController.goToPerson();
    }

    @FXML
    void handleRelicBtnClicked(ActionEvent event) {
        ContentController.goToRelic();
    }

    @FXML
    public void initialize() {
        layoutPane.setOpacity(0);
        Transition.endFadeTransition(layoutPane);

        ContentController.setContentArea(contentArea);
        ContentController.setDynastyBtn(dynastyBtn);
        ContentController.setEventBtn(eventBtn);
        ContentController.setFestivalBtn(festivalBtn);
        ContentController.setPersonBtn(personBtn);
        ContentController.setRelicBtn(relicBtn);
        ContentController.setTfSearch(tfSearch);

        ContentController.goToDynasty();

        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableVa, String oldValue, String newValue) {
                ContentController.handleSearch(newValue);
            }
        });
    }
}
