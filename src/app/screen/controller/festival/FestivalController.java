package app.screen.controller.festival;

import app.history.festival.Festival;
import app.history.storage.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FestivalController {

    @FXML
    private TableView<Festival> tblView;

    @FXML
    private TableColumn<Festival, String> descriptionCol;

    @FXML
    private TableColumn<Festival, String> destinationCol;

    @FXML
    private TableColumn<Festival, String> nameCol;

    @FXML
    private TableColumn<Festival, String> timeCol;

    @FXML
    private void initialize() {
        timeCol.setCellValueFactory(
                new PropertyValueFactory<Festival, String>("time")
        );

        nameCol.setCellValueFactory(
                new PropertyValueFactory<Festival, String>("name")
        );

        destinationCol.setCellValueFactory(
                new PropertyValueFactory<Festival, String>("destination")
        );

        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<Festival, String>("description")
        );

        tblView.setItems(Storage.festivals);
    }

}
