package application;

import application.controller.LoginController;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
		FXMLLoader loaderFinestraPrincipale=new FXMLLoader(getClass().getResource("/fxml/Registrazione.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		AnchorPane registrazione=(AnchorPane) loaderFinestraPrincipale.load();
		Scene scene = new Scene(root);


		SceneHandler.getIstance().init(scene,primaryStage);
		SceneHandler.getIstance().add("Login",root);
		
		SceneHandler.getIstance().add("Registrazione",registrazione);
		
		scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
		
	    LoginController controller=loader.getController();
	    controller.addStage(primaryStage);
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
