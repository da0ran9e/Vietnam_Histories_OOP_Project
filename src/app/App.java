package app;

import app.screen.controller.components.Transition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/app/screen/fxml/home.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Lich su Vietnam");
        stage.setResizable(false);
        stage.setScene(scene);
        Transition.setRootStage(stage);
        stage.show();
    }
}
