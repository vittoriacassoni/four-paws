package sample;

import business.Validates;
import business.log.threads.ManageAudit;
import business.services.AdoptionService;
import business.services.AnimalService;
import business.services.ReportAbandonmentService;
import business.services.UserService;
import business.singleton.LocalStorage;
import comuns.access.Adoption;
import comuns.access.Animal;
import comuns.access.Audit;
import comuns.access.ReportAbandonment;
import dao.access.UserSqlServerDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerMain implements Initializable {
    @FXML
    Pane paneMenu, paneReportAbandonment, darkPane, paneHost, paneAdoption;

    @FXML
    ImageView btnShowMenu, imgHeart, imgLupa;

    @FXML
    RadioButton rdCovered, rdNotCovered;

    @FXML
    Label lblName, lblWelcome;

    @FXML
    TextField txtAddress, txtLastSeen, txtNameHost, txtAddressHost;

    @FXML
    Button btnFindPet, btnDonation, btnReportAnimalAbandonment, btnRegisterAnimal;

    @FXML
    TableView tableAdoption2;

    ReportAbandonmentService reportAbandonment = new ReportAbandonmentService();
    AdoptionService adoptionService = new AdoptionService();
    AnimalService animalService = new AnimalService();

    Integer userId = LocalStorage.getInstance().getUserId();

    public ControllerMain() throws IOException, SQLException {

    }

    public class AnimalAdopted {
        Animal animal;
        Adoption adoption;

        AnimalAdopted(Animal animal, Adoption adoption) {
            this.animal = animal;
            this.adoption = adoption;
        }

        public String getName() {
            return animal.getName();
        }

        public String getHistory() {
            return animal.getHistory();
        }

        public Date getCreatedAt() {
            return adoption.getCreatedAt();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (LocalStorage.getInstance().getUserRoleId() == 1) {
                admProfile();
            } else {
                normalProfile();
            }
            lblName.setText(LocalStorage.getInstance().getUserName());
            lblWelcome.setText("Bem-vinde, " + LocalStorage.getInstance().getUserName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: PREENCHER QUANTIDADE DE PETS ADOTADOS
    }

    public void findPet() throws IOException, SQLException {
        if(LocalStorage.getInstance().getAdoptionRequirementId() > 0){
            Parent root = FXMLLoader.load(getClass().getResource("ScreenChooseAnimal.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Escolha seu pet.");
            primaryStage.setScene(new Scene(root, 1200, 700));
            primaryStage.show();
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("ScreenAdoptionRequirement.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Preencha o formulário e encontre seu pet.");
            primaryStage.setScene(new Scene(root, 1200, 700));
            primaryStage.show();
        }
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

    public void showRegisterAnimal() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenRegisterAnimal.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Cadastre um novo animal.");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void saveReport() {
        try {
            Integer userId = LocalStorage.getInstance().getUserId();
            Boolean temporaryHome;
            if (rdCovered.isSelected()) {
                temporaryHome = true;
            } else {
                temporaryHome = false;
            }

            // Mari - esse tava errado com HostName 
            //var report = new ReportAbandonment(txtAddress.getText(), userId, temporaryHome, txtHostName.getText(), txtAddressHost.getText());
            var report = new ReportAbandonment(txtAddress.getText(), userId, temporaryHome, txtNameHost.getText(), txtAddressHost.getText());

            if (reportAbandonment.insert(report, txtLastSeen.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso!");
                alert.setHeaderText(null);
                alert.setContentText("Reportado com sucesso!");
                alert.showAndWait();
                closeReportAbandonmentModal();


                Audit audit = new Audit();
                audit.setUserId(String.valueOf(userId));
                audit.setAction("Novo Report");
                ManageAudit.getInstance().addAudit(audit);
                ManageAudit.getInstance().activate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
        }

    }

    private void listAdoptions() throws SQLException {

        List<Adoption> adoptions = adoptionService.validateId(userId);
        List<Animal> animals = animalService.validateAll();

        List<ControllerMain.AnimalAdopted> animalAdopteds = new ArrayList<>();

        for (Adoption adoption : adoptions) {
            for (Animal animal : animals) {
                if (animal.getId() == adoption.getAnimalId()) {
                    ControllerMain.AnimalAdopted animalAdopted = new ControllerMain.AnimalAdopted(animal, adoption);
                    animalAdopteds.add(animalAdopted);
                }
            }
        }

        TableColumn name = new TableColumn("Nome do Pet");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(120);
        TableColumn history = new TableColumn("História");
        history.setCellValueFactory(new PropertyValueFactory<>("history"));
        history.setPrefWidth(280);
        TableColumn createdAt = new TableColumn("Data de Adoção");
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        createdAt.setPrefWidth(150);

        tableAdoption2.setEditable(true);
        tableAdoption2.getColumns().setAll(name, history, createdAt);
        tableAdoption2.setPrefHeight(195);
        tableAdoption2.setItems(FXCollections.observableList(animalAdopteds));
        tableAdoption2.setFixedCellSize(40);

    }

    public void showReportAbandonmentModal() throws IOException {
        paneReportAbandonment.setVisible(true);
        darkPane.setVisible(true);
    }

    public void closeReportAbandonmentModal() {
        paneReportAbandonment.setVisible(false);
        darkPane.setVisible(false);
    }

    public void closeAdoptionModal() {
        paneAdoption.setVisible(false);
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
        if (rdCovered.isSelected()) {
            paneHost.setVisible(true);
        } else {
            paneHost.setVisible(false);
        }
    }

    public void showAdoptionPane() throws IOException {
        paneAdoption.setVisible(true);
        darkPane.setVisible(true);
        try {
            listAdoptions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void normalProfile() throws IOException, SQLException {
        btnFindPet.setVisible(true);
        btnReportAnimalAbandonment.setVisible(true);
    }

    public void admProfile() throws IOException, SQLException {
        btnDonation.setVisible(true);
        imgHeart.setVisible(true);
        imgLupa.setVisible(false);
        btnRegisterAnimal.setVisible(true);
    }

    public void signOut() throws IOException, SQLException {
        if (LocalStorage.checkLocalStorage()) {
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

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocorreu um erro inesperado!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}