package sample;

import business.Access;
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


    public void login(ActionEvent event) throws IOException, SQLException {
        if (Access.validateLogin(txtEmail.getText(), txtPassword.getText())) {
            Parent root = FXMLLoader.load(getClass().getResource("ScreenMain.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Seja Bem-Vinde");
            primaryStage.setScene(new Scene(root, 1200, 700));
            primaryStage.show();
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
}
