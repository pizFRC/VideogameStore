package application.controller;





import application.SceneHandler;
import application.client.Client;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {

	private Stage stage;
	  

    @FXML
    private VBox prova;
	
    @FXML
    private TextField usernameField;

    @FXML
    private Button recuperoPWButton;
    
    @FXML
    private Button registrazioneButton;
    
    @FXML
    private ImageView logoImg;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;
    
    @FXML
    private ImageView leftImg;
    
    @FXML
    private Button reduceButton;

    @FXML
    private Button closeButton;
    
    public void addStage(Stage stage) {
    	this.stage=stage;
    	
    }
    @FXML
    void reduceWindow(ActionEvent event) {
         stage.setIconified(true);
    }

    @FXML
    void closeWindow(ActionEvent event) {
          stage.close();
    }
    
    @FXML
    void loginClicked(ActionEvent event) {
        Client.getInstance();
        
       }
    @FXML
    void recuperoPW(ActionEvent event) {
    	SceneHandler.getIstance().setCurrent("Registrazione");
    }
    

    @FXML
    void registraUtente(ActionEvent event) {

    }

   
    public void initialize() {
    	
    	 
    	 Image bgLeft= new Image(getClass().getResourceAsStream("/img/bgl.png"));
    	Image logo=new Image(getClass().getResourceAsStream("/icons/logo.jpg"));
         logoImg.setImage(logo);
         leftImg.setImage(bgLeft);
        
    
    	
    	
    }
    

}



