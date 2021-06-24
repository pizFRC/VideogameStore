package application.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import application.SceneHandler;
import application.client.Client;
import application.model.AccountLogin;
import application.model.Messaggio;
import application.client.Messages;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


	public class ChatController extends AnimationTimer{
     boolean aggiorna;
	    @FXML
	    private VBox messaggiVBox;

	    @FXML
	    private TextArea testoMessaggio;
	    @FXML
	    private HBox principale;
	    @FXML
	    private HBox messaggioHBox;
	    
	    @FXML
	    private ScrollPane scrollPane;

		private long previousTime = 0;
		private long frequency = 500 * 1000000;
		
		
		@FXML
	    private Separator sep;

	    @FXML
	    private BorderPane root;
	    
	    
	    @FXML
	    void sendMessage(ActionEvent event) {
	    	AccountLogin account=Client.getInstance().getAccount();
	    	String time=LocalTime.now().toString().substring(0, 5);
	    	Messaggio m=new Messaggio(account.getUsername(),time,testoMessaggio.getText(),account.getImg());
             if(!Client.getInstance().sendMessageChat(m))
            	SceneHandler.getInstance().showError("errore durante l'invio connessione persa",AlertType.ERROR);
             testoMessaggio.clear();
	    }

		@Override
		public void handle(long now) {
			 if(now - previousTime >= frequency  ) {
				
				  messaggiVBox.getChildren().clear();
				
				 ArrayList<Messaggio> all = Messages.readMessages();
					for(Messaggio m: all) {
						
					
				
				try {
					 FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/messaggio.fxml"));
					   Parent root = (Parent)loader.load();
					  // System.out.println("testo me"+m.getText());
					   MessaggioController controller=loader.getController();
					   controller.setDati(m.getUsername()+':'+m.getText(),m.getTime(),m.getImg());
					 if(m.getUsername().equals(Client.getInstance().getAccount().getUsername()))
					 {	
				HBox b=new HBox(root);
					b.setStyle("-fx-background-color:red");
				   b.prefWidthProperty().bind(scrollPane.widthProperty());		
						b.setStyle("-fx-border-color:red");
						b.setAlignment(Pos.TOP_RIGHT);
						
						  messaggiVBox.getChildren().add(b);
						 
						 continue;
					 }
					   messaggiVBox.getChildren().add(root);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				
					 
			 }
				
				 previousTime = now;
			 }
			
			
		}

	
		@FXML
		private void initialize() {
	aggiorna=Client.getInstance().getStatus();
			sep.prefWidthProperty().bind(root.widthProperty());
			sep.minWidthProperty().bind(root.minWidthProperty());
			
		      
		}

}
