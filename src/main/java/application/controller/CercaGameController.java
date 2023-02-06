package application.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import application.SceneHandler;
import application.client.Client;
import application.model.CategorieGame;
import application.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;


public class CercaGameController {

	private boolean aggiorna=true;

	 private ArrayList<Game>game ;

    @FXML
    private ChoiceBox<String> ordineChoicheBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TilePane listaGame;

    @FXML
    private Button categoriaAvventura;

    @FXML
    private Button categoriaSparatutto;

    @FXML
    private Button resetButton;

    @FXML
    private Button categoriaStrategia;

    @FXML
    private TextField titoloField;

    @FXML
    private Button categoriaSport;
  
    
    @FXML
     void initialize() {
    	
    	ordineChoicheBox.getItems().addAll("Alfabetico","Download","Preferenze","Prezzo","Data");
    	ordineChoicheBox.setValue("Alfabetico");
    	game=Client.getInstance().getGameCerca();
   
    	

		Image img=new Image(getClass().getResourceAsStream("/icons/sword.png"),72,72,true,true);
           categoriaAvventura.setGraphic(new ImageView(img));
           img=new Image(getClass().getResourceAsStream("/icons/ball.png"),72,72,true,true);
       categoriaSport.setGraphic(new ImageView(img));
       img=new Image(getClass().getResourceAsStream("/icons/chess.png"),72,72,true,true);
       categoriaStrategia.setGraphic(new ImageView(img));
       img=new Image(getClass().getResourceAsStream("/icons/gun.png"),72,72,true,true);
       categoriaSparatutto.setGraphic(new ImageView(img));
       img=new Image(getClass().getResourceAsStream("/icons/redo.png"),72,72,true,true);
       resetButton.setGraphic(new ImageView(img));
        listaGame.prefWidthProperty().bind(scrollPane.widthProperty());
        
        
        titoloField.textProperty().addListener((observable, oldValue, newValue) -> {
        
           aggiorna=!aggiorna;
           if(newValue.isEmpty())
        	   aggiorna=false;
           
          
    setGame(game);
        });
        
        
        
        ordineChoicheBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("Alfabetico")){
            	game.sort(new Comparator<Game>() {

            		@Override
            		public int compare(Game o1, Game o2) {
            			return o1.getNome().compareTo(o2.getNome());
            			
            		}
            		
            	});
            }else if(newValue.equals("Download")) {
            	game.sort(new Comparator<Game>() {
                           
            		@Override 
            		public int compare(Game o1, Game o2) {
            			if(o1.getDownload()<o2.getDownload())
            			return 1;
            			else if(o1.getDownload()>o2.getDownload())
            				return -1;
            			else return 0;
            		
            			
            		}
            		
            	});
            }else if(newValue.equals("Preferenze")) {
            	game.sort(new Comparator<Game>() {

            		@Override
            		public int compare(Game o1, Game o2) {
            			if(o1.getPreferenze()<o2.getPreferenze())
            			return 1;
            			else if(o1.getPreferenze()>o2.getPreferenze())
            				return -1;
            			else return 0;
            		
            			
            		}
            		
            	});
            }else if(newValue.equals("Prezzo")) {
            	game.sort(new Comparator<Game>() {

            		@Override
            		public int compare(Game o1, Game o2) {
            			if(o1.getPrezzo()<o2.getPrezzo())
            			return -1;
            			else if(o1.getPrezzo()>o2.getPrezzo())
            				return 1;
            			else return 0;
            		
            			
            		}
            		
            	});
            }else if(newValue.equals("Data")){
            	game.sort(new Comparator<Game>() {

            		@Override
            		public int compare(Game o1, Game o2) {
            			return o1.getData().compareTo(o2.getData());
            		
            			
            		}
            		
            	});
            }
            
  
            
            setGame(game);
          });
        
     	setGame(game);
        
    	
    }
   private  void setGame(ArrayList<Game>game) {
	   
	   listaGame.getChildren().clear();
        listaGame.setPrefRows(game.size()/5);
	   for(Game g:game) {
		 if(!titoloField.getText().isEmpty()){
			
			 if(!g.getNome().toLowerCase().startsWith(titoloField.getText().toLowerCase())) {
				
				    continue;
		       
		 }
		 
	   }
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/GameItemClient.fxml"));
			   Parent root = (Parent)loader.load();
			   GameItemController controller=loader.getController();
			   controller.setData(g);
			 
			   listaGame.getChildren().add(root);
		} catch (IOException e) {
		 try {
			SceneHandler.getInstance().showErrorPage();
		} catch (Exception e1) {
			System.err.println("errore durante il caricamento di error page");
		}
		}
		   
	   }
   }
   
   @FXML
   void sportPressed(ActionEvent event) {
	  
	   ArrayList<Game>gameSport= new  ArrayList<Game>();
	   for(Game g:game) {
		   if(g.getGenere().toLowerCase().contains(CategorieGame.SPORT.toLowerCase()))
		   gameSport.add(g);
	   }
	   setGame(gameSport);
   }
   @FXML
   void sparatuttoPressed(ActionEvent event) {
	  
	   ArrayList<Game>gameSparatutto= new  ArrayList<Game>();
	   for(Game g:game) {
		   if(g.getGenere().toLowerCase().contains(CategorieGame.SPARATUTTO.toLowerCase()))
			   gameSparatutto.add(g);
	   }
	   setGame(gameSparatutto);
   }

   @FXML
   void strategiaPressed(ActionEvent event) {
	   
	   ArrayList<Game>gameSport= new  ArrayList<Game>();
	   for(Game g:game) {
		   if(g.getGenere().toLowerCase().contains(CategorieGame.STRATEGIA.toLowerCase()))
		   gameSport.add(g);
	   }
	   setGame(gameSport);
   }

   @FXML
   void avventuraPressed(ActionEvent event) {
	  
	   ArrayList<Game>gameAvv= new  ArrayList<Game>();
	   for(Game g:game) {
		   if(g.getGenere().toLowerCase().contains(CategorieGame.AVVENTURA.toLowerCase()))
			   gameAvv.add(g);
	   }
	   setGame(gameAvv);
   }

   @FXML
   void reset(ActionEvent event) {
	   titoloField.clear();
          setGame(game);
   }

}