package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.*;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Basket;
import org.cs320.ozyegin.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class BasketServiceTest {

    @Autowired
    protected AdvertService advertService;

    @Autowired
    protected AdvertRepository advertRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected BasketRepository basketRepository;
    @Autowired
    protected BasketService basketService;
    @Autowired
    protected WalletRepository walletRepository;
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
    void createBasket() throws IOException {
        List<Basket> baskets = createRandomBaskets(1);
        basketRepository.save(baskets.get(0));
        Basket basketFromDb = basketService.findBasketById(baskets.get(0).getId());
        assertEquals(baskets.get(0), basketFromDb);
    }

    @Test
    void deleteBasket() throws IOException {
        List<Basket> baskets = createRandomBaskets(1);
        basketRepository.save(baskets.get(0));
        basketService.deleteBasket(baskets.get(0));
        Basket basketFromDb = basketService.findBasketById(baskets.get(0).getId());
        assertNull(basketFromDb);
    }


    @Test
    void findBasketQuantity() {
        List<Basket> baskets = createRandomBaskets(1);
        Basket myBasket = baskets.get(0);
        basketRepository.save(myBasket);
        User user = new User();
        user.setId(myBasket.getBuyer_id());
        assertEquals(myBasket, basketService.findBasketByUser(user).get(0));
    }

    List<Basket> createRandomBaskets(int size) {
        List<Basket> baskets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Basket basket = new Basket();
            basket.setQuantity(((int) (Math.random() * 1000)));
            basket.setProduct_id(ThreadLocalRandom.current().nextLong(1000L, 5000L + 1));
            basket.setBuyer_id(ThreadLocalRandom.current().nextLong(1000L, 5000L + 1));

            //No need to give random variables for following:
            baskets.add(basket);
        }
        return baskets;
    }

}