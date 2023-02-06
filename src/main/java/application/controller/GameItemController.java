package application.controller;



import java.text.DecimalFormat;


import application.SceneHandler;
import application.client.Client;
import application.model.Game;
import application.util.imageReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class GameItemController {


	 @FXML
	    private ImageView copertinaImg;

	    @FXML
	    private Label produttoreLabel;

	    @FXML
	    private Label dataLabel;

	    @FXML
	    private Label prezzoLabel;

	    @FXML
	    private Button addCarrelloButton;

	    @FXML
	    private Label nomeLabel;

	    @FXML
	    private Button likeButton;


    private Game tmp;
    public void setData(Game g) {
    	tmp=g;
    	
    	
   	 produttoreLabel.setText("Produttore:"+g.getProduttore());
   	 
   	 dataLabel.setText("Data Uscita:"+g.getData().toString());
   	 nomeLabel.setText("Nome:"+g.getNome());
  
   	 imageReader reader=new imageReader();
 
		 float prezzo=g.getPrezzo();
		 DecimalFormat decimalFormat = new DecimalFormat("#.00");
		 String result=decimalFormat.format(prezzo);

   	prezzoLabel.setText(result);
   	
       try {
    	   copertinaImg.setImage(reader.byteToImg(g.getImg()));
		} catch (Exception e) {
			System.err.println("errore durante il caricamento del game :"+g.getNome());
		}
    }
    
    @FXML
    void like(ActionEvent event) {
       Client.getInstance().addPreferenza(tmp);

       
   Client.getInstance().aggiungiPreferito(tmp);

    }

    @FXML
    void addCarrello(ActionEvent event) {
      if(!Client.getInstance().addGameCarrello(tmp))
    	  SceneHandler.getInstance().showMessage("Il gioco selezionato risulta essere già in tuo possesso.  ","Errore", AlertType.CONFIRMATION);
      

   }
    
}