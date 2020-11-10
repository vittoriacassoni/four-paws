package sample;

import business.Validates;
import business.log.threads.ManageAudit;
import business.singleton.config.Config;
import comuns.access.Audit;
import comuns.access.ForumTopic;
import comuns.access.User;
import comuns.enums.RepositoryType;
import dao.access.ForumTopicSqlServerDAO;
import dao.access.UserSqlServerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerSignUp implements Initializable {
    @FXML
    TextField txtFullName;

    @FXML
    TextField txtEmail;

    @FXML
    TextField txtDateBirth;

    @FXML
    TextField txtPassword;

    UserSqlServerDAO userDAO = new UserSqlServerDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signUp(MouseEvent event) {
        try {
            var name = Validates.validateFullName(txtFullName.getText());
            Date dateOfBirth = Validates.validateDate(txtDateBirth.getText());
            Validates.validateEmail(txtEmail.getText());

            var user = new User(name[0], name[1], txtEmail.getText(), txtPassword.getText(), dateOfBirth);
            user.setUserRoleld(1);

            if(userDAO.insert(user)){
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                Stage stage = (Stage) txtDateBirth.getScene().getWindow();
                stage.close();

                var userEntity = userDAO.select(user.getEmail());
                var userId = userEntity.getId();

                Audit audit = new Audit();
                audit.setUserId(String.valueOf(userId));
                audit.setAction("Cadastro");
                ManageAudit.getInstance().addAudit(audit);
                ManageAudit.getInstance().activate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerForum.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception error){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
            error.printStackTrace();
        }

    }
}
