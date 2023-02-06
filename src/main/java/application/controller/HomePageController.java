package application.controller;


import java.util.ArrayList;
import java.util.Comparator;

import application.client.Client;
import application.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomePageController{
	
    @FXML
    private HBox rowTopGame;

    @FXML
    private HBox rowTopVenduti;

    @FXML
    private ImageView bannerImg;
    
 
    @FXML
    private VBox vboxPrincipale;

    
    private void loadFxml(int numGame,ArrayList<Game>tmp,boolean rowTop) throws Exception {
    	
    	
    	for(int i=0;i<numGame;i++){
    	
    			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/GameItemClient.fxml"));

    			Parent root = (Parent)loader.load();
    			GameItemController controller=loader.getController();
    			controller.setData(tmp.get(i));
    			
    		if(rowTop)
            rowTopGame.getChildren().add(root);
    		else
    			rowTopVenduti.getChildren().add(root);
    	}
    }
public void setImage() {
	ArrayList<Game>tmp=Client.getInstance().getGameCerca();
	if(tmp.isEmpty() ) {
		Label lb=new Label("nessun gioco presente");
		 rowTopGame.getChildren().add(lb);
		 return;
		 
	
	}
	//se sono più di 8 giochi
	if(tmp.size()>8) {
	rowTopGame.getChildren().clear();
	tmp.sort(new Comparator<Game>() {

		@Override
		public int compare(Game o1, Game o2) {
			
			return o1.getData().compareTo(o2.getData())*-1;
			
		}
		
	});
      try {
		loadFxml(8,tmp,true);
	} catch (Exception e1) {
		rowTopGame.getChildren().clear();
	}


	tmp.sort(new Comparator<Game>() {

		@Override
		public int compare(Game o1, Game o2) {
			
			return String.valueOf(o1.getDownload()).compareToIgnoreCase(String.valueOf(o2.getDownload()));
			
		}
		
	});
	rowTopVenduti.getChildren().clear();
	 try {
		loadFxml(8, tmp,false);
	} catch (Exception e) {
		rowTopVenduti.getChildren().clear();
	}
	
	}
	
	//fine if
	else{
		
		//sono meno di 8
		rowTopGame.getChildren().clear();
		tmp.sort(new Comparator<Game>() {

			@Override
			public int compare(Game o1, Game o2) {
				
				return o1.getData().compareTo(o2.getData())*-1;
				
			}
			
		});
		
		
		try {
			loadFxml(tmp.size(),tmp,true);
		} catch (Exception e1) {
			rowTopGame.getChildren().clear();

		}

		tmp.sort(new Comparator<Game>() {

			@Override
			public int compare(Game o1, Game o2) {
				
				return String.valueOf(o1.getDownload()).compareToIgnoreCase(String.valueOf(o2.getDownload()))*-1;
				
			}
			
		});
		
		
		rowTopVenduti.getChildren().clear();
		try {
			loadFxml(tmp.size(),tmp,false);
		} catch (Exception e1) {
			rowTopVenduti.getChildren().clear();
		}
		
		
	}
	
	
	
	
    }
	
@FXML
 void initialize() {
       Image img=new Image(getClass().getResourceAsStream("/img/log.gif"));
    	bannerImg.setImage(img);	
        setImage();
	}

	
	

    
    

			 
			 
		        
				
			
		
	

}
