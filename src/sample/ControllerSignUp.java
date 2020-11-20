package sample;

import business.Image;
import business.Validates;
import business.log.threads.ManageAudit;
import business.services.UserService;
import comuns.access.Audit;
import comuns.access.User;
import dao.access.UserSqlServerDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
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
    TextField txtPassword, txtPath;

    @FXML
    ImageView imgUser;

    UserSqlServerDAO userDAO = new UserSqlServerDAO();

    UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signUp(MouseEvent event) {
        try {
            var name = UserService.validateFullName(txtFullName.getText());
            Date dateOfBirth = Validates.validateDate(txtDateBirth.getText());
            UserService.validateEmail(txtEmail.getText());

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

    /*public void saveImage(MouseEvent event) {
        Image image = new Image();
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i= file.showSaveDialog(null);
        if (i==1){
            image.
        } else {
            File arquivo = file.getSelectedFile();
            JtextFieldLocal.setText(arquivo.getPath());
        }

    }*/
}
