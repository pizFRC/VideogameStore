package application.controller;





import java.io.File;
import java.text.DecimalFormat;


import application.SceneHandler;
import application.client.Client;
import application.model.Game;
import application.util.DownloadGame;
import application.util.imageReader;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

public class LibreriaAcquistiController {
	
	private static ObservableList<Game> obsGames;


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

    @FXML
 void initialize() {
    	DownloadGame lastDownload=Client.getInstance().getLastDownload();
    	if(lastDownload!=null) {
    		download=lastDownload;
    		scarica(download.getG());
    	}
    	download=new DownloadGame();
    	download.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				
				
			
			 
			    
			Client.getInstance().addDownload(download.getG());
				
			
				
           Alert alert=new Alert(AlertType.INFORMATION);	
            alert.setTitle("funziona ");
            alert.setContentText("Download Completato con successo");
		       alert.showAndWait();
			}
		
		});
    	
    
    	download.setOnFailed(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				
				HBox b1=(HBox)progressVbox.getChildren().get(0);
				
				   Label lb1=(Label)b1.getChildren().get(2);
				    
				   lb1.setVisible(false);
				   lb1=(Label)b1.getChildren().get(1);
				   lb1.setText(download.getG().getNome()+" Download failed");
				lb1.autosize();
				
				
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
                            	 
                            	 if(download.isRunning()) {
                          			SceneHandler.getInstance().showMessage("Un download è già in corso","Errore", AlertType.ERROR);
                                         return;
                            	 }

                             	File file=choosenFile();
                             	if(file==null){
                     				SceneHandler.getInstance().showMessage("Errore durante il salvatagio del file, \n download fallito","Errore", AlertType.ERROR);
                     		 return ;
                     		 }else if(!file.isDirectory()) {
                     			 
                     			SceneHandler.getInstance().showMessage("Selezionare una cartella di destinazione","Errore", AlertType.ERROR);
                        		 return ; 
                     		 }
                     			
                         		 
                            	 //scelgo il percorso
                            	 Game g1=getTableRow().getItem();
                        		 if(g1==null) {
                            		 return;}
                        		 
                        		 download.setFilePath(file.getPath());
                        		 download.setUrlText(g1.getUrlDownload());
                        	    download.setGame(g1);
                            	// Client.getInstance().addDownload(g);
                            	download.restart();
                            	scarica(g1);
                            	Client.getInstance().setLastDownload(download);
                            	
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
       
         downloadCol.setStyle("-fx-alignment:CENTER");
        table.setItems(obsGames);
       
    
    }
    private File choosenFile() {
    	DirectoryChooser dirChooser = new DirectoryChooser(); 
    	dirChooser.setTitle("Selezionare la cartella in cui salvare il download");
		 File file = 	dirChooser.showDialog(null);
       
		if(! (file==null)) {
			
               return file;
		}else {
			
			return null;
		}
		

    }
      private void scarica(Game g) {
    	
    	progressVbox.getChildren().clear();
    	
    	
    	
    	
    	Label nome=new Label(  g.getNome());
    	Label misura=new Label("Kb");
    	
    	nome.setStyle("-fx-font-size:36px; -fx-text-fill:white");
    	Label info=new Label("");
    	info.setStyle("-fx-font-size:36px; -fx-text-fill:white");
   
    	info.textProperty().bind(download.messageProperty());
    	Label byteScaricati=new Label("0");
    	byteScaricati.setStyle("-fx-font-size:36px; -fx-text-fill:white");
    	misura.setStyle("-fx-font-size:36px; -fx-text-fill:white");
    	byteScaricati.setText("0");
   	 DecimalFormat decimalFormat = new DecimalFormat("#.00");
    	float res=Float.parseFloat(download.getSize());
  	byteScaricati.textProperty().bind(download.progressProperty().multiply(res/1000).asString("%.2f"));
  	
 
		 String result=decimalFormat.format(res/1000);
		 Label byteTotali=new Label('/'+result);
	    	
    	byteTotali.setStyle("-fx-font-size:36px; -fx-text-fill:white");
    	imageReader reader=new imageReader();
    	try {
			Image imgGame=reader.byteToImg(g.getImg());
ImageView imgView=new ImageView(imgGame);
	    	HBox b=new HBox(imgView,nome,info,byteScaricati,byteTotali,misura);
	    	HBox.setHgrow(nome, Priority.SOMETIMES);
	    	HBox.setHgrow(info, Priority.SOMETIMES);
	    	HBox.setHgrow(byteScaricati, Priority.ALWAYS);
	    	HBox.setHgrow(byteTotali, Priority.ALWAYS);
	    	b.setSpacing(50);
	    	b.setStyle("-fx-border-color:white");
	    	b.setAlignment(Pos.CENTER);
	    
	        VBox.setMargin(b, new Insets(50,50,50,50));
	    	
	        VBox.setVgrow(b,Priority.ALWAYS);
	    	
	    
			progressVbox.getChildren().add(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
   
    	
		
		
		
	}

	
    
}
