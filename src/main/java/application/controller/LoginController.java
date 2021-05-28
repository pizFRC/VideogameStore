package application.controller;





import application.SceneHandler;
import application.client.Client;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {

	
	  

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
    private PasswordField passwordField;
    @FXML
    private ImageView leftImg;
    
    @FXML
    private Button reduceButton;

    @FXML
    private Button closeButton;
    
    
    @FXML
    void reduceWindow(ActionEvent event) {
    	 SceneHandler.getIstance().iconified();
    	 
  
    }

    @FXML
    void closeWindow(ActionEvent event) {
         SceneHandler.getIstance().close();
    }
    
    @FXML
    void loginClicked(ActionEvent event) {
      Client.getInstance();
       
        
       }
    @FXML
    void recuperoPW(ActionEvent event) {
    	
    }
    

    @FXML
    void registraUtente(ActionEvent event) {
    	 try {
 			SceneHandler.getIstance().setRegistrazione();
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }

   
    public void initialize() {
    	
    	 
    	 Image bgLeft= new Image(getClass().getResourceAsStream("/img/bgl.png"));
    	Image logo=new Image(getClass().getResourceAsStream("/img/logo.png"));
         logoImg.setImage(logo);
         leftImg.setImage(bgLeft);
        
    
    	
    	
    }
    

}



