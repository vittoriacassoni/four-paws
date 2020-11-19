package sample;

import business.services.AdoptionService;
import business.services.AnimalService;
import business.services.DonationService;
import business.services.UserService;
import business.singleton.LocalStorage;
import comuns.access.Adoption;
import comuns.access.Animal;
import comuns.access.Donation;
import comuns.access.User;
import dao.access.UserSqlServerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerProfile implements Initializable {
    @FXML
    Pane paneEdit;

    @FXML
    Pane paneDarkBackground;

    @FXML
    ListView listDonations;

    @FXML
    ListView listAdoptions;

    @FXML
    Label txtName;

    @FXML
    Label txtEmail;

    @FXML
    Label txtBirthday;

    @FXML
    Label txtPassword;

    @FXML
    TextField txtNameEdit;

    @FXML
    TextField txtEmailEdit;

    @FXML
    TextField txtPasswordEdit;

    UserSqlServerDAO userDAO = new UserSqlServerDAO();

    UserService userService = new UserService();
    AnimalService animalService = new AnimalService();
    DonationService donationService = new DonationService();
    AdoptionService adoptionService = new AdoptionService();

    Integer id = LocalStorage.getInstance().getUserId();
    String name = LocalStorage.getInstance().getUserName();
    String email = LocalStorage.getInstance().getUserEmail();

    public ControllerProfile() throws IOException, SQLException {

    }

    private ObservableList<Donation> donations = FXCollections.observableArrayList();
    private ObservableList<Animal> animals = FXCollections.observableArrayList();
    private ObservableList<Adoption> adoptions = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        try {
            User user = userService.validateId(id);
            List<Animal> animal = animalService.validateId(id);
            List<Donation> donation = donationService.validateId(id);
            List<Adoption> adoptions = adoptionService.validateId(id);

            txtName.setText(name);
            txtNameEdit.setText(name);
            txtEmail.setText(email);
            txtEmailEdit.setText(email);
            txtPasswordEdit.setText(user.getPasswordHash());
            txtBirthday.setText(user.getDateOfBirth().toString());

            for (Donation List: donation) {
                donations.add(List);
            }

            listDonations.setPrefHeight(195);
            listDonations.setItems(donations);
            listDonations.setFixedCellSize(40);



        } catch (SQLException | NullPointerException e) {

        }
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


            if(userDAO.update(user)){
                closePanel();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerForum.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception error){
            //TODO PADRONIZAR MENSAGEM DE ERRO!
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
            error.printStackTrace();
        }
    }
}
