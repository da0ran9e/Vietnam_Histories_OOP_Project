package app.screen.controller.components;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Transition {
    public static Stage rootStage;

    public static void setRootStage(Stage rootStage) {
        Transition.rootStage = rootStage;
    }

    public static void startFadeTransition(BorderPane rootPane, String nextPaneSrc) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent nextPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(nextPaneSrc)));
                    Scene newScene = new Scene(nextPane);
                    Stage currentStage = (Stage) rootPane.getScene().getWindow();

                    rootStage.setScene(newScene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        fadeTransition.play();
    }

    public static void endFadeTransition(BorderPane rootPane) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}
