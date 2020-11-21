package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ComponentForumCommentController extends Pane {
    @FXML
    Label lblAnswer, lblUser, lblDate;

    public ComponentForumCommentController() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("components/ComponentForumComment.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setAnswer(String value) {
        lblAnswer.setText(value);
    }

    public void setDate(String date){ lblDate.setText(date); }

    public void setUser(String userName){
        lblUser.setText(userName);
        lblUser.setAlignment(Pos.CENTER_RIGHT);
    }

}
