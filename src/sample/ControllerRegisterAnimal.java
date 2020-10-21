package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegisterAnimal implements Initializable {
    @FXML ChoiceBox<String> dropBreeds;
    @FXML ChoiceBox<String> dropColors;
    @FXML Circle imgAnimal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImagePattern pattern =  new ImagePattern(
                new Image("file:///C:/Users/Vitto/Documents/N2LP/four-paws/src/assets/images/Dog.png")
        );
        imgAnimal.setFill(pattern);

        dropBreeds.getItems().addAll("Poodle", "Maltês", "Golden", "Pug");
        dropBreeds.getSelectionModel().select(0);

        dropColors.getItems().addAll("Preto", "Branco", "Creme", "Caramelo");
        dropColors.getSelectionModel().select(0);
    }
}
