package application.controller;





import application.SceneHandler;
import application.client.Client;
import application.client.Protocol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {

	
	  

	
    @FXML
    private TextField usernameField;

    
    @FXML
    private Button registrazioneButton;
    
    @FXML
    private ImageView logoImg;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;
    
    
    @FXML
    private Button reduceButton;

    @FXML
    private Button closeButton;
   
    
    @FXML
    void reduceWindow(ActionEvent event) {
    	 SceneHandler.getInstance().iconified();
    	 
  
    }

    @FXML
    void closeWindow(ActionEvent event) {
         SceneHandler.getInstance().close();
    }
    
    @FXML
    void loginClicked(ActionEvent event) {
    	login(usernameField.getText(),passwordField.getText());
        
       }
    
    

    @FXML
    void registraUtente(ActionEvent event) {
    	 try {
 			SceneHandler.getInstance().setRegistrazione();
 			
 		} catch (Exception e) {
 			SceneHandler.getInstance().showMessage("Errore durante il caricamento di registrazione","Errore", AlertType.ERROR);
 			e.printStackTrace();
 		}
    }

   
    @FXML
   	void initialize(){
    	
    	 
    	 
    	Image logo=new Image(getClass().getResourceAsStream("/img/log.gif"));
         logoImg.setImage(logo);
         
    
    	
    	
    }
    
    private void login(String us,String pas){
    	
    	String serverRes=Client.getInstance().login(us, pas);
        if(serverRes.equals(Protocol.OK)) {
     	     try {
     	    	
     	    	 SceneHandler.getInstance().setHome();
     	     }catch(Exception e) {
     	    	Client.getInstance().reset();
     	    
     	     }
     	     
     	    
            }else if(serverRes.equals(Protocol.ERROR)){ 
            	
            	 Client.getInstance().reset();
             	
             	return;
            }
            else  if(serverRes.equals(Protocol.USER_LOGGED_ERROR)){
            	SceneHandler.getInstance().showMessage(Protocol.USER_LOGGED_ERROR,"Errore",AlertType.ERROR);
            	Client.getInstance().reset();
            	return;
            }else {
        	
        	SceneHandler.getInstance().showMessage(Protocol.AUTHENTICATION_ERROR,"Errore",AlertType.ERROR);
     	   Client.getInstance().reset();
     	   return;
        }
        
    }
    

}



