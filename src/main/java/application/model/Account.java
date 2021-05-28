package application.model;

public class Account {
	private String username;
	private String email;
	private String password;
	
	public Account(String email,String pw){
		this.setEmail(email);
		this.setPassword(pw);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
}
