package sample;

import business.singleton.LocalStorage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerMain implements Initializable {
    @FXML
    Pane paneMenu, paneReportAbandonment, darkPane, paneHost;

    @FXML
    ImageView btnShowMenu;

    @FXML
    RadioButton rdCovered, rdNotCovered;

    @FXML
    Label lblName, lblWelcome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lblName.setText(LocalStorage.getInstance().getUserName());
            lblWelcome.setText("Bem-vinde, " + LocalStorage.getInstance().getUserName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: PREENCHER QUANTIDADE DE PETS ADOTADOS
    }

    public void findPet() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenAdoptionRequirement.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Preencha o formulário e encontre seu pet.");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void accessForum() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenForum.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Participe da nossa comunidade e ajude nossos Pawers.");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void showProfile() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenProfile.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Suas informações.");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void showReportAbandonmentModal() throws IOException {
        paneReportAbandonment.setVisible(true);
        darkPane.setVisible(true);
    }

    public void closeReportAbandonmentModal() {
        paneReportAbandonment.setVisible(false);
        darkPane.setVisible(false);
    }

    public void showHideMenu() {
        if (paneMenu.isVisible()) {
            btnShowMenu.setRotate(90);
            paneMenu.setVisible(false);
        } else {
            btnShowMenu.setRotate(-90);
            paneMenu.setVisible(true);
        }
    }

    public void showHideHostInfo() {
        if(rdCovered.isSelected()){
            paneHost.setVisible(true);
        } else{
            paneHost.setVisible(false);
        }
    }

}