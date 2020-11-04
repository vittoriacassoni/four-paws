package sample;

import business.singleton.config.Config;
import comuns.access.Audit;
import business.log.threads.ManageAudit;
import comuns.enums.RepositoryType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.Instant;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Config.getInstance().setDataBase(RepositoryType.SQLSERVER);
        Parent root = FXMLLoader.load(getClass().getResource("ScreenSignUp.fxml"));

        primaryStage.setTitle("4Paws, Bem-vinde!");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();

        System.out.printf("%s - Início da brincadeira\n", Instant.now().toString());
        ManageAudit.getInstance().activate();

        Audit audit = new Audit();
        audit.setUserId(null);
        audit.setAction("teste2");
        System.out.println(audit.getAction());
        ManageAudit.getInstance().addAudit(audit);
        Thread.sleep(1000);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
