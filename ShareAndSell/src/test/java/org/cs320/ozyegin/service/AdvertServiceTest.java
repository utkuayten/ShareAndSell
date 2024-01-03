package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.*;
import org.cs320.ozyegin.model.Advertisement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdvertServiceTest {

    @Autowired
    protected AdvertService advertService;

    @Autowired
    protected AdvertRepository advertRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected BasketRepository basketRepository;
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
    void createAdvertisement() throws IOException {
        List<Advertisement> advertisements = createRandomAdvertisement(1);
        advertRepository.save(advertisements.get(0));
        Advertisement advertisementFromDB = advertService.findAdvertByID(advertisements.get(0).getId());
        assertEquals(advertisements.get(0), advertisementFromDB);
        advertRepository.delete(advertisements.get(0));
    }


    @Test
    void findAllAdvertisements() {
        List<Advertisement> advertisements = createRandomAdvertisement(10);
        advertRepository.saveAll(advertisements);
        assertEquals(advertisements, advertRepository.findAll());
    }


    @Test
    void findAdvertByID() {
        List<Advertisement> advertisements = createRandomAdvertisement(1);
        Advertisement myAdvert = advertisements.get(0);
        advertRepository.save(myAdvert);
        assertEquals(myAdvert, advertRepository.findByID(myAdvert.getId()));
    }


    List<Advertisement> createRandomAdvertisement(int size) {
        List<Advertisement> advertisements = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Advertisement advertisement = new Advertisement();
            advertisement.setTitle(RandomStringUtils.random(8));
            advertisement.setDescription(RandomStringUtils.random(8));
            advertisement.setSeller_name(RandomStringUtils.random(8));
            advertisement.setQuantity(((int) (Math.random() * 1000)));
            advertisement.setPrice(((int) (Math.random() * 1000)));
            advertisement.setDate(null);
            advertisement.setImageData(null);
            advertisement.setSeller_id(null);
            advertisement.setActive(true);
            advertisements.add(advertisement);
        }
        return advertisements;
    }

}