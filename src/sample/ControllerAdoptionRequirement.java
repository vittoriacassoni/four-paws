package sample;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ControllerAdoptionRequirement implements Initializable {

    @FXML private Spinner gradeSpinner;
    @FXML private Spinner gradeSpinner1;
    @FXML private Spinner gradeSpinner2;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        SpinnerValueFactory<Integer> gradesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000,100);
        this.gradeSpinner.setValueFactory(gradesValueFactory);
        gradeSpinner.setEditable(true);

        SpinnerValueFactory<Integer> gradesValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000,100);
        this.gradeSpinner1.setValueFactory(gradesValueFactory1);
        gradeSpinner1.setEditable(true);

        SpinnerValueFactory<Integer> gradesValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000,100);
        this.gradeSpinner2.setValueFactory(gradesValueFactory2);
        gradeSpinner2.setEditable(true);

    }
}