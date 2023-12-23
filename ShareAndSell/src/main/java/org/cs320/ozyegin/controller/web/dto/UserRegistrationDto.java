package org.cs320.ozyegin.controller.web.dto;

public class UserRegistrationDto {
	private String name;
	private String email;
	private String password;

	public String getRole() {
		return role;
	}

	private void setRole(String role) {
		this.role = role;
	}

	private String role;

	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String name,  String email, String password,String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
