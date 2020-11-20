package sample;

import business.services.AdoptionService;
import business.services.AnimalService;
import business.services.DonationService;
import business.services.UserService;
<<<<<<< HEAD
import comuns.access.*;
import comuns.bases.Entity;
import dao.access.AdoptionRequirementSqlServerDAO;
import dao.access.DonationSqlServerDAO;
=======
import business.singleton.LocalStorage;
import comuns.access.Adoption;
import comuns.access.Animal;
import comuns.access.Donation;
import comuns.access.User;
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1
import dao.access.UserSqlServerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
<<<<<<< HEAD
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
=======
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1
import javafx.scene.layout.Pane;

<<<<<<< HEAD
import business.Validates;

import javax.swing.*;
=======
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerProfile implements Initializable {
    @FXML
<<<<<<< HEAD
    Pane paneEdit, paneDarkBackground, newDonationPane, darkPane;
=======
    Pane paneEdit;

    @FXML
    Pane paneDarkBackground;

    @FXML
    Label txtName;
>>>>>>> 4a6c25bd13e72ae80e4e29d21c72c879757c99a1

    @FXML
    ListView listDonations, listAdoptions;

    @FXML
    Label txtName, txtEmail, txtBirthday, txtPassword;

    @FXML
    TextField txtNameEdit, txtEmailEdit, txtPasswordEdit, txtDescription, txtValue;

    DonationSqlServerDAO donationDAO = new DonationSqlServerDAO();

    @FXML
    TableView tableDonation;

    @FXML
    TableView tableAdoption;

    UserSqlServerDAO userDAO = new UserSqlServerDAO();

    UserService userService = new UserService();
    AnimalService animalService = new AnimalService();
    DonationService donationService = new DonationService();
    AdoptionService adoptionService = new AdoptionService();

    Integer id = LocalStorage.getInstance().getUserId();
    String name = LocalStorage.getInstance().getUserName();
    String email = LocalStorage.getInstance().getUserEmail();
    User user = userService.validateId(id);

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
        txtName.setText(name);
        txtNameEdit.setText(name);
        txtEmail.setText(email);
        txtEmailEdit.setText(email);
        txtPasswordEdit.setText(user.getPasswordHash());
        txtBirthday.setText(user.getDateOfBirth().toString());

        try {
            listDonation();
            listAdoptions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void listDonation() throws SQLException {
        List<Donation> donation = donationService.validateId(id);

        TableColumn value = new TableColumn("Valor Doado");
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        value.setPrefWidth(120);
        TableColumn description = new TableColumn("Descrição");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        description.setPrefWidth(280);
        TableColumn createdAt = new TableColumn("Data de Submissão");
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        createdAt.setPrefWidth(200);

        tableDonation.setEditable(true);
        tableDonation.getColumns().setAll(value, description, createdAt);

        donations = FXCollections.observableList(donation);

        tableDonation.setPrefHeight(195);
        tableDonation.setItems(donations);
        tableDonation.setFixedCellSize(40);
    }

    public void listAdoptions() throws SQLException {

        List<Adoption> adoptions = adoptionService.validateId(id);
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
        createdAt.setPrefWidth(200);

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
            User user = userService.validateId(id);

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
            var value = Validates.validateRequiredField(txtValue.getText());
            var description = Validates.validateRequiredField(txtDescription.getText());

            String descriptions = (String) txtDescription.getText();
            Double values = (Double) Double.valueOf(txtValue.getText());


            var donation = new Donation( values.toString(), descriptions);

            if (donationDAO.insert(donation)) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                newDonationPane.setVisible(false);
                darkPane.setVisible(false);

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
