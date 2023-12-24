package org.cs320.ozyegin.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.cs320.ozyegin.controller.web.dto.UserRegistrationDto;
import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;


	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) throws Exception {
		User user= new User();
		user.setEmail(registrationDto.getEmail());
		user.setName(registrationDto.getName());
		user.setPassword(registrationDto.getPassword());
		user.setRole("ROLE_USER");
		return userRepository.save(user);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRoleToAuthority(user.getRole()));
	}

	@Override
	public User find(UserRegistrationDto registrationDto) {
		// Implement your logic to find the user based on the registrationDto
		// This method seems to be returning null, you'll want to handle the user lookup here.
		// Assuming you retrieve the user object somehow...
		User user = new User();
		user.setRole(registrationDto.getRole());
		user.setMail(registrationDto.getEmail());
		user.setPassword(registrationDto.getPassword());
		user.setName(registrationDto.getName());
				// Extract role from the registrationDto or user object

		// Map role to authority
		Collection<GrantedAuthority> authorities = mapRoleToAuthority(user.getRole());

		// Set authorities to the user
		user.setAuthorities(authorities); // Assuming User class has a method to set authorities

		return user;
	}

	private Collection<GrantedAuthority> mapRoleToAuthority(String role){
		// You might want to perform validation or error handling if the role is invalid
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}

}