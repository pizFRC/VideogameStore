package application.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import application.client.Client;
import application.model.Game;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class HomePageController{
	
    @FXML
    private HBox rowTopGame;

    @FXML
    private TilePane piùVendutiPane;

    @FXML
    private ImageView bannerImg;
    
 
    @FXML
    private VBox vboxPrincipale;

public void setImage() {
	//Client.getInstance().getGameHomeVenduti();
	ArrayList<Game>tmp=Client.getInstance().getGameCerca();
	if(tmp.isEmpty() ) {
		Label lb=new Label("nessun gioco presente");
		 rowTopGame.getChildren().add(lb);
		 return;
		 
	
	}
	if(tmp.size()>8) {
	rowTopGame.getChildren().clear();
	tmp.sort(new Comparator<Game>() {

		@Override
		public int compare(Game o1, Game o2) {
			
			return o1.getData().compareTo(o2.getData())*-1;
			
		}
		
	});
	
	
	for(int i=0;i<8;i++){
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/GameItemClient.fxml"));

			Parent root = (Parent)loader.load();
			GameItemController controller=loader.getController();
			controller.setData(tmp.get(i));
			
		
        rowTopGame.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	tmp.sort(new Comparator<Game>() {

		@Override
		public int compare(Game o1, Game o2) {
			
			return o1.getNome().compareToIgnoreCase(o2.getNome());
			
		}
		
	});
	for(int i=0;i<6;i++){

		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/GameItemClient.fxml"));

			Parent root = (Parent)loader.load();
			GameItemController controller=loader.getController();
			controller.setData(tmp.get(i));
			
			piùVendutiPane.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}
	else{
		rowTopGame.getChildren().clear();
		tmp.sort(new Comparator<Game>() {

			@Override
			public int compare(Game o1, Game o2) {
				
				return o1.getData().compareTo(o2.getData())*-1;
				
			}
			
		});
		
		
		for(Game g:tmp){
			try {
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/GameItemClient.fxml"));

				Parent root = (Parent)loader.load();
				GameItemController controller=loader.getController();
				controller.setData(g);
				
			
	        rowTopGame.getChildren().add(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		tmp.sort(new Comparator<Game>() {

			@Override
			public int compare(Game o1, Game o2) {
				
				return o1.getNome().compareToIgnoreCase(o2.getNome());
				
			}
			
		});
		for(Game g:tmp){

			try {
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/GameItemClient.fxml"));

				Parent root = (Parent)loader.load();
				GameItemController controller=loader.getController();
				controller.setData(g);
				
				piùVendutiPane.getChildren().add(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
    }
	


	public  void initialize() {
		System.out.println("inizitalize");
		piùVendutiPane.setPrefRows(1);
		
		
		Image img=new Image(getClass().getResourceAsStream("/img/logo.png"),200,50,true,true);
    	bannerImg.setImage(img);
    	piùVendutiPane.prefWidthProperty().bind(vboxPrincipale.widthProperty());
    	System.out.println(piùVendutiPane.getParent().getParent());
   setImage();
	}

	
	

    
    

			 
			 
		        
				
			
		
	

}
