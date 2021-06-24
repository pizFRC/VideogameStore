package application.model;

import java.io.Serializable;

public class Messaggio implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 7252228639650509436L;
	private byte[]img;
	private String username;
	private String text;
	private String time;

	public Messaggio( String username, String time,String mess,byte[]img) {
		
		
			this.username = username;
			this.img =img;
			this.text=mess;
		      this.time=time;
	}
	public byte[] getImg() {
		return img;
	}

	public String getUsername() {
		return username;
	}

	public String getText() {
		return text;
	}
	public String getTime() {
		return time;
	}


}
