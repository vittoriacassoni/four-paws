package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAnimalRegister implements Initializable {
    @FXML ChoiceBox<String> dropBreeds;
    @FXML ChoiceBox<String> dropColors;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropBreeds.getItems().addAll("Poodle", "Maltês", "Golden", "Pug");
        dropBreeds.getSelectionModel().select(0);

        dropColors.getItems().addAll("Preto", "Branco", "Creme", "Caramelo");
        dropColors.getSelectionModel().select(0);
    }
}
