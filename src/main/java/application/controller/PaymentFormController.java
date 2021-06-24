package application.controller;

import javafx.fxml.FXML;
import application.SceneHandler;
import application.client.Client;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PaymentFormController {
    private Stage stage;
    @FXML
    private TextField nomeField;

    @FXML
    void annulla(ActionEvent event) {
     stage.close();
    }

    @FXML
    void confermaAcquisto(ActionEvent event) {
    	SceneHandler.getInstance().showError("acquisto confermato", AlertType.INFORMATION);



	Client.getInstance().svuotaCarrello();
	

stage.close();
    }

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
    
    
}