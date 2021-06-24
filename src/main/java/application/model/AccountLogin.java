package application.model;

import java.io.Serializable;

public class AccountLogin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7120804379463823531L;
	private String password;
	private String username;
	private byte[] img;
	
	public AccountLogin(String username,String password) {
		this.password=password;
		this.username=username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	

}
