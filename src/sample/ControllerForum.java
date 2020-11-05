package sample;

import business.Validates;
import business.log.threads.ManageAudit;
import business.log.threads.ThreadManageAudit;
import business.singleton.config.Config;
import comuns.access.Audit;
import comuns.access.ForumComment;
import comuns.access.ForumTopic;
import comuns.enums.RepositoryType;
import dao.access.ForumCommentSqlServerDAO;
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
    TextField txtTitle;
    @FXML
    TextArea txtDiscussion;
    @FXML
    TextField txtComment;
    @FXML
    Pane darkPane;

    ForumTopicSqlServerDAO topicDAO = new ForumTopicSqlServerDAO();
    ForumCommentSqlServerDAO commentDAO = new ForumCommentSqlServerDAO();

    public void saveNewTopic(MouseEvent mouseEvent) {
        try {
            var topic = new ForumTopic(txtTitle.getText(), txtDiscussion.getText());
            topic.setUserId(1);

            /*topicDAO.insert(topic);*/

             if(topicDAO.insert(topic)) {
                 ManageAudit.getInstance().activate();

                 var userBanco = topicDAO.select(topic.getUserId());
                 var userId = userBanco.getId();

                 Audit audit = new Audit();
                 audit.setUserId(String.valueOf(userId));
                 audit.setAction("Novo Tópico");
                 ManageAudit.getInstance().addAudit(audit);
                 Thread.sleep(1000);

                 JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                 newTopicPane.setVisible(false);
                 darkPane.setVisible(false);
             }

             } catch (SQLException ex) {
                Logger.getLogger(ControllerForum.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception error){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erro!");
                alert.setHeaderText(null);
                alert.setContentText(error.getMessage());
                alert.showAndWait();
            }
    }

    public void sendNewComment(MouseEvent mouseEvent) {
        try {
            var comment = new ForumComment(txtComment.getText());
            comment.setForumTopicId(1);
            comment.setUserId(1);

            if (commentDAO.insert(comment)) {
                ManageAudit.getInstance().activate();

                Audit audit = new Audit();
                audit.setUserId(null);
                audit.setAction("Novo Comentário");
                ManageAudit.getInstance().addAudit(audit);
                Thread.sleep(1000);

                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                answerTopicPane.setVisible(false);
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

    public void loadPane(MouseEvent mouseEvent) throws IOException {
        newTopicPane.setVisible(true);
        darkPane.setVisible(true);
    }

    public void closePane(MouseEvent mouseEvent) throws IOException {
        newTopicPane.setVisible(false);
        darkPane.setVisible(false);
    }

    public void loadAnswerPane(MouseEvent mouseEvent) throws IOException {
        answerTopicPane.setVisible(true);
        darkPane.setVisible(true);
    }

    public void closeAnswerPane(MouseEvent mouseEvent) throws IOException {
        answerTopicPane.setVisible(false);
        darkPane.setVisible(false);
    }
}

