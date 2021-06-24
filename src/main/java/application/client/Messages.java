package application.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import application.model.Messaggio;

public class Messages {

	private static ArrayList<Messaggio> allMessages = new ArrayList<Messaggio>();


	public synchronized static void addMessage(Messaggio message) {
	
		allMessages.add(message);
		
		//canRead=true;
	}
	
	public synchronized static ArrayList<Messaggio> readMessages() {
		
		ArrayList<Messaggio> tmp = new ArrayList<Messaggio>();
		for(Messaggio mess : allMessages) {
			tmp.add(mess);
			
		}
		
		
		//allMessages.clear();
		
		return tmp;
	}
	
	
}
