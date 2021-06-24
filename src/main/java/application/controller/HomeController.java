package application.controller;


import application.SceneHandler;
import application.client.Client;
import application.client.Messages;
import application.client.Protocol;
import application.model.imageReader;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HomeController extends AnimationTimer {

	private long previousTime = 0;
	private long frequency = 1000 * 1000000;
	private double xOffSet=0;
	private double yOffSet=0;
	

	
	private Stage stage;
	  @FXML
	    private Button downloadButton;

	    @FXML
	    private ImageView imgProfilo;

	    @FXML
	    private Button chatButton;

	    @FXML
	    private BorderPane root;

	    @FXML
	    private VBox vboxLeft;

	    @FXML
	    private Label usernameLabel;

	    @FXML
	    private Button impostazioniButton;

	    @FXML
	    private Button profileImgButton;

	    @FXML
	    private Button homeButton;
	    @FXML
	    private Button carrelloButton;
    
	    private boolean aggiorna;
    public void initialize() {
    	
    	 aggiorna=Client.getInstance().getStatus();
    // Client.getInstance().getGameHomeVenduti();
   //  Client.getInstance().getAllGame();
   
		
    }
    
   
    @FXML
    void setProfilo(ActionEvent event) {
    	try {
			SceneHandler.getInstance().setProfilo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
   
    }

    @FXML
    void changeHome(ActionEvent event) {
      try {// System.out.println(Client.getInstance().getGameHomeVenduti());

		SceneHandler.getInstance().setHomePage();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    @FXML
    void showCerca(ActionEvent event) {
       try {
    	   System.out.println("cerca premuto");
		SceneHandler.getInstance().showCercaGames();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    @FXML
    void logout(ActionEvent event) {
  
    try {
    	 
   
    	 SceneHandler.getInstance().close();
			
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    @FXML
    void showChat(ActionEvent event) {
try {
	SceneHandler.getInstance().showChat();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    }
    
    @FXML
    void showCarrello(ActionEvent event) {
   try {
	SceneHandler.getInstance().showCarrello();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    }
    @FXML
    void showLibreria(ActionEvent event) { 
    	try {
			SceneHandler.getInstance().showLibreriaAcquisti();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void handle(long now) {
		 if(now - previousTime >= frequency  && aggiorna ) {
			
			 aggiorna=Client.getInstance().getStatus();
			 if(!aggiorna) {
				 try {
					SceneHandler.getInstance().showErrorPage();
					
				} catch (Exception e) {
					
				}
				 this.stop();
				 return;
			 }
			
				
			
			
			 //chatButton.setText(String.valueOf(Messages.getSizeNuoviMessaggi()));
			  imageReader imgR=new imageReader();
				Image imgP;
				
				try {
					int size=Client.getInstance().getSizeProdottiCarrello();
					if(size>0)
					carrelloButton.setText(String.valueOf(size));
					else
						carrelloButton.setText("");
					imgP = imgR.byteToImg(Client.getInstance().getAccount().getImg());
					 setImgProfilo(imgP);
					 usernameLabel.setText("@"+Client.getInstance().getAccount().getUsername());
					// SceneHandler.getInstance().setHomePage();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					try {
						SceneHandler.getInstance().showErrorPage();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					System.out.println("prova nel home");
				}
				
			 
			 previousTime = now;
		 }
		
	
}
	private void setImgProfilo(Image img) {
	imgProfilo.setImage(img);	
	}
	public void setStage(Stage stage) {
		this.stage=stage;
		
	}
	
	
}
