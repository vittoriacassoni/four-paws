package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ControllerLogin {

    public void login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenMain.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Seja Bem-Vinde");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void signUp(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenSignUp.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Cadastro - Novo Usuário");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void rememberPass() {
        TextInputDialog dialog = new TextInputDialog("Email");
        dialog.setTitle("Recuperar a senha:");
        dialog.setHeaderText("Digite o e-mail cadastrado:");
        dialog.setGraphic(null);
        dialog.getDialogPane().getButtonTypes().remove(1);
        Optional<String> result = dialog.showAndWait();
    }
}


