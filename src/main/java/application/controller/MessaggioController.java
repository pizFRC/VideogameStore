package application.controller;

import application.model.imageReader;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class MessaggioController {

    @FXML
    private ImageView imgProfilo;


    @FXML
    private TextArea testo;
    
     public void setDati(String txt,String datatime,byte[]img) {
    	 testo.setEditable(false);
    	 testo.setText(txt+System.lineSeparator()+datatime);
    	 imageReader reader=new imageReader();
    	 try {
			imgProfilo.setImage(reader.byteToImg(img));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}
