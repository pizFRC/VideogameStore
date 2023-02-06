package application.controller;



import java.io.File;

import application.client.Client;
import application.model.Game;
import application.util.imageReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class ProfiloClientController {

    @FXML
    private TextField usernameField;

    @FXML
    private ImageView imgProfilo;


    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;
    
    @FXML
    private ListView<Game> listaPreferenze;
    
    @FXML
   	void initialize() {
    imageReader reader=new imageReader();
   Image img;
try {
	

	ObservableList<Game> obsGames = FXCollections.observableArrayList(Client.getInstance().getGamePreferiti());
	if(!obsGames.isEmpty())
		listaPreferenze.getItems().setAll(obsGames);
	
	
	String[]info=Client.getInstance().getInfo();
	img = reader.byteToImg(Client.getInstance().getAccount().getImg());
	imgProfilo.setImage(img);
	imgProfilo.setFitWidth(150);
	imgProfilo.setFitHeight(150);
	usernameField.setText(info[0]);
	
	emailField.setText(info[2]);
	phoneField.setText(info[3]);
	
} catch (Exception e) {
	e.printStackTrace();
}
    	
    }
    
    @FXML
    void changeImg(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
         fileChooser.getExtensionFilters().addAll(
        			new FileChooser.ExtensionFilter("IMAGE files  (*.png,*.jpeg,*.jpg)", "*.png","*.jpeg","*.jpg"));
        	fileChooser.setTitle("Seleleziona un'immagine del profilo");
	
		 File file = 	fileChooser.showOpenDialog(null);
		 imageReader imgReader=new imageReader();
		
		
			
		 if(file==null || (!file.canRead()))
			 return;
		  try {
			byte[] img=imgReader.read(file.getPath());
			
		
			 
			Client.getInstance().changeImg(img);
			imgProfilo.setImage(imgReader.byteToImg(img));
			imgProfilo.setFitWidth(150);
			imgProfilo.setFitHeight(150);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	    
    }
}
