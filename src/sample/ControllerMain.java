package sample;

import business.Validates;
import business.log.threads.ManageAudit;
import business.services.ReportAbandonmentService;
import business.singleton.LocalStorage;
import comuns.access.Audit;
import comuns.access.ReportAbandonment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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

    @FXML
    TextField txtAddress, txtLastSeen, txtHostName, txtAddressHost;

    ReportAbandonmentService reportAbandonment = new ReportAbandonmentService();

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

    public void saveReport(MouseEvent event) {
        try {
            Date lastSeen = Validates.validateDate(txtLastSeen.getText());
            Integer userId = LocalStorage.getInstance().getUserId();
            Boolean temporaryHome;
            if (rdCovered.isSelected()){
                temporaryHome = true;
            } else {
                temporaryHome = false;
            }

            var report = new ReportAbandonment(txtAddress.getText(), lastSeen, userId, temporaryHome, txtHostName.getText(), txtAddressHost.getText());

            if(reportAbandonment.insert(report)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reportado com sucesso!");
                alert.setHeaderText(null);
                alert.showAndWait();
                paneReportAbandonment.setVisible(false);

                Audit audit = new Audit();
                //audit.setUserId(null);
                audit.setAction("Novo Report");
                ManageAudit.getInstance().addAudit(audit);
                ManageAudit.getInstance().activate();
            }
        } catch(Exception error) {

        }

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

    public void signOut() throws IOException, SQLException {
        if(LocalStorage.checkLocalStorage()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sair");
            alert.setHeaderText("Você tem certeza que deseja sair? Será preciso fazer o login novamente!");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(type -> {
                System.out.println(type);
                if (type == ButtonType.YES) {
                    try {
                        LocalStorage.getInstance().deleteLocalStorage();
                        Parent root = FXMLLoader.load(getClass().getResource("ScreenLogin.fxml"));
                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("4Paws, Bem-vinde!");
                        primaryStage.setScene(new Scene(root, 1200, 700));
                        primaryStage.show();

                        Stage stage = (Stage) lblWelcome.getScene().getWindow();
                        stage.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocorreu um erro inesperado!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}