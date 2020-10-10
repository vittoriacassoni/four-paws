package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ControllerMain {

    public void formpet() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenAdoptionRequirement.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Preencha o formulário e encontre seu pet.");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void formForum() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenForum.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Participe da nossa comunidade e ajude nossos Pawers.");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }
}