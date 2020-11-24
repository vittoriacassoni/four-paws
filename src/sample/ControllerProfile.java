package sample;

import business.log.threads.ManageAudit;
import business.services.AdoptionService;
import business.services.AnimalService;
import business.services.DonationService;
import business.services.UserService;
import comuns.access.*;
import business.singleton.LocalStorage;
import dao.access.UserSqlServerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import business.Validates;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerProfile implements Initializable {
    @FXML
    Pane paneEdit, paneDarkBackground, newDonationPane, darkPane;

    @FXML
    ListView listDonations, listAdoptions;

    @FXML
    Label txtName, txtEmail, txtBirthday, txtPassword;

    @FXML
    TextField txtNameEdit, txtEmailEdit, txtPasswordEdit, txtDescription, txtValue;

    @FXML
    TableView tableDonation, tableAdoption;


    UserSqlServerDAO userDAO = new UserSqlServerDAO();

    UserService userService = new UserService();
    AnimalService animalService = new AnimalService();
    DonationService donationService = new DonationService();
    AdoptionService adoptionService = new AdoptionService();

    Integer userId = LocalStorage.getInstance().getUserId();
    String name = LocalStorage.getInstance().getUserName();
    String email = LocalStorage.getInstance().getUserEmail();
    User user = userService.validateId(userId);

    public ControllerProfile() throws IOException, SQLException {

    }

    private ObservableList<Donation> donations = FXCollections.observableArrayList();

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
        fillUserInfo();

        try {
            listDonation();
            listAdoptions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void fillUserInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtName.setText(name);
        txtNameEdit.setText(name);
        txtEmail.setText(email);
        txtEmailEdit.setText(email);
        txtPasswordEdit.setText(user.getPasswordHash());
        txtBirthday.setText(dateFormat.format(user.getDateOfBirth()));
    }

    private void listDonation() throws SQLException {
        List<Donation> donation = donationService.validateId(userId);

        TableColumn value = new TableColumn("Valor Doado");
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        value.setPrefWidth(120);
        TableColumn description = new TableColumn("Descrição");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.setPrefWidth(280);
        TableColumn createdAt = new TableColumn("Data de Submissão");
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        createdAt.setPrefWidth(150);

        tableDonation.setEditable(true);
        tableDonation.getColumns().setAll(value, description, createdAt);

        donations = FXCollections.observableList(donation);

        tableDonation.setPrefHeight(195);
        tableDonation.setItems(donations);
        tableDonation.setFixedCellSize(40);
    }

    public void listAdoptions() throws SQLException {

        List<Adoption> adoptions = adoptionService.validateId(userId);
        List<Animal> animals = animalService.validateAll();

        List<AnimalAdopted> animalAdopteds = new ArrayList<AnimalAdopted>();

        for (Adoption adoption : adoptions) {
            for (Animal animal : animals) {
                if (animal.getId() == adoption.getAnimalId()) {
                    AnimalAdopted animalAdopted = new AnimalAdopted(animal, adoption);
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

        tableAdoption.setEditable(true);
        tableAdoption.getColumns().setAll(name, history, createdAt);
        tableAdoption.setPrefHeight(195);
        tableAdoption.setItems(FXCollections.observableList(animalAdopteds));
        tableAdoption.setFixedCellSize(40);

    }

    public void edit() {
        paneEdit.setVisible(true);
        paneDarkBackground.setVisible(true);
    }

    public void closePanel() {
        paneEdit.setVisible(false);
        paneDarkBackground.setVisible(false);
    }

    public void confirmEdit() {
        try {
            User user = userService.validateId(userId);

            var name = UserService.validateFullName(txtNameEdit.getText());
            UserService.validateEmail(txtEmailEdit.getText());

            user.setName(name[0]);
            user.setLastName(name[1]);
            user.setEmail(txtEmailEdit.getText());
            user.setPasswordHash(txtPasswordEdit.getText());


            if (userDAO.update(user)) {
                closePanel();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerForum.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception error) {
            //TODO PADRONIZAR MENSAGEM DE ERRO!
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
            error.printStackTrace();
        }
    }

    public void loadDonationPane(MouseEvent mouseEvent) throws IOException {
        newDonationPane.setVisible(true);
        darkPane.setVisible(true);
    }

    public void closeDonationPane(MouseEvent mouseEvent) throws IOException {
        newDonationPane.setVisible(false);
        darkPane.setVisible(false);
    }

    public void sendNewDonation(MouseEvent mouseEvent) {
        try {
            String description = txtDescription.getText();
            Double value = Double.valueOf(txtValue.getText());

            var donation = new Donation(value, description, userId);

            if (donationService.insert(donation)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso!");
                alert.setHeaderText(null);
                alert.setContentText("Salvo com sucesso!");
                alert.showAndWait();

                listDonation();
                closeDonationPane(mouseEvent);

                Audit audit = new Audit();
                audit.setUserId(String.valueOf(userId));
                audit.setAction("Novo Doação");
                ManageAudit.getInstance().addAudit(audit);
                ManageAudit.getInstance().activate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerForum.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
        }
    }
}
