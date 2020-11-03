package sample;

import business.log.threads.ThreadManageAudit;
import business.singleton.config.Config;
import comuns.access.ForumTopic;
import comuns.enums.RepositoryType;
import dao.access.ForumTopicSqlServerDAO;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerForum {
    @FXML
    Pane newTopicPane;
    @FXML
    Pane answerTopicPane;
    @FXML
    TextField txtTitulo;
    @FXML
    TextArea txtAssunto;

    ForumTopic newTopic = new ForumTopic();
    ForumTopicSqlServerDAO topic = new ForumTopicSqlServerDAO();

    public void saveNewTopic(MouseEvent mouseEvent) throws IOException {
        Config.getInstance().setDataBase(RepositoryType.SQLSERVER);
        try {
            if (txtTitulo.getText().equals("") || txtAssunto.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dados inválidos!");
                alert.setHeaderText(null);
                alert.setContentText("Preencha os campos obrigatórios.");
                alert.showAndWait();
            } else {
                topic.insert(newTopic);
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                newTopicPane.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerForum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadPane(MouseEvent mouseEvent) throws IOException {
        newTopicPane.setVisible(true);
    }

    public void closePane(MouseEvent mouseEvent) throws IOException {
        newTopicPane.setVisible(false);
    }

    public void loadAnswerPane(MouseEvent mouseEvent) throws  IOException {
        answerTopicPane.setVisible(true);
    }

    public void closeAnswerPane(MouseEvent mouseEvent) throws IOException {
        answerTopicPane.setVisible(false);
    }



}

