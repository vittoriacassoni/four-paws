package sample;

import business.singleton.config.Config;
import comuns.enums.RepositoryType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Config.getInstance().setDataBase(RepositoryType.SQLSERVER);
        Parent root = FXMLLoader.load(getClass().getResource("ScreenProfile.fxml"));

        primaryStage.setTitle("4Paws, Bem-vinde!");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();

        //ManageAudit.getInstance().activate();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
