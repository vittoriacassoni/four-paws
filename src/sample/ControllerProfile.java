package sample;

import business.Validates;
import business.log.threads.ManageAudit;
import business.services.UserService;
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
import javafx.scene.layout.Pane;
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
    Pane paneEdit;

    @FXML
    Pane paneDarkBackground;

    @FXML
    ListView listDonations;

    @FXML
    ListView listAdoptions;

    @FXML
    Label txtName;

    @FXML
    Label txtEmail;

    @FXML
    Label txtBirthday;

    @FXML
    Label txtPassword;

    @FXML
    TextField txtNameEdit;

    @FXML
    TextField txtEmailEdit;

    @FXML
    TextField txtPasswordEdit;

    UserSqlServerDAO userDAO = new UserSqlServerDAO();
    UserService userService = new UserService();
    int id = 22;


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

        try {
            User user = userService.validateId(id);

            txtName.setText(user.getName());
            txtNameEdit.setText(user.getName());

            txtEmail.setText(user.getEmail());
            txtEmailEdit.setText(user.getEmail());

            txtPasswordEdit.setText(user.getPasswordHash());

            txtBirthday.setText(user.getDateOfBirth().toString());

        } catch (SQLException | NullPointerException e) {

        }
    }

    public void edit() {
        paneEdit.setVisible(true);
        paneDarkBackground.setVisible(true);
    }

    public void closePanel() {
        paneEdit.setVisible(false);
        paneDarkBackground.setVisible(false);
    }

    public void confirmEdit() {
        try {
            User user = userService.validateId(id);

            var name = UserService.validateFullName(txtNameEdit.getText());
            UserService.validateEmail(txtEmailEdit.getText());

            user.setName(name[0]);
            user.setLastName(name[1]);
            user.setEmail(txtEmailEdit.getText());
            user.setPasswordHash(txtPasswordEdit.getText());


            if(userDAO.update(user)){
                closePanel();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerForum.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception error){
            //TODO PADRONIZAR MENSAGEM DE ERRO!
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
            error.printStackTrace();
        }
    }
}
