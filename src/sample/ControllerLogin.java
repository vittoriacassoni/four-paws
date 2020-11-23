package sample;

import business.log.threads.ManageAudit;
import business.singleton.LocalStorage;
import business.services.UserService;
import comuns.access.Audit;
import comuns.access.User;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    @FXML
    Pane paneRememberPass;

    @FXML
    Pane paneDarkBackground;

    @FXML
    TextField txtEmail;

    @FXML
    TextField txtPassword;

    UserSqlServerDAO userDAO = new UserSqlServerDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    
    public void login(ActionEvent event) throws IOException, SQLException, InterruptedException {
        UserService userService = new UserService();

        if (userService.validateLogin(txtEmail.getText(), txtPassword.getText())) {
            var user = (User) userDAO.select(txtEmail.getText());

            LocalStorage.getInstance().saveUserId(String.valueOf(user.getId()));
            LocalStorage.getInstance().saveUserEmail(user.getEmail());
            LocalStorage.getInstance().saveUserName(user.getName());
            LocalStorage.getInstance().saveUserLastName(user.getLastName());
            LocalStorage.getInstance().saveUserRoleId(String.valueOf(user.getUserRoleld()));

            showScreen("ScreenMain.fxml", "Seja Bem-Vinde");

            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.close();

            //TODO Retirar a DAO, mudar para business!
            Audit audit = new Audit();
            audit.setUserId(String.valueOf(user.getId()));
            audit.setAction("Login");
            ManageAudit.getInstance().addAudit(audit);
            ManageAudit.getInstance().activate();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dados inválidos!");
            alert.setHeaderText(null);
            alert.setContentText("Verifique seu dados de Login!");
            alert.showAndWait();
        }
    }

    private void showScreen(String screen, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(screen));
        Stage primaryStage = new Stage();
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void signUp(MouseEvent event) throws IOException {
        showScreen("ScreenSignUp.fxml", "Cadastro - Novo Usuário");
    }

    public void rememberPass() {
        paneRememberPass.setVisible(true);
        paneDarkBackground.setVisible(true);
    }

    public void closePanel() {
        paneRememberPass.setVisible(false);
        paneDarkBackground.setVisible(false);
    }

    public void closePanelRememberPassword(MouseEvent event){
        if(paneRememberPass.isVisible()){
            paneRememberPass.setVisible(false);
            paneDarkBackground.setVisible(false);
        }
    }
}
