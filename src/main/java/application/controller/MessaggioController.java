package application.controller;

import application.util.imageReader;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
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
     public void changeBG() {
    	

    	 testo.getParent().setStyle("-fx-background-radius:100;"+"-fx-background-color:#bf2b24;");
    	 testo.getParent().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
     }
}
