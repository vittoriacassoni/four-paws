package sample;

import business.Validates;
import business.log.threads.ManageAudit;
import business.singleton.LocalStorage;
import comuns.access.Animal;
import comuns.access.Audit;
import dao.access.AnimalSqlServerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRegisterAnimal implements Initializable {

    @FXML
    ChoiceBox<String> dropBreeds, dropColors;
    @FXML
    Circle imgAnimal;
    @FXML
    TextField txtName;
    @FXML
    TextField txtHistory;
    @FXML
    Spinner spnAge;
    @FXML
    Spinner spnWeight;
    @FXML
    Spinner spnSize;

    AnimalSqlServerDAO animalDAO = new AnimalSqlServerDAO();

    Integer userId = LocalStorage.getInstance().getUserId();

    public ControllerRegisterAnimal() throws IOException, SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dropBreeds.getItems().addAll("Poodle", "Maltês", "Golden", "Pug", "Vira-lata", "Outras");
        dropBreeds.getSelectionModel().select(0);

        dropColors.getItems().addAll("Preto", "Branco", "Creme", "Caramelo", "Outras");
        dropColors.getSelectionModel().select(0);

        SpinnerValueFactory<Double> sizeValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, Double.MAX_VALUE, 1);
        this.spnSize.setValueFactory(sizeValue);
        spnSize.setEditable(true);

        SpinnerValueFactory<Integer> ageValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        this.spnAge.setValueFactory(ageValue);
        spnAge.setEditable(true);

        SpinnerValueFactory<Double> weightValue = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, Double.MAX_VALUE, 1);
        this.spnWeight.setValueFactory(weightValue);
        spnWeight.setEditable(true);
    }

    public void registerPet(ActionEvent event) throws IOException {
        try {
            insertAnimal();
        }
        catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
        }
    }

    public void insertAnimal() throws Exception {
        if (!Validates.validateRequiredField(txtName.getText()) &&
                !Validates.validateRequiredField(dropBreeds.getValue()) &&
                !Validates.validateRequiredField(dropColors.getValue()) &&
                Validates.validateDoubleNumber(spnSize.getValueFactory().getValue().toString()) &&
                Validates.validateDoubleNumber(spnWeight.getValueFactory().getValue().toString()) &&
                !Validates.validateRequiredField(txtHistory.getText()) &&
                !Validates.validateRequiredField(spnAge.getValueFactory().getValue().toString())) {

            String name = txtName.getText();
            String breed = dropBreeds.getValue();
            String color = dropColors.getValue();
            Double size = (Double) spnSize.getValueFactory().getValue();
            Double weight = (Double) spnWeight.getValueFactory().getValue();
            String history = txtHistory.getText();
            Integer age = (Integer) spnAge.getValueFactory().getValue();
            //TODO ADICIONAR O CAMPO DE IMAGEM!
            var animal = new Animal(name, breed, color, size, weight, history, age);

            if (animalDAO.insert(animal)) {
                ManageAudit.getInstance().activate();

                Audit audit = new Audit();
                audit.setUserId(userId.toString());
                audit.setAction("Encontrar Pet");
                ManageAudit.getInstance().addAudit(audit);
                Thread.sleep(1000);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Salvo!");
                alert.setHeaderText(null);
                alert.setContentText("Animal cadastrado com sucesso!");
                alert.showAndWait();

            }
        }
    }
}