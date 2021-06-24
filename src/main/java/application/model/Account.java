package application.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Account implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -2476130011205638664L;
private String username;
private String email;
private String phone;
private String password;
private Date birthday;


@Override
public int hashCode() {
	return username.hashCode();
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Account tmp = (Account) obj;
	return username.equals(tmp.getUsername())&&
			email.equals(tmp.getEmail())&&
		phone.equals(tmp.getPhone());
}
public Account(String username, String email, String phone, String password,Date birthday) {
	super();
	this.username = username;
	this.email = email;
	this.phone = phone;
	this.password = password;

	this.birthday=birthday;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
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
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}

}
