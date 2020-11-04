package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerForum {
@FXML Pane newTopicPane;

    public void loadPane(MouseEvent mouseEvent) throws IOException {
        newTopicPane.setVisible(true);
    }

    public void closePane(MouseEvent mouseEvent) throws IOException {
        newTopicPane.setVisible(false);
    }
}

