package application.controller;



import java.text.DecimalFormat;

import application.SceneHandler;
import application.client.Client;
import application.model.Game;
import application.util.imageReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class GameCarrelloController {
	private Game tmp;
    @FXML
    private Label etàLabel;

    @FXML
    private Label produttoreLabel;

    @FXML
    private Label genereLabel;

    @FXML
    private Label prezzoLabel;

    @FXML
    private Label dataLabel;

    @FXML
    private Label nomeLabel;

    @FXML
    private ImageView imgGame;

    @FXML
    void deleteGame(ActionEvent event) {
Client.getInstance().removeGamesCarrello(tmp);
    }

    public void setData(Game g) {
    	
    	 DecimalFormat decimalFormat = new DecimalFormat("#.00");
		 
    	tmp=g;
   	 produttoreLabel.setText("Produttore:"+g.getProduttore());
   	 genereLabel.setText("Genere:"+g.getGenere());
   	 dataLabel.setText("Data Uscita:"+g.getData().toString());
   	 nomeLabel.setText("Nome:"+g.getNome());
   	 etàLabel.setText("Età:3");
   	 imageReader reader=new imageReader();
   	prezzoLabel.setText("Prezzo:"+   decimalFormat.format(g.getPrezzo()));
       try {
			imgGame.setImage(reader.byteToImg(g.getImg()));
		} catch (Exception e) {
			SceneHandler.getInstance().showMessage("Errore :i prodotti nel carrello non sono disponibili","Errore", AlertType.ERROR);
		}
    }

}
