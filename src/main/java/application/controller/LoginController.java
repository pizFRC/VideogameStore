package application.controller;





import application.SceneHandler;
import application.client.Client;
import application.client.Protocol;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {

	
	  

	
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
    void recuperoPW(ActionEvent event) {
    	System.out.println("recupero pw");
   Client.getInstance().recPW();
    }
    

    @FXML
    void registraUtente(ActionEvent event) {
    	 try {
 			SceneHandler.getInstance().setRegistrazione();
 			
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
    
    private void login(String us,String pas){
    	//System.out.println("login pressed");
    	
    	String serverRes=Client.getInstance().login(us, pas);
    	System.out.println("login res " +serverRes);
        if(serverRes.equals(Protocol.OK)) {
     	     try {
     	    	
     	    	 SceneHandler.getInstance().setHome();
     	     }catch(Exception e) {
     	    	System.out.println(e.getMessage());
     	    	Client.getInstance().reset();
     	    	//SceneHandler.getIstance().showError("Errore caricamento Store",AlertType.ERROR);
     	    
     	     }
     	     
     	    
            }else if(serverRes.equals(Protocol.ERROR)){ 
            	
            	// Client.getInstance().reset();
             	System.out.println("errore intercettato" );
             	
             	return;
            }
            else  if(serverRes.equals(Protocol.USER_LOGGED_ERROR)){
            	SceneHandler.getInstance().showError(Protocol.USER_LOGGED_ERROR,AlertType.ERROR);
            	Client.getInstance().reset();
            	return;
            }else {
        	
        	SceneHandler.getInstance().showError(Protocol.AUTHENTICATION_ERROR,AlertType.ERROR);
     	   Client.getInstance().reset();
     	   return;
        }
        
    }
    

}



