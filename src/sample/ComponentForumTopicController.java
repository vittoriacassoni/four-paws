package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ComponentForumTopicController extends Pane {
    @FXML
    private Label lblTitle, lblDiscussion, lblAnswer, lblTopicId, lblDate, lblUser;

    private String title = "";

    public ComponentForumTopicController(javafx.event.EventHandler<MouseEvent> answerTopic) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("components/ComponentForumTopic.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            lblAnswer.setOnMouseClicked(answerTopic);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setTitle(String value) {
       lblTitle.setText(value);
       title = value;
    }

    public void setDiscussion(String value) {
       lblDiscussion.setText(value);
    }

    public void setTopicId(Integer topicId){ lblTopicId.setText(String.valueOf(topicId)); }

    public void setDate(String date){ lblDate.setText(date); }

    public void setUser(String userName){
        lblUser.setText(userName);
        lblUser.setAlignment(Pos.CENTER_RIGHT);
    }

    public Integer getTopicId(){  return Integer.valueOf(lblTopicId.getText()); }

    public String getTitle(){ return lblTitle.getText(); }
}
