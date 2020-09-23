package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {

    public void login(ActionEvent actionEvent) throws IOException {
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

    public void rememberPass(ActionEvent event) {
        /*
        TextInputDialog dialog = new TextInputDialog("Email");
        dialog.setTitle("Esqueceu a Senha:");
        Optional<String> result = dialog.showAndWait();
        */
    }
}

