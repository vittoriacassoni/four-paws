package sample;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.Validates;
import business.log.threads.ManageAudit;
import comuns.access.AdoptionRequirement;
import comuns.access.Audit;
import dao.access.AdoptionRequirementSqlServerDAO;
import dao.access.ForumTopicSqlServerDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.*;

public class ControllerAdoptionRequirement implements Initializable {

    @FXML private Spinner gradeSpinner;
    @FXML private Spinner gradeSpinner1;
    @FXML private Spinner gradeSpinner2;

    @FXML  Spinner spinnerMaxExpensive;

    @FXML  Spinner spinnerRequiredSpace;

    @FXML  Spinner spinnerAge;

    @FXML  Checkbox checkAngry;

    @FXML  Checkbox checkHappy;

    @FXML  Checkbox checkNeedy;

    @FXML  Checkbox checkCaring;

    @FXML  Checkbox checkQuiet;

    @FXML  Pane paneFoundPet;


    AdoptionRequirementSqlServerDAO AdoptionRequirementDAO = new AdoptionRequirementSqlServerDAO();
    ForumTopicSqlServerDAO topicDAO = new ForumTopicSqlServerDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        SpinnerValueFactory<Integer> gradesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000,100);
        this.gradeSpinner.setValueFactory(gradesValueFactory);
        gradeSpinner.setEditable(true);

        SpinnerValueFactory<Integer> gradesValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000,100);
        this.gradeSpinner1.setValueFactory(gradesValueFactory1);
        gradeSpinner1.setEditable(true);

        SpinnerValueFactory<Integer> gradesValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000,100);
        this.gradeSpinner2.setValueFactory(gradesValueFactory2);
        gradeSpinner2.setEditable(true);

    }

   // public void foundPet(MouseEvent event) throws IOException {
       // Config.getInstance().setDataBase(RepositoryType.SQLSERVER);
     //   try {

           // Validates.validateSpinnersAdaption(spinnerMaxExpensive.getValue().toString());
          //  Validates.validateSpinnersAdaption(spinnerRequiredSpace.getValue().toString());

           // var adoptionRequirement = new AdoptionRequirement(spinnerAge.getValue(),spinnerMaxExpensive.getValue(),spinnerRequiredSpace.getValue(),checkAngry.get);
            //AdoptionRequirement.setBoolean(0);

            //if(AdoptionRequirementDAO.insert(adoptionRequirement)){
               // ManageAudit.getInstance().activate();

               // var userBanco = AdoptionRequirementDAO.select(AdoptionRequirement.getUserId());
               // var userId = userBanco.getId();

              //  Audit audit = new Audit();
              //  audit.setUserId(String.valueOf(userId));
             //   audit.setAction("Encontrar Pet");
            //    ManageAudit.getInstance().addAudit(audit);
           //     Thread.sleep(1000);

           //     JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        //        paneFoundPet.setVisible(false);

      //      }
     //   } catch (SQLException ex) {
      //      Logger.getLogger(ControllerForum.class.getName()).log(Level.SEVERE, null, ex);
      //  } catch (Exception error){
       //     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //    alert.setTitle("Erro!");
       //     alert.setHeaderText(null);
       //     alert.setContentText(error.getMessage());
    //        alert.showAndWait();
    //    }
   // }
}