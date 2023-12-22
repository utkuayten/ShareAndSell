package org.cs320.ozyegin.service;

import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
