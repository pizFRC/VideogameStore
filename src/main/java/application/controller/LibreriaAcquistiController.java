package application.controller;





import com.sun.javafx.scene.traversal.SceneTraversalEngine;

import application.SceneHandler;
import application.client.Client;
import application.model.DownloadGame;
import application.model.Game;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class LibreriaAcquistiController {
	
	private static ObservableList<Game> obsGames;

	private Game tmpDownload;
	@FXML
    private TableColumn<Game, Image> imgCol;
    @FXML
    private TableColumn<Game, String> nomeCol;

    @FXML
    private TableColumn<Game, String> dataCol;

    @FXML
    private TableColumn<Game, String> prodCol;

    @FXML
    private TableColumn<Game, String> downloadCol;

    @FXML
    private TableView<Game> table;
    
    
    @FXML
    private VBox progressVbox; 
    
    private DownloadGame download;

    	 
    
    public void initialize() {
    	
    	download=new DownloadGame();
    	download.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				
				ProgressBar b=(ProgressBar)progressVbox.getScene().lookup('#'+tmpDownload.getNome());
			
			
				System.out.println(progressVbox.getChildren().get(0));
				HBox b1=(HBox)progressVbox.getChildren().get(0);
				System.out.println(b1.getChildren().get(1));
			
				b.setStyle("-fx-background-color:green");
			
				
				
				
				b.setProgress(1.0);
				
           Alert alert=new Alert(AlertType.INFORMATION);	
            alert.setTitle("funziona ");
            alert.setContentText("Download Completato con successo");
		       alert.showAndWait();
		       tmpDownload=null;
			}
		
		});
    	
    	download.setOnFailed(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				ProgressBar b=(ProgressBar)progressVbox.getScene().lookup('#'+tmpDownload.getNome());
				System.out.println(b.getId());
							
				b.setAccessibleText("FAILED");
				b.setStyle("-fx-background-color:red");
			System.out.println("failed "+tmpDownload.getUrlDownload());
				tmpDownload=null;
	           
				Alert alert=new Alert(AlertType.ERROR);	
                  alert.setTitle("Connection Error");
                  alert.setContentText("Cannot load URL:check your connection or the and the inserted url");
			       alert.showAndWait();
			}
		});
    	
    	obsGames=FXCollections.observableArrayList(Client.getInstance().getGameAcquistati());
    
    	nomeCol.setCellValueFactory(new PropertyValueFactory<Game, String>("nome"));
    	dataCol.setCellValueFactory(new PropertyValueFactory<Game, String>("data"));
    	prodCol.setCellValueFactory(new PropertyValueFactory<Game, String>("Produttore"));
    
    	
    	 downloadCol.setCellValueFactory(new PropertyValueFactory<>(""));

         Callback<TableColumn<Game, String>, TableCell<Game, String>> cellFactory
                 =   new Callback<TableColumn<Game, String>, TableCell<Game, String>>() {
             @Override
             public TableCell call(final TableColumn<Game, String> param) {
                 final TableCell<Game, String> cell = new TableCell<Game, String>() {

                     final Button btn = new Button("");
                     { FontAwesomeIcon icona=new FontAwesomeIcon();
                     icona.setIconName("DOWNLOAD");
                     icona.setSize("3em");
                     btn.setGraphic(icona);
                     }
                     @Override
                     public void updateItem(String item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty) {
                             setGraphic(null);
                             setText(null);
                         } else {
                             btn.setOnAction(event -> {
           
                          Game g1=getTableRow().getItem();
                          
                     
                            	if(tmpDownload!=null) {
                            		SceneHandler.getInstance().showError("Un gioco è gia presente nella coda download",AlertType.ERROR);
                            		return;
                             }
                            	 if(g1==null) {
                            		
                            		 System.out.println("game null");
                            		 return;}
                            	
                            	 System.out.println("url: "+g1.getUrlDownload());
                            	 System.out.println("produtorre"+ g1.getProduttore());
                            	 System.out.println("nome"+ g1.getNome());
                            	 tmpDownload=g1;
                            	
                            	 download.setUrlText(g1.getUrlDownload());
                            	 download.setNomeGame(g1.getNome());
                            	 download.setProduttore(g1.getProduttore());
                            	// Client.getInstance().addDownload(g);
                            	download.restart();
                            	scarica();
                                 System.out.println("prova");
                             });
                             setGraphic(btn);
                             setText(null);
                         }
                     }
					
                 };
                 return cell;
             }

			
         };
       
         downloadCol.setCellFactory(cellFactory);
    	
    
        table.setItems(obsGames);
       
    
    }
    private void scarica() {
    	
     
    	ProgressBar bar=new ProgressBar(0);
    	
    	
    	
    	Label nome=new Label(  tmpDownload.getNome());
    	bar.setId(tmpDownload.getNome());
    	System.out.println("bar:"+bar.getId());
    	HBox.setHgrow(bar, Priority.ALWAYS);
    	
    	HBox b=new HBox(nome,bar);
    	
    	b.setAlignment(Pos.CENTER);
    	nome.prefWidthProperty().bind(b.widthProperty().subtract(nome.getWidth()));
     VBox.setMargin(b, new Insets(20,20,20,20));
    	bar.prefWidthProperty().bind(b.widthProperty().subtract(nome.getWidth()));
    	
    	
    	
    	b.setStyle("-fx-background-color:red");
		progressVbox.getChildren().add(b);
		
	}
    
}
