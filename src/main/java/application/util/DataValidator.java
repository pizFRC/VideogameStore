package application.util;

import java.util.regex.Pattern;

public class DataValidator {

	
	public DataValidator() {
		
	}
	
	public static boolean parseEmail(String email) {
			
		String regex="^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
		return  Pattern.matches(regex,email);

	}
	
	public static boolean parsePassword(String password) {
		
		String regex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};’:”\\|,.<>\\/?])(?=\\S+$).{7,}$";
	    return Pattern.matches(regex, password);
	}
	
	
	public static boolean parseUsername(String username) {
		
		
		return  Pattern.matches("^[A-Za-z]\\w{5,20}$",username) ;
	}
	public static boolean parsePhone(String numberPhone) {
		boolean lenght=numberPhone.length()==10;
		return  Pattern.matches("(3)[0-9]{9}",numberPhone) && lenght;
		
	}
	
	
}

