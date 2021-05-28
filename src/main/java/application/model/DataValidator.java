package application.model;

import java.util.regex.Pattern;

public class DataValidator {

	
	public DataValidator() {
		
	}
	
	public boolean parseEmail(String email) {
			
		return  Pattern.matches("[a-zA-Z]+@[a-zA-Z]+\\.(com|it)",email);

	}
	
	public boolean parsePassword(String password) {
    
		String regex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};’:”\\|,.<>\\/?])(?=\\S+$).{7,}$";
	    return Pattern.matches(regex, password);
	}
	
	
	public boolean parseUsername(String username) {
		boolean lenghtMin=username.length()>4;
		
		boolean lenghtMax=username.length()<16;
		
		return  Pattern.matches("[a-zA-Z0-9]+",username)  && lenghtMin && lenghtMax;
	}
	public boolean parsePhone(String numberPhone) {
		boolean lenght=numberPhone.length()==10;
		return  Pattern.matches("[0-9]{10}",numberPhone) && lenght;
		
	}
	
	
}

