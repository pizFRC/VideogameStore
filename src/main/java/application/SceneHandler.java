package application;




import java.io.IOException;

import application.client.Client;
import application.client.Messages;
import application.controller.CarrelloController;
import application.controller.CercaGameController;
import application.controller.ChatController;
import application.controller.HomeController;
import application.controller.HomePageController;
import application.controller.PaymentFormController;
import application.model.AccountLogin;
import application.model.imageReader;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneHandler {

private  Scene main;
private  Stage stage;
private static SceneHandler instance=null;
private boolean isInChat=false;

private SceneHandler() {
	
}
public static SceneHandler getInstance(){
	if(instance==null)
		instance=new SceneHandler();
	return instance;
}

private void setCSS() {
    main.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
      

}
public  void init(Stage stage) throws Exception {
	  this.stage=stage;
	   
     
	  //stage.initStyle(StageStyle.UNDECORATED);
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
   // stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(main);
   
	
    stage.setResizable(false);
	stage.show();
	
	
}
public  void setHome() throws Exception {
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
	BorderPane root = (BorderPane)loader.load();  
	
    main=new Scene(root);
    
    HomeController controller=loader.getController();
	controller.setStage(stage);
	Thread t = new Thread(Client.getInstance());
	t.setDaemon(true);
	t.start();

	controller.start();
	FXMLLoader loader2=new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"));
	stage.setOnHidden(e -> {
		  
		  Platform.exit();
		});
	Parent home = (Parent)loader2.load(); 
	root.setCenter(home);
   setCSS();
   
   
    stage.setScene(main);

	
    stage.setResizable(true);
   
	stage.show();
	
	
	
}
public void setHomePage()throws Exception{
	System.out.println(main.getRoot());
	BorderPane p=(BorderPane) main.getRoot();
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/homepage.fxml"));
	Parent home = (Parent)loader.load(); 
	p.setCenter(home);
	
		
	
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
public void showErrorPage() throws Exception {
	BorderPane p=(BorderPane) main.getRoot();
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/errorePage.fxml"));
	Parent home = (Parent)loader.load(); 
	BorderPane root=(BorderPane)main.getRoot();
	System.out.println(root);
	VBox left=(VBox)root.getLeft();
	left.setDisable(true);
	p.setCenter(home);
}
public void showLibreriaAcquisti() throws Exception {
	BorderPane p=(BorderPane) main.getRoot();
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/libreria.fxml"));
	Parent home = (Parent)loader.load(); 
	
	p.setCenter(home);
}

public void showError(String message,AlertType tipo) {
	Alert a=new Alert(tipo);
	a.setTitle("Errore");
	a.setContentText(message);
	a.setHeaderText("");
	a.show();
}
public void setProfilo() throws Exception {
	System.out.println(main.getRoot());
	BorderPane p=(BorderPane) main.getRoot();
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/profiloClient.fxml"));
	Parent home = (Parent)loader.load(); 
	p.setCenter(home);

	
}
public void setImgNameProfilo(String username, byte[] img) {
	System.out.println(main.getRoot());
	BorderPane p=(BorderPane) main.getRoot();
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
	

    
     
	
}
public void showCercaGames() throws Exception {
	System.out.println(main.getRoot());
	BorderPane p=(BorderPane) main.getRoot();
	p.setCenter(null);
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/cercaGame.fxml"));
	
	Parent home = (Parent)loader.load(); 
	//CercaGameController controller=loader.getController();
	//controller.start();
	p.setCenter(home);
	 System.out.println("nuovi nessaggi?");
	
}
public void showChat() throws Exception {
	System.out.println(main.getRoot());
	BorderPane p=(BorderPane) main.getRoot();
	p.setCenter(null);
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Chat.fxml"));

	Parent home = (Parent)loader.load(); 
	
	
	ChatController controller=loader.getController();
	
	controller.start();
    
   
	p.setCenter(home);
	
	
}
public void showCarrello() throws Exception {
	System.out.println(main.getRoot());
	BorderPane p=(BorderPane) main.getRoot();
	p.setCenter(null);
	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/carrello.fxml"));

	Parent home = (Parent)loader.load(); 
	
	
	CarrelloController controller=loader.getController();
	controller.start();

    
   
	p.setCenter(home);
	
	
}
public void showPaymentForm() throws Exception {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/paymentForm.fxml"));
Parent root = (Parent) loader.load();
Stage tmpStage = new Stage();
PaymentFormController controller=loader.getController();
controller.setStage(tmpStage);
tmpStage.initModality(Modality.APPLICATION_MODAL);
tmpStage.setScene(new Scene(root, 400, 400));
tmpStage.showAndWait();	
}



}
