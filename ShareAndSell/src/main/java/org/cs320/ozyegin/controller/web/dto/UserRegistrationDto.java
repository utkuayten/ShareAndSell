package org.cs320.ozyegin.controller.web.dto;

public class UserRegistrationDto {
	private String name;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String email;
	private String password;
	private String role; // Assuming a role field exists for user roles

	// Constructors (default and parameterized)
	public UserRegistrationDto() {
		// Default constructor
	}

	public UserRegistrationDto(String name, String email, String password, String role ) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	// Getters and setters for all fields
	// For example:
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// ... getEmail(), setEmail(), getPassword(), getRole(), setRole() for other fields

	// Override toString() method for debugging/logging purposes
	@Override
	public String toString() {
		return "UserRegistrationDto{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
