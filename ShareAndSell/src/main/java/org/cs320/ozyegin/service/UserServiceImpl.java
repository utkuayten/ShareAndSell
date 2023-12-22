package org.cs320.ozyegin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.repository.UserRepository;
import org.cs320.ozyegin.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	//private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		String userRole = "LOGGED_IN"; // Set the role name

		User user = new User().name(registrationDto.getName()).password(registrationDto.getPassword()).role(userRole).email(registrationDto.getEmail());

		return userRepository.save(user);
	}
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		// Grant authority based on the role condition
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (user.getRole() != null && user.getRole().equals("LOGGED_IN")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_LOGGED_IN")); // Grant the authority
		}

		// Create the UserDetails object with username, password, and authorities

        return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				authorities
		);
	}


}
