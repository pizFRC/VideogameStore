package application.controller;



import java.io.File;

import application.client.Client;
import application.client.Protocol;
import application.model.AccountLogin;
import application.model.imageReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TextField pwField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;
   
    
    public void initialize() {
    imageReader reader=new imageReader();
   Image img;
try {
	
	String[]info=Client.getInstance().getInfo();
	img = reader.byteToImg(Client.getInstance().getAccount().getImg());
	imgProfilo.setImage(img);
	usernameField.setText(info[0]);
	pwField.setText(info[1]);
	emailField.setText(info[2]);
	phoneField.setText(info[3]);
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    	
    }
    
    @FXML
    void changeImg(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
         fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("IMAGE files (*.png)", "*.png"),
        		new FileChooser.ExtensionFilter("IMAGE files (*.jpg)", "*.jpg")
        		);
  
		fileChooser.setTitle("Seleleziona un'immagine del profilo");
	
		 File file = 	fileChooser.showOpenDialog(null);
		 imageReader imgReader=new imageReader();
		
		
			
		 if(file==null || (!file.canRead()))
			 return;
		  try {
			byte[] img=imgReader.read(file.getPath());
			
			// String username= Client.getInstance().getAccount().getUsername();
			 
			Client.getInstance().changeImg(img);
			imgProfilo.setImage(imgReader.byteToImg(img));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    }
}
