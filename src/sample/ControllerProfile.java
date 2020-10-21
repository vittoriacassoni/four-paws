package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerProfile implements Initializable {
    @FXML
    ListView listDonations;

    @FXML
    ListView listAdoptions;

    private ObservableList<String> items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listDonations.setPrefHeight(195);
        items.add("TESTE");
        items.add("TESTE 2");
        listDonations.setItems(items);
        listDonations.setFixedCellSize(40);

        listAdoptions.setPrefHeight(195);
        listAdoptions.setItems(items);
        listAdoptions.setFixedCellSize(40);
    }
}
