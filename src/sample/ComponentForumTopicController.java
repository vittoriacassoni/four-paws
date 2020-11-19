package sample;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;

public class ComponentForumTopicController extends Pane {
    @FXML
    Label lblTitle, lblDiscussion;

    public ComponentForumTopicController() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/sample/components/ComponentForumTopic.fxml"));
    }
    public void setTitle(String value) {
        lblTitle.setText(value);
    }
    public void setDiscussion(String value) {
        lblDiscussion.setText(value);
    }

}
