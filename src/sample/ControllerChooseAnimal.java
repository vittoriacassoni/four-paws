package sample;

import business.services.AnimalService;
import business.singleton.LocalStorage;
import comuns.access.Animal;
import dao.access.AnimalSqlServerDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerChooseAnimal implements Initializable {

    @FXML
    Label lblName1, lblName2, lblName3, lblExpense1, lblExpense2, lblExpense3, lblColor1, lblColor2, lblColor3;
    @FXML
    Label lblWeight1, lblWeight2, lblWeight3, lblSize1, lblSize2, lblSize3, lblAge1, lblAge2, lblAge3, lblHistory1, lblHistory2, lblHistory3;
    @FXML
    Label lblBreed1, lblBreed2, lblBreed3;
    @FXML
    Label btnChooseAnimal1, btnChooseAnimal2, btnChooseAnimal3;

    AnimalService service = new AnimalService();

    Integer animal1, animal2, animal3 = 0;
    Integer userId = LocalStorage.getInstance().getUserId();

    public ControllerChooseAnimal() throws IOException, SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<Animal> list = (ArrayList<Animal>) service.listAnimalsToAdopt(LocalStorage.getInstance().getAdoptionRequirementId());
            lblName1.setText(list.get(0).getName());
            lblName2.setText(list.get(1).getName());
            lblName3.setText(list.get(2).getName());

            lblExpense1.setText("R$ " + String.valueOf(list.get(0).getMaxExpense()));
            lblExpense2.setText("R$ " + String.valueOf(list.get(1).getMaxExpense()));
            lblExpense3.setText("R$ " + String.valueOf(list.get(2).getMaxExpense()));

            lblColor1.setText(list.get(0).getColor());
            lblColor2.setText(list.get(1).getColor());
            lblColor3.setText(list.get(2).getColor());

            lblBreed1.setText(list.get(0).getBreed());
            lblBreed2.setText(list.get(1).getBreed());
            lblBreed3.setText(list.get(2).getBreed());

            lblWeight1.setText(String.valueOf(list.get(0).getWeight()) + " kg");
            lblWeight2.setText(String.valueOf(list.get(1).getWeight()) + " kg");
            lblWeight3.setText(String.valueOf(list.get(2).getWeight()) + " kg");

            lblSize1.setText(String.valueOf(list.get(0).getSize()) + " m");
            lblSize2.setText(String.valueOf(list.get(1).getSize()) + " m");
            lblSize3.setText(String.valueOf(list.get(2).getSize()) + " m");

            lblAge1.setText(String.valueOf(list.get(0).getAge()));
            lblAge2.setText(String.valueOf(list.get(1).getAge()));
            lblAge3.setText(String.valueOf(list.get(2).getAge()));

            lblHistory1.setText(list.get(0).getHistory());
            lblHistory2.setText(list.get(1).getHistory());
            lblHistory3.setText(list.get(2).getHistory());

            btnChooseAnimal1.setText("Escolher " + list.get(0).getName());
            btnChooseAnimal2.setText("Escolher " + list.get(1).getName());
            btnChooseAnimal3.setText("Escolher " + list.get(2).getName());

            animal1 = list.get(0).getId();
            animal2 = list.get(1).getId();
            animal3 = list.get(2).getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseAnimal1(MouseEvent event) throws Exception {
       if(service.update(animal1, userId)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Sucesso!");
           alert.setHeaderText(null);
           alert.setContentText("Animal adotado com sucesso!");
           alert.showAndWait();

           Stage stage = (Stage) lblName1.getScene().getWindow();
           stage.close();
       }
    }

    public void chooseAnimal2(MouseEvent event) throws Exception {
        if(service.update(animal1, userId)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText(null);
            alert.setContentText("Animal adotado com sucesso!");
            alert.showAndWait();

            Stage stage = (Stage) lblName1.getScene().getWindow();
            stage.close();
        }
    }

    public void chooseAnimal3(MouseEvent event) throws Exception {
        if(service.update(animal1, userId)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText(null);
            alert.setContentText("Animal adotado com sucesso!");
            alert.showAndWait();

            Stage stage = (Stage) lblName1.getScene().getWindow();
            stage.close();
        }
    }
}
