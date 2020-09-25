package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSignUp implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signIn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenLogin.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }
}
