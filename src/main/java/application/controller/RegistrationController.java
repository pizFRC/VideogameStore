package application.controller;


import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.SceneHandler;
import application.client.Client;
import application.client.Protocol;
import application.model.DataValidator;
import application.model.imageReader;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
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
    @FXML
	void chooseImg(ActionEvent event) {
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
			imgDefault=imgReader.read(file.getPath());
ImageView img= new ImageView(imgReader.byteToImg(imgDefault));
        	
        	img.setScaleY(2);
        		img.setScaleX(2);
        			imgButton.setGraphic(img);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
    public void initialize() {
       
            	imageReader imgReader=new imageReader();
            	
          
            
            	try {
        			imgDefault = imgReader.read(getClass().getResource("/img/man2.png").getPath());
        			ImageView img= new ImageView(imgReader.byteToImg(imgDefault));
        	
        	img.setScaleY(2);
        		img.setScaleX(2);
        			imgButton.setGraphic(img);
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
            	
            
    	System.out.println("initr");
    	
    	
    	 
    	confirmButton.setDisable(true);
    	
    	usernameField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				  boolean username=DataValidator.parseUsername(usernameField.getText());
				if(!username && !usernameField.getText().isEmpty()) {
					usernameField.setStyle("-fx-background-color:  #c00000;");
					if(!confirmButton.isDisable())
						confirmButton.setDisable(true);
				}else
					usernameField.setStyle("-fx-background-color: white;");
				
			}
    		
    	});
    	
    	emailField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				  boolean email=DataValidator.parseEmail(emailField.getText());
				if(!email && !emailField.getText().isEmpty()) {
					emailField.setStyle("-fx-background-color:  #c00000;");
					if(!confirmButton.isDisable())
						confirmButton.setDisable(true);
				}else
					emailField.setStyle("-fx-background-color: white;");
				
			}
    		
    	});
    	numberField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(!controllaCriteri() && !passwordField.getText().isEmpty()) {
					passwordField.setStyle("-fx-background-color:  #c00000;");
					if(!confirmButton.isDisable())
						confirmButton.setDisable(true);
				}else
					passwordField.setStyle("-fx-background-color: white;");
				
			}
    		
    	});
    	passwordField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				boolean pw=DataValidator.parsePassword( passwordField.getText());
				if(!pw && !passwordField.getText().isEmpty()) {
					passwordField.setStyle("-fx-background-color:  #c00000;");
					if(!confirmButton.isDisable())
						confirmButton.setDisable(true);
				}else
					passwordField.setStyle("-fx-background-color: white;");
				  
			}
    		
    	});
    	confirmPWField.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				//boolean pw=DataValidator.parsePassword( confirmPWField.getText());
				if(! (passwordField.getText().length()==confirmPWField.getText().length())){
					confirmPWField.setStyle("-fx-background-color: #c00000;");
					if(!confirmButton.isDisable())
						confirmButton.setDisable(true);
			
				}else {
					
				
							if(confirmPWField.getText().equals(passwordField.getText())) {
							confirmPWField.setStyle("-fx-background-color: white;");
							confirmButton.setDisable(false);
							}
						else {
						confirmPWField.setStyle("-fx-background-color: #c00000;");
						confirmButton.setDisable(true);
									}
				
				}
			
			}
    	});
    	
    	
    
    	
    }
    @FXML
    void confirmRegistration(ActionEvent event) {
    	
        registration( );
clear();
    }
    
    private void clear() {
    	usernameField.setText("");
    	numberField.setText("");
    	privacyCheckBox.setSelected(false);
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
	 byte[]img;
 	String res=Client.getInstance().registration(username, email, phone,pw,date,imgDefault);
 	
 	if(res.equals(Protocol.OK)) {
 		
 		try {
 			
			SceneHandler.getInstance().setHome();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 	}else {
 		
 		SceneHandler.getInstance().showError("non registrato", AlertType.ERROR);
 		Client.getInstance().reset();
 		
 	}
 }
    @FXML
    void back(ActionEvent event) {
      try {
		SceneHandler.getInstance().setLogin();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    

   

  
    private boolean controllaCriteri() {
   
     boolean pw=DataValidator.parsePassword( passwordField.getText());
     boolean username=DataValidator.parseUsername(usernameField.getText());
     boolean email=DataValidator.parseUsername(emailField.getText());
     boolean number=DataValidator.parsePhone(areaCodeField.getText()+numberField.getText());
     boolean pwC=DataValidator.parsePassword( confirmPWField.getText());
    return  pw && username && email && number && pwC;
    
  
    }
    
    public String[] getData() {
    	boolean correct=false;
    	String [] container = new String[4];
    	
    	container[0]=usernameField.getText();
    	container[1]=emailField.getText();
    	container[2]=areaCodeField.getText()+numberField.getText();
    	container[3]= passwordField.getText();
    	
    	
    	
    	
    	
    	
    	
		return container;
    	
    }


}
