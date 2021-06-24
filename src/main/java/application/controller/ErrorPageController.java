package application.controller;

import application.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ErrorPageController {

    @FXML
    private Button exitButton;

    @FXML
    void exit(ActionEvent event) {
  SceneHandler.getInstance().close();
    }

}
