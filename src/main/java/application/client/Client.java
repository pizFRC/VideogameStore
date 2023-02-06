package application.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.SceneHandler;
import application.client.Client;
import application.model.Account;
import application.model.AccountLogin;
import application.model.Game;
import application.model.Messaggio;
import application.util.DownloadGame;
import javafx.scene.control.Alert.AlertType;


public class Client implements Runnable{

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket socket;
	private static Client instance=null;
	private AccountLogin account;
	private String[] info;
	private boolean status;
	private DownloadGame lastDownload;
	ArrayList<Game>carrelloGame= new ArrayList<Game>();
	List<Game> visitedGameCerca = Collections.synchronizedList(new ArrayList<Game>());
	List<Game> preferenze = Collections.synchronizedList(new ArrayList<Game>());
	List<Game> listaAcquistati = Collections.synchronizedList(new ArrayList<Game>());
	
	private Client() {
		try {
			
			socket=new Socket("localhost",8000);
			out=new ObjectOutputStream(socket.getOutputStream());
			status=true;
		}  catch (IOException e) {
		
			SceneHandler.getInstance().showMessage("Non sono riuscito a connettermi","Errore",AlertType.WARNING);
			status=false;
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
		
		
		
		while(isActive() ) {
			String message;
			
			try {
				
				if(status==false) {
				
					return;
				}
				message = (String)in.readObject();
				
				
				if(message.equals(Protocol.POST_LOGIN)) {
					Object tmp=in.readObject();
					if(!(tmp instanceof AccountLogin)) {
						
						sendMessage(Protocol.PROTOCOL_NOT_RESPECTED);
						throw new Exception ();
					}
					 account=(AccountLogin)tmp;
					 tmp=in.readObject();
						if(!(tmp instanceof byte[])) {
							sendMessage(Protocol.PROTOCOL_NOT_RESPECTED);
							throw new Exception ();
						}
						
					account.setImg((byte[])tmp);
					
					
					
					 tmp=in.readObject();
						if(!(tmp instanceof String[])) {
							sendMessage(Protocol.PROTOCOL_NOT_RESPECTED);
							throw new Exception ();
						}
					info=(String[])tmp;
				
					
					 tmp=in.readObject();
						if(!(tmp instanceof ArrayList<?>)) {
							
							sendMessage(Protocol.PROTOCOL_NOT_RESPECTED);
								throw new Exception ();
						}
					ArrayList<Game> all=(ArrayList<Game>)tmp;
			    
			    	
			    	 visitedGameCerca.addAll(all);
			    	 
			    	 
			    	 
			    	 tmp=in.readObject();
						if(!(tmp instanceof ArrayList<?>)) {
							sendMessage(Protocol.PROTOCOL_NOT_RESPECTED);
							throw new Exception ();
						}
			    	 ArrayList<Game> acqui=(ArrayList<Game>)tmp;
			    
			    	
			    	 listaAcquistati.addAll(acqui);
			    	 
					
					
					 tmp=in.readObject();
						if(!(tmp instanceof ArrayList<?>)) {
							
							sendMessage(Protocol.PROTOCOL_NOT_RESPECTED);
								throw new Exception ();
						}
					ArrayList<Game> piaciuti=(ArrayList<Game>)tmp;
			    
			    	
					preferenze.addAll(piaciuti);
					
			     }else if(message.equals(Protocol.MESSAGE)) {
			    	Object tmp=in.readObject();
						if(!(tmp instanceof Messaggio)) {
							
							sendMessage(Protocol.PROTOCOL_NOT_RESPECTED);
							throw new Exception ();
						}
				Messaggio m=(Messaggio)tmp;
				Messages.addMessage(m);
			     }
			     
			} catch (Exception e) {
				status=false;
				
				System.out.println("clientexception");
				
				reset();
			
				
			}
			
		}
		
	}
	public void changeImg(byte[]img) {
		sendMessagePrivate(Protocol.CHANGE_IMG);
	
		sendMessagePrivate(img);
		
		account.setImg(img);
	
	}
	
	public String[]getInfo(){
		return info;
	}
	
	

	public String login(String username,String password) {
		
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
	
public boolean aggiungiPreferito(Game g) {
	if(preferenze.contains(g)) {
		preferenze.remove(g);
		
		return true;
}
		else
			preferenze.add(g);
	
	
	return false;
	
	
}
	private boolean sendMessagePrivate(Object message) {
		if(out==null) {
			return false;
		}
			
		try {
		
			out.writeObject(message);
			
			out.flush();
			return true;
		} catch (IOException e) {
			
			reset();
			return false;
		}
	
		
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
    public synchronized ArrayList<Game> getGamePreferiti(){
    	
      	 ArrayList<Game>tmp=new ArrayList<Game>(preferenze);
      	
      	return  tmp;
      }

	public boolean addGameCarrello(Game tmp) {
		if( getGameAcquistati().contains(tmp)) {
			
			
			return false;
		}else
			
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
	
		sendMessage(Protocol.ACQUISTO);
		
		
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

	public DownloadGame getLastDownload() {
		return lastDownload;
	}

	public void setLastDownload(DownloadGame lastDownload) {
		this.lastDownload = lastDownload;
	}
	
	
}
