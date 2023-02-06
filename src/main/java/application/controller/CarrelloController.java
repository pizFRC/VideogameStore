package application.controller;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import application.SceneHandler;
import application.client.Client;
import application.model.Game;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CarrelloController extends AnimationTimer{
    private ArrayList<Game> aggiuntiCarrello;
	private long previousTime = 0;
	private long frequency = 1000 * 1000000;
	 @FXML
	    private Label prezzoTotLabel;

	    @FXML
	    private VBox vboxRiepilogoOrdineItem;

	    @FXML
	    private VBox vboxCarrello;


	    @FXML
	    private ScrollPane scrollPane;
	    @FXML
	    void procediAcquisto(ActionEvent event) {
          try {
			SceneHandler.getInstance().showPaymentForm();
		} catch (Exception e) {
			SceneHandler.getInstance().showMessage("Errore non è possibile procedere con il pagamento riprova più tardi","Errore", AlertType.ERROR);
		}
	    }

	    @FXML
		void initialize() {
		aggiuntiCarrello=Client.getInstance().getGameCarrello();
		if(Client.getInstance().getSizeProdottiCarrello()>0)
		setGame();
	
	}
	
	
	private void setGame() {
		
		vboxCarrello.prefWidthProperty().bind(	scrollPane.widthProperty());
		   aggiuntiCarrello=Client.getInstance().getGameCarrello();
		  	 DecimalFormat decimalFormat = new DecimalFormat("#.00");
    	   
    	   vboxCarrello.getChildren().clear();
    	   vboxRiepilogoOrdineItem.getChildren().clear();
       float tot=0;
		for(Game g :aggiuntiCarrello )
		{
			try {

				FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/GameItemCarrello.fxml"));
				Parent root=(Parent)loader.load();
				
				HBox b=(HBox)root;
				GameCarrelloController  controller=loader.getController();
				controller.setData(g);
	     b.prefWidthProperty().bind(vboxCarrello.widthProperty());
				vboxCarrello.getChildren().add(b);
				HBox item=new HBox();
				
				Label nome=new Label(g.getNome());
				HBox.setMargin(nome,new Insets(10,10,10,10));
				Label produttore=new Label(g.getProduttore());
				HBox.setMargin(produttore,new Insets(10,10,10,10));
				Label prezzo=new Label(decimalFormat.format(g.getPrezzo()));
				prezzo.setAlignment(Pos.CENTER_RIGHT);
				HBox.setMargin(prezzo,new Insets(10,10,10,10));
				item.getChildren().addAll(nome,produttore,prezzo);
				item.setAlignment(Pos.CENTER_LEFT);
				vboxRiepilogoOrdineItem.getChildren().add(item);
				tot+=g.getPrezzo();
				
			} catch (IOException e) {
			try {
				SceneHandler.getInstance().showErrorPage();
			} catch (Exception e1) {
				System.err.println("errore durante il caricamento di errorpage");
			}
			}
			
		}

 
		 
		if(tot!=0)
		prezzoTotLabel.setText(decimalFormat.format(tot));
		else
			prezzoTotLabel.setText(String.valueOf("0"));
	}

	@Override
	public void handle(long now) {
		 if(now - previousTime >= frequency ) {	
			     setGame();
			 previousTime=now;
		 }
	}
}

