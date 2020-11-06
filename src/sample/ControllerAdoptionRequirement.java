package sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.Validates;
import business.log.threads.ManageAudit;
import comuns.access.AdoptionRequirement;
import comuns.access.Audit;
import dao.access.AdoptionRequirementSqlServerDAO;
import dao.access.ForumTopicSqlServerDAO;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class ControllerAdoptionRequirement implements Initializable {

    @FXML
    Spinner<Double> spnMaxExpense, spnRequiredSpace, spnAgePreference;

    @FXML
    CheckBox ckAngry, ckHappy, ckNeedy, ckCaring, ckQuiet;


    AdoptionRequirementSqlServerDAO AdoptionRequirementDAO = new AdoptionRequirementSqlServerDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Double> maxExpenseValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0);
        this.spnMaxExpense.setValueFactory(maxExpenseValue);
        spnMaxExpense.setEditable(true);

        SpinnerValueFactory<Double> requiredSpaceValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0);
        this.spnRequiredSpace.setValueFactory(requiredSpaceValue);
        spnRequiredSpace.setEditable(true);

        SpinnerValueFactory<Double> agePreferenceValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0);
        this.spnAgePreference.setValueFactory(agePreferenceValue);
        spnAgePreference.setEditable(true);
    }

    public void foundPet(MouseEvent event) throws IOException {
        try {
            Validates.validateDoubleNumber(spnMaxExpense.getValueFactory().getValue().toString());
            Validates.validateDoubleNumber(spnRequiredSpace.getValueFactory().getValue().toString());
            Validates.validateDoubleNumber(spnAgePreference.getValueFactory().getValue().toString());

            var adoptionRequirement = new AdoptionRequirement(ckAngry.isSelected(), ckHappy.isSelected(),
                    ckNeedy.isSelected(), ckCaring.isSelected(), ckQuiet.isSelected(),
                    (Double) spnMaxExpense.getValueFactory().getValue(), (Double) spnRequiredSpace.getValueFactory().getValue(),
                    (Double) spnAgePreference.getValueFactory().getValue());

            if (AdoptionRequirementDAO.insert(adoptionRequirement)) {
                ManageAudit.getInstance().activate();

                Audit audit = new Audit();
                //TODO - PEGAR ID DO USUARIO DA SESSÃO
                audit.setUserId(null);
                audit.setAction("Encontrar Pet");
                ManageAudit.getInstance().addAudit(audit);
                Thread.sleep(1000);

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

                Parent root = FXMLLoader.load(getClass().getResource("ScreenChooseAnimal.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Escolher Animal");
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