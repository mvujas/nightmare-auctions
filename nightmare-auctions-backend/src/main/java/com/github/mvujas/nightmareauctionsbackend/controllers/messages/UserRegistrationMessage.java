package com.github.mvujas.nightmareauctionsbackend.controllers.messages;

import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegistrationMessage {

	@Size(
		min = 4, 
		max = 32, 
		message = "Username must be between 4 and 32 characters long")
	@Pattern(
		regexp = "^[\\w\\d]+$", 
		message = "Username can have only numbers and digits")
	@NotNull(message = "Username cannot be null")
	private String username;	
	
	@Size(
		min = 8, 
		message = "Password must be at least 8 characters long")
	@Pattern(
		regexp = "^.*[a-z].*$",
		message = "Password must contain a small letter")
	@Pattern(
		regexp = "^.*[A-Z].*$",
		message = "Password must contain a large letter")
	@Pattern(
		regexp = "^.*[0-9].*$",
		message = "Password must contain a digit")
	@Pattern(
		regexp = "^\\S*$",
		message = "Password cannot contain white spaces")
	@NotNull(message = "Password cannot be null")
	private String password;
	
	@Email(
		regexp="^\\S+@\\S+\\.\\S+$",
		message = "Invalid email format")
	@NotNull(message = "Email cannot be null")
	private String email;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "UserRegistrationMessage [username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	

}
