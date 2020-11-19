package sample;

import business.log.threads.ManageAudit;
import business.services.ForumTopicService;
import comuns.access.Audit;
import comuns.access.ForumComment;
import comuns.access.ForumTopic;
import dao.access.ForumCommentSqlServerDAO;
import dao.access.ForumTopicSqlServerDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerForum implements Initializable {

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
    Pane darkPane, topicsPane;




    ForumTopicSqlServerDAO topicDAO = new ForumTopicSqlServerDAO();
    ForumTopicService topicService = new ForumTopicService();
    ForumCommentSqlServerDAO commentDAO = new ForumCommentSqlServerDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            var list = topicService.listTopics();
            list.forEach((topic) -> {
                ComponentForumTopicController forumTopic = new ComponentForumTopicController();
                forumTopic.setTitle(topic.getTitle());
                forumTopic.setDiscussion(topic.getDiscussion());
                topicsPane.getChildren().add(forumTopic);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveNewTopic(MouseEvent mouseEvent) {
        try {
            var topic = new ForumTopic(txtTitle.getText(), txtDiscussion.getText());
            topic.setUserId(22);

             if(topicDAO.insert(topic)) {
                 JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                 newTopicPane.setVisible(false);
                 darkPane.setVisible(false);

                 Audit audit = new Audit();
                 audit.setUserId("22");
                 audit.setAction("Novo Tópico");
                 ManageAudit.getInstance().addAudit(audit);
                 ManageAudit.getInstance().activate();
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
            comment.setForumTopicId(22);
            comment.setUserId(22);

            if (commentDAO.insert(comment)) {
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                answerTopicPane.setVisible(false);
                darkPane.setVisible(false);

                Audit audit = new Audit();
                //audit.setUserId(null);
                audit.setAction("Novo Comentário");
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

