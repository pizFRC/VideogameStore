package application.controller;


import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

import application.SceneHandler;
import application.client.Client;
import application.client.Protocol;
import application.util.DataValidator;
import application.util.imageReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class RegistrationController {
    
	@FXML
    private TextField usernameField;

    @FXML
    private HBox topPane;

    @FXML
    private TextField numberField;
    
    @FXML
    private DatePicker datePicker;


    @FXML
    private CheckBox privacyCheckBox;

    @FXML
    private PasswordField confirmPWField;

    @FXML
    private Button backButton;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button confirmButton;

    @FXML
    private Label labelRegistration;

    @FXML
    private TextField areaCodeField;
    
 
    @FXML
    private Button imgButton;
 private byte[]  imgDefault;
 
 private String styleField=(" -fx-background-color: #a9a9a9 , white , white;"
   		+ "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
    
 
 @FXML
 void chooseImg(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    
    	
   
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("IMAGE files  (*.png,*.jpeg,*.jpg)", "*.png","*.jpeg","*.jpg"));
        
        
        
       
		fileChooser.setTitle("Seleleziona un'immagine del profilo");
	
		 File file = 	fileChooser.showOpenDialog(null);
		 imageReader imgReader=new imageReader();
		
		 if(file==null || (!file.canRead()))
			 return;
		  try {
			imgDefault=imgReader.read(file.getPath());
ImageView img= new ImageView(imgReader.byteToImg(imgDefault));
img.setFitWidth(150);
img.setFitHeight(150);
        	
        			imgButton.setGraphic(img);
        			
		} catch (Exception e) {
			SceneHandler.getInstance().showMessage("errore durante la selezione dell'immagine verrà  \nimpostata quella di default","Errore", AlertType.ERROR);
		}
	    }
   

 
 @FXML
	void initialize(){
    	Tooltip a=new Tooltip("I dati non saranno condivisi con aziende di terze parti");
       privacyCheckBox.setTooltip(a);
            	imageReader imgReader=new imageReader();
            	
            	areaCodeField.setText("39");
            	areaCodeField.setEditable(false);
            
            	try {
        			imgDefault = imgReader.read(getClass().getResource("/img/user_profile.png").getPath());
        			ImageView img= new ImageView(imgReader.byteToImg(imgDefault));
        	
        	img.setScaleY(2);
        		img.setScaleX(2);
        			imgButton.setGraphic(img);
        		} catch (Exception e) {
        			SceneHandler.getInstance().showMessage("Errore durante il carimento della finestra di registrazione","Errore",AlertType.ERROR);
        		}
            	
            
    	
    	
    	 
    	confirmButton.setDisable(true);
    	
    	usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
	          
	           boolean pw=DataValidator.parseUsername(newValue);
	           if(newValue.isEmpty()) {
	        	   usernameField.setStyle(styleField);
	        	
    	}else if(pw) {
	        	 
	        	   usernameField.setStyle(styleField);
	        	   
	           }else//NON corretta
	           {   usernameField.setStyle("-fx-background-color: red;");
	        	   
	           }
	           unloackButton();
	        });

    	
    	
    	emailField.textProperty().addListener((observable, oldValue, newValue) -> {
		          
	           boolean pw=DataValidator.parseEmail(newValue);
	           if(newValue.isEmpty()) {
	        	   emailField.setStyle(styleField);
 	}else if(pw) {
	        	   emailField.setStyle(styleField);
	        	   
	           }else//NON corretta
	           {   emailField.setStyle("-fx-background-color: red;");
	        	   
	           }
	           unloackButton();
	        });
    	
    	
    	numberField.textProperty().addListener((observable, oldValue, newValue) -> {
		          
	           boolean pw=DataValidator.parsePhone(newValue);
	           if(newValue.isEmpty()) {
	        	   numberField.setStyle(styleField);
 	}else if(pw) {
	        	   numberField.setStyle(styleField);
	        	   
	           }else//NON corretta
	           {   numberField.setStyle("-fx-background-color: red;");
	        	   
	           }
	           unloackButton();
	        });
    	
    	
    	passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
		          
	           boolean pw=DataValidator.parsePassword(newValue);
	           if(newValue.isEmpty()) {
	        	   passwordField.setStyle(styleField);
	}else if(pw) {
	        	   passwordField.setStyle(styleField);
	        	   
	           }else//NON corretta
	           {   passwordField.setStyle("-fx-background-color: red;");
	        	   
	           }
	           unloackButton();
	        });
    	
    	
    	
    	
    	confirmPWField.textProperty().addListener((observable, oldValue, newValue) -> {
		          
	           boolean pw=DataValidator.parsePassword(newValue);
	           if(newValue.isEmpty()) {
	        	   confirmPWField.setStyle(styleField);
	}else if(pw) {
		    if(newValue.equals(passwordField.getText())) {
			    confirmPWField.setStyle(styleField);
	          }   else {
	        	   confirmPWField.setStyle("-fx-background-color: red;");
	        	   }
	        	   
	           }else//NON corretta
	           {   confirmPWField.setStyle("-fx-background-color: red;");
	        	   
	           }
	           
	           unloackButton();
	        });
    
    	
    }
    @FXML
    void confirmRegistration(ActionEvent event) {
    	if(!privacyCheckBox.isSelected()) {
    		SceneHandler.getInstance().showMessage("Conferma le condizioni della privacy per proseguire","Errore", AlertType.WARNING);
    		return;
    	}
        registration( );
clear();
    }
    
    private void clear() {
    	usernameField.setText("");
    	numberField.setText("");
    	privacyCheckBox.setSelected(false);
    	datePicker.getEditor().clear();
    	emailField.setText("");
    	areaCodeField.setText("");
    	confirmPWField.setText("");
        passwordField.setText("");
    }
    
    
 private void registration() {
	 String[] data=getData();
	 String username=data[0];
	 String email=data[1];
	 String phone=data[2];
	 String pw=data[3];
	 Date date = Date.valueOf(LocalDate.now());
	
 	String res=Client.getInstance().registration(username, email, phone,pw,date,imgDefault);
 	
 	if(res.equals(Protocol.OK)) {
 		
 		try {
 			
			SceneHandler.getInstance().setHome();
		} catch (Exception e) {
			SceneHandler.getInstance().showMessage("Alcuni file potrebbero essere danneggiati o non essere presenti","Errore",  AlertType.ERROR);
			
		}
 		
 	}else {
 		
 		SceneHandler.getInstance().showMessage("La registrazione non è andata a buon fine","Errore", AlertType.ERROR);
 		clear();
 		Client.getInstance().reset();
 		
 	}
 }
    @FXML
    void back(ActionEvent event) {
      try {
		SceneHandler.getInstance().setLogin();
	} catch (Exception e) {
		SceneHandler.getInstance().showMessage("Non è possibile tornare alla finestra di login","Errore",  AlertType.ERROR);
	}
    }
    

   private void unloackButton() {
	   if(controllaCriteri())
		   confirmButton.setDisable(false);
	   else
		   confirmButton.setDisable(true);
   }

  
    private boolean controllaCriteri() {
   
     boolean pw=DataValidator.parsePassword( passwordField.getText());
     boolean username=DataValidator.parseUsername(usernameField.getText());
     boolean email=DataValidator.parseEmail(emailField.getText());
     boolean number=DataValidator.parsePhone(numberField.getText());
     boolean pwC=DataValidator.parsePassword( confirmPWField.getText());
    return  pw && username && email && number && pwC;
    
  
    }
    
    public String[] getData() {
    	
    	String [] container = new String[4];
    	
    	container[0]=usernameField.getText();
    	container[1]=emailField.getText();
    	container[2]=areaCodeField.getText()+numberField.getText();
    	container[3]= passwordField.getText();
    	
    	
    	
    	
    	
    	
    	
		return container;
    	
    }


}
