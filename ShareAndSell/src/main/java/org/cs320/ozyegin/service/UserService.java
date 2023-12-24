package org.cs320.ozyegin.service;

import org.cs320.ozyegin.controller.web.dto.UserRegistrationDto;
import org.cs320.ozyegin.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
	User save(UserRegistrationDto registrationDto) throws Exception;
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	User find(UserRegistrationDto registrationDto);
}
