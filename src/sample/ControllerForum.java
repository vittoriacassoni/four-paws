package sample;

import business.log.threads.ManageAudit;
import business.services.ForumCommentService;
import business.services.ForumTopicService;
import business.singleton.LocalStorage;
import comuns.access.Audit;
import comuns.access.ForumComment;
import comuns.access.ForumTopic;
import dao.access.ForumCommentSqlServerDAO;
import dao.access.ForumTopicSqlServerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.beans.EventHandler;
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

public class ControllerForum implements Initializable {

    //region Variáveis FXML
    @FXML
    Pane newTopicPane, answerTopicPane, darkPane;

    @FXML
    TextField txtTitle, txtComment, txtTitleFilter;

    @FXML
    TextArea txtDiscussion;

    @FXML
    Label lblTitleTopic, lblNoComment, lblNoTopic;

    @FXML
    ListView<ComponentForumTopicController> topicsPane;

    @FXML
    ListView<ComponentForumCommentController> commentsPane;
    //endregion

    //region Services
    ForumTopicService topicService = new ForumTopicService();
    ForumCommentService commentService = new ForumCommentService();
    //endregion

    //region Variáveis gerais
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Integer currentTopic = 0;
    Integer userId = LocalStorage.getInstance().getUserId();
    String userFullName = LocalStorage.getInstance().getUserName() + " " + LocalStorage.getInstance().getUserLastName();
    //endregion

    public ControllerForum() throws IOException, SQLException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listTopics("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Método para listar os tópicos por ordem de data e podendo filtrar por título
    private void listTopics(String titleFilter) {
        try{
            topicsPane.getItems().clear();
            var list = topicService.listTopics(titleFilter);

            if(list.size() > 0){
                lblNoTopic.setVisible(false);
                List<ComponentForumTopicController> itemsComponent = new ArrayList<ComponentForumTopicController>();

                list.forEach((topic) -> {
                    ComponentForumTopicController forumTopic = null;
                    try {
                        forumTopic = new ComponentForumTopicController(showAnswerPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    forumTopic.setTitle(topic.getTitle());
                    forumTopic.setDiscussion(topic.getDiscussion());
                    forumTopic.setTopicId(topic.getId());
                    forumTopic.setDate(dateFormat.format(topic.getCreatedAt()));
                    forumTopic.setUser(topic.getUserName());
                    itemsComponent.add(forumTopic);
                });
                ObservableList<ComponentForumTopicController> items = FXCollections.observableList(itemsComponent);
                topicsPane.setItems(items);
            } else{
                lblNoTopic.setVisible(true);
            }
        } catch(Exception error){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
        }
    }

    //Método para listar os tópicos filtrados por título quando clicar na lupa na tela
    public void listTopicsByTitle(MouseEvent mouseEvent) {
        try{
            var filter = txtTitleFilter.getText();
            listTopics(filter);
        } catch (Exception error){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro!");
            alert.setHeaderText(null);
            alert.setContentText(error.getMessage());
            alert.showAndWait();
        }
    }

    //Método para criar um novo tópico e mostrar em tela
    public void saveNewTopic(MouseEvent mouseEvent) {
        try {
            var topic = new ForumTopic(txtTitle.getText(), txtDiscussion.getText());
            topic.setUserId(userId);

            if (topicService.insert(topic)) {
                clearTextFields();

                ComponentForumTopicController forumTopic = new ComponentForumTopicController(showAnswerPane);
                forumTopic.setTitle(topic.getTitle());
                forumTopic.setDiscussion(topic.getDiscussion());
                forumTopic.setTopicId(topic.getId());
                forumTopic.setDate(dateFormat.format(Date.from(Instant.now())));
                forumTopic.setUser(userFullName);
                topicsPane.getItems().add(0, forumTopic);
                lblNoTopic.setVisible(false);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso!");
                alert.setHeaderText(null);
                alert.setContentText("Salvo com sucesso!");
                alert.showAndWait();

                closeTopicPane(mouseEvent);

                Audit audit = new Audit();
                audit.setUserId(String.valueOf(userId));
                audit.setAction("Novo Tópico");
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

    //Método para criar um novo comentário e mostrar em tela
    public void sendNewComment(MouseEvent mouseEvent) {
        try {
            var comment = new ForumComment(txtComment.getText());
            comment.setForumTopicId(currentTopic);
            comment.setUserId(userId);

            if (commentService.insert(comment)) {
                clearTextFields();

                ComponentForumCommentController forumComment = new ComponentForumCommentController();
                forumComment.setAnswer(comment.getDiscussion());
                forumComment.setUser(userFullName);
                forumComment.setDate(dateFormat.format(Date.from(Instant.now())));

                commentsPane.getItems().add(forumComment);
                lblNoComment.setVisible(false);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso!");
                alert.setHeaderText(null);
                alert.setContentText("Salvo com sucesso!");
                alert.showAndWait();

                Audit audit = new Audit();
                audit.setUserId(String.valueOf(userId));
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

    //region Métodos para controlar modal de novo tópico
    //Método para abrir pane de novo tópico
    public void showTopicPane(MouseEvent mouseEvent) throws IOException {
        newTopicPane.setVisible(true);
        darkPane.setVisible(true);
    }

    //Método para fechar pane de novo tópico
    public void closeTopicPane(MouseEvent mouseEvent) throws IOException {
        newTopicPane.setVisible(false);
        darkPane.setVisible(false);
    }
    //endregion

    //region Métodos para controlar modal de respostas de um tópico
    //Método para abrir pane de resposta do tópico escolhido
    javafx.event.EventHandler<MouseEvent> showAnswerPane = new javafx.event.EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            try{
                commentsPane.getItems().clear();
                ComponentForumTopicController topic = (ComponentForumTopicController) ((Label) event.getSource()).getParent().getParent();
                currentTopic = topic.getTopicId();
                lblTitleTopic.setText(topic.getTitle());
                lblTitleTopic.setAlignment(Pos.CENTER);

                listCommentsByTopicId();

                answerTopicPane.setVisible(true);
                darkPane.setVisible(true);
            } catch (Exception error){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erro!");
                alert.setHeaderText(null);
                alert.setContentText(error.getMessage());
                alert.showAndWait();
            }
        }
    };

    //Método para fechar pane de resposta do tópico escolhido
    public void closeAnswerPane(MouseEvent mouseEvent) throws IOException {
        answerTopicPane.setVisible(false);
        darkPane.setVisible(false);
    }
    //endregion

    // Método para listar os comentários de um tópico e mostrar em tela
    private void listCommentsByTopicId() {
        ArrayList<ForumComment> list = null;
        try {
            list = commentService.listComments(currentTopic);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(list.size() > 0){
            lblNoComment.setVisible(false);
            List<ComponentForumCommentController> itemsComponent = new ArrayList<ComponentForumCommentController>();

            list.forEach((comment) -> {
                ComponentForumCommentController forumComment = null;
                try {
                    forumComment = new ComponentForumCommentController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                forumComment.setAnswer(comment.getDiscussion());
                forumComment.setUser(comment.getUserName());
                forumComment.setDate(dateFormat.format(comment.getCreatedAt()));
                itemsComponent.add(forumComment);
            });
            ObservableList<ComponentForumCommentController> items = FXCollections.observableList(itemsComponent);
            commentsPane.setItems(items);
        } else{
            lblNoComment.setVisible(true);
        }
    }

    //Método para limpar todos os inputs da tela após salvar algo
    private void clearTextFields(){
        txtComment.clear();
        txtDiscussion.clear();
        txtTitle.clear();
    }

}

