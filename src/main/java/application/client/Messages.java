package application.client;

import java.util.ArrayList;

import application.model.Messaggio;

public class Messages {

	private static ArrayList<Messaggio> allMessages = new ArrayList<Messaggio>();


	public synchronized static void addMessage(Messaggio message) {
	
		allMessages.add(message);
		
	}
	
	public synchronized static ArrayList<Messaggio> readMessages() {
		
		ArrayList<Messaggio> tmp = new ArrayList<Messaggio>();
		for(Messaggio mess : allMessages) {
			tmp.add(mess);
			
		}
		
		
		return tmp;
	}
	
	
}
