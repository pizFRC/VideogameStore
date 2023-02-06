package application.controller;


import application.SceneHandler;
import application.client.Client;
import application.util.imageReader;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class PannelloPrincipale extends AnimationTimer {

	private long previousTime = 0;
	private long frequency = 1000 * 1000000;
	

	
	@FXML
    private Button downloadButton;

    @FXML
    private ImageView imgProfilo;

    @FXML
    private Button chatButton;

    @FXML
    private Button carrelloButton;

    @FXML
    private BorderPane root;

    @FXML
    private VBox vboxLeft;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button cercaButton;

  

    @FXML
    private Button profileImgButton;

    @FXML
    private Button homeButton;

    
	    private boolean aggiorna;
	    @FXML
		void initialize(){
    	
    	 aggiorna=Client.getInstance().getStatus();
  
		
    }
    
   
    @FXML
    void setProfilo(ActionEvent event) {
    	try {
			SceneHandler.getInstance().setProfilo();
		} catch (Exception e) {
			try {
				SceneHandler.getInstance().showErrorPage();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
      
   
    }

    @FXML
    void changeHome(ActionEvent event) {
      try {

		SceneHandler.getInstance().setHomePage();
	} catch (Exception e) {
		try {
			SceneHandler.getInstance().showErrorPage();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
    }
    @FXML
    void showCerca(ActionEvent event) {
       try {
    	   
		SceneHandler.getInstance().showCercaGames();
	} catch (Exception e) {
		try {
			SceneHandler.getInstance().showErrorPage();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
    }
    @FXML
    void logout(ActionEvent event) {
  
    try {
    	 
   
    	 SceneHandler.getInstance().close();
			
		
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
    
    
    @FXML
    void showChat(ActionEvent event) {
try {
	SceneHandler.getInstance().showChat();
} catch (Exception e) {
	try {
		SceneHandler.getInstance().showErrorPage();
	} catch (Exception e1) {
		e1.printStackTrace();
	}
}
    }
    
    @FXML
    void showCarrello(ActionEvent event) {
   try {
	SceneHandler.getInstance().showCarrello();
} catch (Exception e) {
	try {
		SceneHandler.getInstance().showErrorPage();
	} catch (Exception e1) {
		e1.printStackTrace();
	}
}
    }
    
    
    
    
    @FXML
    void showLibreria(ActionEvent event) { 
    	try {
			SceneHandler.getInstance().showLibreriaAcquisti();
		} catch (Exception e) {
			try {
				SceneHandler.getInstance().showErrorPage();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
    }
	public void handle(long now) {
		 if(now - previousTime >= frequency  && aggiorna ) {
			
			 aggiorna=Client.getInstance().getStatus();
			 if(!aggiorna) {
				 try {
					SceneHandler.getInstance().showErrorPage();
					
				} catch (Exception e) {
					this.stop();
					return;
				}
				 this.stop();
				 return;
			 }
			
			 carrelloButton.setStyle("-fx-text-fill:white;");
			
			
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
				} catch (Exception e) {
					
					try {
						SceneHandler.getInstance().showErrorPage();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
					
				}
				
			 
			 previousTime = now;
		 }
		
	
}
	private void setImgProfilo(Image img) {
	imgProfilo.setImage(img);	
imgProfilo.setFitWidth(125);
	
	}
	
	
	
}
