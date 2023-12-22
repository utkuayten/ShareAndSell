package org.cs320.ozyegin.model;


public class User {
	private int id;
	private String name;
	private String role;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public User email(String email){
		this.email = email;
		return this;
	}

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

	private String email;
	private String password;

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
}