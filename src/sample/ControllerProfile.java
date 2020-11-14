package sample;

import business.Validates;
import business.log.threads.ManageAudit;
import comuns.access.Audit;
import comuns.access.User;
import comuns.bases.Entity;
import dao.access.UserSqlServerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerProfile implements Initializable {
    @FXML
    ListView listDonations;

    @FXML
    ListView listAdoptions;

    @FXML
    Label txtName;

    @FXML
    Label txtEmail;

    @FXML
    Label txtDateBirth;

    UserSqlServerDAO userDAO = new UserSqlServerDAO();

    private ObservableList<String> items = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int id = 22;

        listDonations.setPrefHeight(195);
        items.add("TESTE");
        items.add("TESTE 2");
        listDonations.setItems(items);
        listDonations.setFixedCellSize(40);

        listAdoptions.setPrefHeight(195);
        listAdoptions.setItems(items);
        listAdoptions.setFixedCellSize(40);

        try {
            User user = Validates.validateId(id);
            txtName.setText(user.getName());
            txtEmail.setText(user.getEmail());
            if(user.getDateOfBirth() != null) {
                txtDateBirth.setText(user.getDateOfBirth().toString());
            }
            else {
                txtDateBirth.setText("01/01/1900");
            }


        } catch (SQLException | NullPointerException e) {

        }
    }
}
