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
<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("ScreenProfile.fxml"));
=======
        Parent root = FXMLLoader.load(getClass().getResource("ScreenLogin.fxml"));
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1

        primaryStage.setTitle("4Paws, Bem-vinde!");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();

        //ManageAudit.getInstance().activate();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
