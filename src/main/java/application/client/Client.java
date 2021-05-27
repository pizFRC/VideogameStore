package application.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import application.client.Client;


public class Client implements Runnable{

	private ObjectOutputStream out=null;
	private ObjectInputStream in=null;
	private Socket socket=null;
	private static Client instance=null;
	private Client() {
		try {
			socket=new Socket("localhost",8000);
			out=new ObjectOutputStream(socket.getOutputStream());
		}  catch (IOException e) {
			//questo riguarda il client e gli mostro che ho problemi di connessione
			
			//qui posso passare dal controller
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		System.out.println("partito");
		
	}
	public static Client getInstance() {
		if(instance==null)
			instance=new Client();
		return instance;
	}

	

}
