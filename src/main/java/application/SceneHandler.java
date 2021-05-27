package application;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneHandler {
private static HashMap<String,Pane>finestre = new HashMap<String,Pane>();;
private  Scene main;
private  Stage stageP;
private static SceneHandler instance=null;

private SceneHandler() {
	
}
public static SceneHandler getIstance(){
	if(instance==null)
		instance=new SceneHandler();
	return instance;
}


public  void init(Scene m,Stage stage) {
    main = m;        	
    stageP=stage;
  
    stageP.setResizable(false);
    stageP.setScene(main);
    stageP.setTitle("Game Store Management - Login");
    stageP.initStyle(StageStyle.UNDECORATED);
    stageP.show();
}

public  void add(String name, Pane pane){
     finestre.put(name, pane);
}

public  void setCurrent(String name){
	main.setRoot(finestre.get(name));
}	

public  void setRegistrazione() {
	setCurrent("Registrazione");
	stageP.initModality(Modality.APPLICATION_MODAL);
	stageP.initStyle(StageStyle.UNDECORATED);
}
public  void setLogin() {
	setCurrent("Login");
	stageP.initStyle(StageStyle.UNDECORATED);
}

}
