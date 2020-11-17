package sample;

import business.Validates;
import business.log.threads.ManageAudit;
import comuns.access.Animal;
import comuns.access.Audit;
import dao.access.AnimalSqlServerDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerRegisterAnimal implements Initializable {

    @FXML ChoiceBox<String> dropBreeds, dropColors ;
    @FXML Circle imgAnimal;
    @FXML TextField txtName;
    @FXML Spinner spnAge, spnWeight, spnSize;

    AnimalSqlServerDAO animalDAO = new AnimalSqlServerDAO();

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


        SpinnerValueFactory<Double> sizeValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 2, 0);
        this.spnSize.setValueFactory(sizeValue);
        spnSize.setEditable(true);

        SpinnerValueFactory<Double> ageValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 13, 0);
        this.spnAge.setValueFactory(ageValue);
        spnAge.setEditable(true);

        SpinnerValueFactory<Double> weightValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 60, 0);
        this.spnWeight.setValueFactory(weightValue);
        spnWeight.setEditable(true);
    }
    public void rigisterPet(MouseEvent event) throws IOException {

        try{

            Validates.validateRequiredField(txtName.getText()) ;
            Validates.validateRequiredField(dropBreeds.getValue());
            Validates.validateRequiredField(dropColors.getValue());
            Validates.validateRequiredField(spnAge.getValueFactory().getValue().toString());
            Validates.validateDoubleNumber(spnSize.getValueFactory().getValue().toString());
            Validates.validateDoubleNumber(spnWeight.getValueFactory().getValue().toString());

            String breed = dropBreeds.getValue();
            String color = dropBreeds.getValue();
            String name =  txtName.getText();
            Double size = (Double) spnSize.getValueFactory().getValue();
            Double weight = (Double) spnWeight.getValueFactory().getValue();
            Integer age = (Integer) spnAge.getValueFactory().getValue();

            var animal = new Animal( name,breed, color, size,weight,age);

            if (animalDAO.insert(animal)) {
                ManageAudit.getInstance().activate();

                Audit audit = new Audit();
                //TODO - PEGAR ID DO USUARIO DA SESSÃO
                audit.setUserId(null);
                audit.setAction("Encontrar Pet");
                ManageAudit.getInstance().addAudit(audit);
                Thread.sleep(1000);

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                Parent root = FXMLLoader.load(getClass().getResource("ScreenRegisterAnimal.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Registrar Animal");
                primaryStage.setScene(new Scene(root, 1200, 700));
                primaryStage.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerForum.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
        }
    }
}