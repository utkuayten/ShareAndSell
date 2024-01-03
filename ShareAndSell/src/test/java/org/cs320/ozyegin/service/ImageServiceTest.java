package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.*;
import org.cs320.ozyegin.model.Image;
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
class ImageServiceTest {

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
    protected WalletService walletService;
    @Autowired
    protected ImageRepository imageRepository;
    @Autowired
    protected TransactionRepository transactionRepository;
    @Autowired
    protected ImageService imageService;


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
    void createImage() throws IOException {
        List<Image> images = createRandomImages(1);
        imageRepository.save(images.get(0));
        Image imageFromDb = imageRepository.findByOwner_id(images.get(0).getOwner_id());
        assertEquals(images.get(0), imageFromDb);
    }

    @Test
    void findAllImages() throws IOException {
        List<Image> images = createRandomImages(10);
        imageRepository.saveAll(images);
        List<Image> imagesFromDb = imageRepository.findAll();
        for (Image image : images) {
            assertTrue(imagesFromDb.contains(image));
        }
    }

    @Test
    void deleteImage() throws IOException {
        List<Image> images = createRandomImages(1);
        imageRepository.save(images.get(0));
        imageRepository.delete(images.get(0));
        Image imageFromDb = imageRepository.findByOwner_id(images.get(0).getId());
        assertNull(imageFromDb);
    }


    List<Image> createRandomImages(int size) {
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Image image = new Image();
            image.setName(RandomStringUtils.random(8));
            image.setType(RandomStringUtils.random(8));
            image.setPurpose(RandomStringUtils.random(8));
            image.setOwner_id(ThreadLocalRandom.current().nextLong(1000L, 5000L + 1));
            images.add(image);
        }
        return images;
    }

}