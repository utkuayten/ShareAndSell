package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.*;
import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    protected AdvertService advertService;

    @Autowired
    protected AdvertRepository advertRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected BasketRepository basketRepository;
    @Autowired
    protected WalletRepository walletRepository;
    @Autowired
    protected WalletService walletService;
    @Autowired
    protected ImageRepository imageRepository;
    @Autowired
    protected TransactionRepository transactionRepository;


    @BeforeEach
    void setUp() {
        basketRepository.deleteAll();
        transactionRepository.deleteAll();
        advertRepository.deleteAll();
        walletRepository.deleteAll();
        imageRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void createUser() throws IOException {
        List<User> users = createRandomUsers(1);
        userRepository.save(users.get(0));
        User userFromDb = userService.findUserByID(users.get(0).getId());
        System.out.println(userFromDb);
        assertEquals(users.get(0), userFromDb);
    }

    @Test
    void deleteUser() throws IOException {
        List<User> users = createRandomUsers(1);
        userRepository.save(users.get(0));
        userService.deleteUser(users.get(0));
        User userFromDb = userService.findUserByID(users.get(0).getId());
        assertNull(userFromDb);
    }

    @Test
    void findAllUsers() throws IOException {
        List<User> users = createRandomUsers(10);
        userRepository.saveAll(users);
        List<User> usersFromDb = userService.findAllUsers();
        for (User user : users) {
            assertTrue(usersFromDb.contains(user));
        }
    }


    List<User> createRandomUsers(int size) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            User user = new User();
            user.setName(RandomStringUtils.random(8));
            user.setEmail(RandomStringUtils.random(8));
            user.setPassword(RandomStringUtils.random(8));
            user.setRole(RandomStringUtils.random(8));
            users.add(user);
        }
        return users;
    }

}