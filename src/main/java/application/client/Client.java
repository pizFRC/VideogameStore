package application.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import application.SceneHandler;
import application.client.Client;
import application.model.Account;
import application.model.AccountLogin;
import application.model.Game;
import application.model.Messaggio;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
;


public class Client implements Runnable{

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket socket;
	private static Client instance=null;
	private AccountLogin account;
	private String[] info;
	private boolean status;
	ArrayList<Game>carrelloGame= new ArrayList<Game>();
	List<Game> visitedGame = Collections.synchronizedList(new ArrayList<Game>());
	List<Game> visitedGameCerca = Collections.synchronizedList(new ArrayList<Game>());
	List<Game> preferenze = Collections.synchronizedList(new ArrayList<Game>());
	List<Game> listaAcquistati = Collections.synchronizedList(new ArrayList<Game>());
	
	private Client() {
		try {
			
			socket=new Socket("localhost",8000);
			out=new ObjectOutputStream(socket.getOutputStream());
			status=true;
		}  catch (IOException e) {
		System.out.println("deded");
			SceneHandler.getInstance().showError("Non sono riuscito a connettermi",AlertType.WARNING);
	
		   reset();
			
		}
	}
	
	public float getPrice() {
		float prezzo=0;
		for(Game g:carrelloGame)
		{
			prezzo+=g.getPrezzo();
		}
		return prezzo;
	}
	public int getSizeProdottiCarrello() {
		return carrelloGame.size();
	}
	
	public static Client getInstance() {
		if(instance==null)
			instance=new Client();
		return instance;
	}
	
	
	@Override
	public void run() {
		
		System.out.println("run client");
		
		while(isActive() ) {
			String message;
			
			try {
				System.out.println("Client in attesa");
				if(status==false) {
					System.out.println("errore");
					return;
				}
				message = (String)in.readObject();
				
				if(message.equals(Protocol.POST_LOGIN)) {
					 account=(AccountLogin) in.readObject();
					account.setImg((byte[])in.readObject());
					
					info=(String[])in.readObject();
				
					ArrayList<Game> all=(ArrayList<Game>)in.readObject();
			    
			    	
			    	 visitedGameCerca.addAll(all);
			    	 ArrayList<Game> acqui=(ArrayList<Game>)in.readObject();
			    
			    	
			    	 listaAcquistati.addAll(acqui);
			    	 
					System.out.println("L'account loggato è "+account.getUsername());
					
					
					
			     }else if(message.equals(Protocol.MESSAGE)) {
				Messaggio m=(Messaggio)in.readObject();
				Messages.addMessage(m);
			     }
			} catch (Exception e) {
				//out=null;
			//System.out.println(	e.getMessage());
				
				status=false;
		//	SceneHandler.getInstance().showError("Disconnesso", AlertType.ERROR);
				
				//e.printStackTrace() ;
				System.out.println("clientexception");
				
				//if(Platform.isFxApplicationThread())
				reset();
				
			//Thread.currentThread().interrupt();
			    
				//
    // Platform.exit();
			
				
			}
			
		}
		
	}
	public void changeImg(byte[]img) {
		sendMessagePrivate(Protocol.CHANGE_IMG);
	
		sendMessagePrivate(img);
		
		account.setImg(img);
	
	}
	public void infoAccount() {
		sendMessage(Protocol.INFO_ACCOUNT);
		
	}
	public String[]getInfo(){
		return info;
	}
	public void recPW() {
		sendMessagePrivate(Protocol.RECOVERY_PASSWORD);
	}
	
	

	public String login(String username,String password) {
		//STRINGA LOGIN PER DIRE COSA VOGLIO FARE
		
			sendMessagePrivate(Protocol.LOGIN);
		sendMessagePrivate(new AccountLogin(username,password));
		
		
		try {
			
		in=new ObjectInputStream(socket.getInputStream());
		String rs=(String)in.readObject();
		 return rs;
		}catch(Exception e) {
			reset();
			return Protocol.ERROR;
		}
	}
	public String changeProfilePicture(String username,byte[]array) {
		sendMessagePrivate(Protocol.CHANGE_IMG);
		
		sendMessagePrivate(array);

		try {
		in=new ObjectInputStream(socket.getInputStream());
		String rs=(String)in.readObject();
		 return rs;
		}catch(Exception e) {
			reset();
			return Protocol.ERROR;
		}
		
	}
	
	
	public String registration(String username,String email,String phone,String password,Date dt,byte[]img) {
		//STRINGA LOGIN PER DIRE COSA VOGLIO FARE
		
			sendMessagePrivate(Protocol.REGISTRATION);
			
		sendMessagePrivate(new Account(username,email,phone,password,dt));
		sendMessagePrivate(img);
		
		try {
		in=new ObjectInputStream(socket.getInputStream());
		String rs=(String)in.readObject();
		 return rs;
		}catch(Exception e) {
			reset();
			return Protocol.ERROR;
		}
	}
	

	private boolean sendMessagePrivate(Object message) {
		if(out==null) {
			System.out.println("out = null");
			return false;
		}
			
		try {
		
			out.writeObject(message);
			
			out.flush();
		} catch (IOException e) {
			
			reset();
			return false;
		}
		return true;
		
	}
	
	public boolean sendMessage(String message) {
		return sendMessagePrivate((Object)message);
	}
	
	public boolean sendMessageChat(Messaggio message) {
		sendMessage(Protocol.MESSAGE);
		return sendMessagePrivate(message);
	}
	
		
	
	public void reset() {
		instance=null;
		out=null;
		status=false;
		in=null;
		socket=null;
	}
	
	
	public boolean isActive() {
		return out!=null && in!= null && status!=false;
		
	}



	public boolean getStatus() {
		
		return status;
	}



	public synchronized AccountLogin getAccount() {
		
		return account;
	}



	
    public synchronized ArrayList<Game> getGameAcquistati(){
    	
    	 ArrayList<Game>tmp=new ArrayList<Game>(listaAcquistati);
    	
    	return  tmp;
    }
    public synchronized ArrayList<Game> getGameCerca(){
    	
   	 ArrayList<Game>tmp=new ArrayList<Game>(visitedGameCerca);
   	
   	return  tmp;
   }

	public boolean addGameCarrello(Game tmp) {
	System.out.println(listaAcquistati);
		if( getGameAcquistati().contains(tmp)) {
			
			System.out.println("funzionamento forse corretto");
			return false;
		}else
			System.out.println("prova else "+tmp.getNome()+getGameAcquistati());
		if(carrelloGame.contains(tmp) )
			return false;
			
			carrelloGame.add(tmp);
		
		return true;
		
		
	}

	public ArrayList<Game> getGameCarrello() {
		ArrayList <Game>tmp=new ArrayList<Game>(carrelloGame);
		return tmp;
	}

	public void removeGamesCarrello(Game tmp) {
		carrelloGame.remove(tmp);		
	}

	

	public void svuotaCarrello() {
		System.out.println("mandato");
		sendMessage(Protocol.ACQUISTO);
		
		System.out.println(carrelloGame);
		sendMessagePrivate(carrelloGame);


     listaAcquistati.addAll(carrelloGame);
     carrelloGame.clear();	

	}

	public void addPreferenza(Game tmp) {
		sendMessage(Protocol.LIKE);
		sendMessagePrivate(tmp);
		
	}

	public void addDownload(Game g) {
		sendMessage(Protocol.DOWNLOAD);
		sendMessagePrivate(g);
		
	}
	
	
}
