package application.controller;


import application.SceneHandler;
import application.model.DataValidator;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

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

    public void initialize() {
    	System.out.println("initr");
    	passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(!controllaCriteri() && !passwordField.getText().isEmpty()) {
					passwordField.setStyle("-fx-background-color:  #c00000;");
				}else
					passwordField.setStyle("-fx-background-color: white;");
				
			}
    		
    	});
    	confirmPWField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(!controllaCriteri()|| confirmPWField.getText().equals(passwordField.getText())&& !passwordField.getText().isEmpty()) {
					confirmPWField.setStyle("-fx-background-color: #c00000;");
				}else
					confirmPWField.setStyle("-fx-background-color: white;");
				
			}
    		
    	});
    	
    }
    

    @FXML
    void confirmRegistration(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
      try {
		SceneHandler.getIstance().setLogin();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    @FXML
    void textChanged(ActionEvent event) {
   //  System.out.println("testo");
    }

   

  
    private boolean controllaCriteri() {
     DataValidator dv=new DataValidator();
    return  dv.parsePassword(  passwordField.getText());
  
    }

}
