package by.myproject.news.bean;

import java.io.Serializable;
import java.util.Objects;

public class RegistrationInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String firstPassword;
	private String secondPassword;
	private String surname;
	
	public RegistrationInfo() {
	}
		
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstPassword() {
		return firstPassword;
	}
	
	public void setFirstPassword(String firstPassword) {
		this.firstPassword = firstPassword;
	}
	
	public String getSecondPassword() {
		return secondPassword;
	}
	
	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    result = prime * result + ((name == null) ? 0 : name.hashCode());            
	    result = prime * result + ((surname == null) ? 0 : surname.hashCode());
	    result = prime * result + ((email == null) ? 0 : email.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationInfo other = (RegistrationInfo) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + "name:" + name + ", surname:" + surname + ", email:" + email;
	}
	
}
