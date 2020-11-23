package sample;

import business.MyEmailer;
import business.log.threads.ManageAudit;
import business.singleton.LocalStorage;
import business.services.UserService;
import comuns.access.Audit;
import comuns.access.User;
import comuns.bases.Entity;
import dao.access.UserSqlServerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerLogin {
    @FXML
    Pane paneRememberPass;

    @FXML
    Pane paneDarkBackground;

    @FXML
    TextField txtEmail;

    @FXML
    TextField txtPassword;

    @FXML
    TextField txtRememberPass;

    UserSqlServerDAO userDAO = new UserSqlServerDAO();
    UserService userService = new UserService();

    public void login(ActionEvent event) throws IOException, SQLException, InterruptedException {

        if (userService.validateLogin(txtEmail.getText(), txtPassword.getText())) {
            var user = (User) userDAO.select(txtEmail.getText());

            LocalStorage.getInstance().saveUserId(String.valueOf(user.getId()));
            LocalStorage.getInstance().saveUserEmail(user.getEmail());
            LocalStorage.getInstance().saveUserName(user.getName());
            LocalStorage.getInstance().saveUserLastName(user.getLastName());

            Parent root = FXMLLoader.load(getClass().getResource("ScreenMain.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Seja Bem-Vinde");
            primaryStage.setScene(new Scene(root, 1200, 700));
            primaryStage.show();

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

    public void signUp(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenSignUp.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Cadastro - Novo Usuário");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void rememberPass() {
        paneRememberPass.setVisible(true);
        paneDarkBackground.setVisible(true);
    }

    public void closePanel() {
        paneRememberPass.setVisible(false);
        paneDarkBackground.setVisible(false);
    }

    public void sendEmail() throws Exception {
        User user = (User) userService.validatePassword(txtRememberPass.getText());
        if (user != null)
        {
            String text = "Solicitação de recuperação de senha!";
            String password = user.getPasswordHash();
            String recipient = user.getEmail();
            String content = "<p>Olá, aumigo!</p>" +
                    "<p>Conforme sua solicitação na nossa plataforma, segue sua senha de acesso</p>:" +
                    "<p>" + password + "</p>" +
                    "<p> #Lambeijos </p>";
            String subject = "Solicitação de recuperação de senha!";
            MyEmailer myEmailer = new MyEmailer();
            myEmailer.SendMail(text, content, recipient, subject);
        }
    }
}
