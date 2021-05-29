package application;



import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneHandler {

private  Scene main;
private  Stage stage;
private static SceneHandler instance=null;

private SceneHandler() {
	
}
public static SceneHandler getIstance(){
	if(instance==null)
		instance=new SceneHandler();
	return instance;
}

private void setCSS() {
    main.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

}
public  void init(Stage stage) throws Exception {
	  this.stage=stage;
	  stage.initStyle(StageStyle.UNDECORATED);
	setLogin();
    stage.setTitle("Game Store Management - Login");
   
   setCSS();
    stage.show();
    
}


public void close() {
	stage.close();
}
public void iconified() {
	stage.setIconified(true);
}
public  void setRegistrazione() throws Exception {
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Registrazione.fxml"));
	Parent root = (Parent)loader.load();  
    main=new Scene(root);

    setCSS();
    stage.setScene(main);
   
   
	
    stage.setResizable(false);
	stage.show();
	
	
}
public  void setLogin() throws Exception {
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
	
	AnchorPane root = (AnchorPane) loader.load();
	
	
	 main=new Scene(root);

	   setCSS();
	 
	    
     
	stage.setScene(main);
	
	
	stage.setResizable(false);
	 
	 
	stage.show();
	
	
}
public void showError(String message,AlertType tipo) {
	Alert a=new Alert(tipo);
	a.setTitle("Errore");
	a.setContentText(message);
	a.setHeaderText("");
	a.showAndWait();
}
public void setHome() {
	// TODO Auto-generated method stub
	
}
}
