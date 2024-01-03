package org.cs320.ozyegin.service;


import org.cs320.ozyegin.model.User;

import java.util.List;


public interface UserService {



	User saveUser(User user);
	User findUserByID(Long id);

    void deleteUser(User user);

    List<User> findAllUsers();
}
