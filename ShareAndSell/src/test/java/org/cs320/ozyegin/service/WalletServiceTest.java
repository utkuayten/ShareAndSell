package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.*;
import org.cs320.ozyegin.model.Advertisement;
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
class WalletServiceTest {

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
    void createWallet() throws IOException {
        List<Wallet> wallets = createRandomWallet(1);
        walletRepository.save(wallets.get(0));
        Wallet walletFromDb = walletService.findWalletById(wallets.get(0).getId());
        assertEquals(wallets.get(0), walletFromDb);
    }


    @Test
    void updateWallet() throws IOException {
        List<Wallet> wallets = createRandomWallet(1);
        walletRepository.save(wallets.get(0));
        Wallet updatedWallet = wallets.get(0);
        int balance = updatedWallet.getBalance();
        walletService.updateBalance(updatedWallet, 100); // Modify balance
        Wallet walletFromDb = walletService.findWalletById(updatedWallet.getId());
        assertEquals(balance + 100, walletFromDb.getBalance()); // Verify the update
    }

    @Test
    void deleteWallet() throws IOException {
        List<Wallet> wallets = createRandomWallet(1);
        walletRepository.save(wallets.get(0));
        walletService.deleteWallet(wallets.get(0));
        Wallet walletFromDb = walletService.findWalletById(wallets.get(0).getId());
        assertNull(walletFromDb);
    }


    @Test
    void findAllWallets() throws IOException {
        List<Wallet> wallets = createRandomWallet(10);
        walletRepository.saveAll(wallets);
        List<Wallet> walletsFromDb = walletService.findAllWallets();
        for (Wallet wallet : wallets) {
            assertTrue(walletsFromDb.contains(wallet));
        }
    }


    List<Wallet> createRandomWallet(int size) {
        List<Wallet> wallets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Wallet wallet = new Wallet();
            wallet.setBalance(((int) (Math.random() * 1000)));
            wallets.add(wallet);
        }
        return wallets;
    }

}