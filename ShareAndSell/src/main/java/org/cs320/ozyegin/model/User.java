package org.cs320.ozyegin.model;


public class User {

	private String mail;
	private String name;
	private String role;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User password(String password){
		this.password = password;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public User name(String name) {
		this.name = name;
		return this;
	}

	public String getRole() {
		return role;
	}


	public User role(String role){
		this.role = role;
		return this;
	}

	public void setEmail(String email) {
		this.mail = email;
	}
	public String getEmail() {
		return mail;
	}

	public User email(String email){
		this.mail = email;
		return this;
	}

}