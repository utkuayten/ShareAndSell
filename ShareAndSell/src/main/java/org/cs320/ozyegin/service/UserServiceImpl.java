package org.cs320.ozyegin.service;

import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.data_layer.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		String password=passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");
        return userRepository.save(user);
	}

	@Override
	public User findUserByID(Long id) {
		return userRepository.findByID(id);
	}


}