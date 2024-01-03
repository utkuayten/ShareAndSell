package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.*;
import org.cs320.ozyegin.model.Transaction;
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
class TransactionServiceTest {

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
    protected TransactionService transactionService;


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
    void createTransaction() throws IOException {
        List<Transaction> transactions = createRandomTransactions(1);
        transactionRepository.save(transactions.get(0));
        Transaction transactionFromDb = transactionService.findTransactionById(transactions.get(0).getId());
        assertEquals(transactions.get(0), transactionFromDb);
    }


    @Test
    void findAllTransactions() throws IOException {
        List<Transaction> transactions = createRandomTransactions(10);
        transactionRepository.saveAll(transactions);
        List<Transaction> transactionsFromDb = transactionService.findAllTransactions();
        for (Transaction transaction : transactions) {
            assertTrue(transactionsFromDb.contains(transaction));
        }
    }

    @Test
    void deleteTransaction() throws IOException {
        List<Transaction> transactions = createRandomTransactions(1);
        transactionRepository.save(transactions.get(0));
        transactionService.deleteTransaction(transactions.get(0));
        Transaction transactionFromDb = transactionService.findTransactionById(transactions.get(0).getId());
        assertNull(transactionFromDb);
    }


    List<Transaction> createRandomTransactions(int size) {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Transaction transaction = new Transaction();
            transaction.setAddress(RandomStringUtils.random(8));
            transaction.setQuantity((int) (Math.random() * 1000));
            transaction.setProduct_id(ThreadLocalRandom.current().nextLong(1000L, 5000L + 1));
            transaction.setBuyer_id(ThreadLocalRandom.current().nextLong(1000L, 5000L + 1));
            transaction.setSeller_id(ThreadLocalRandom.current().nextLong(1000L, 5000L + 1));
            transactions.add(transaction);
        }
        return transactions;
    }

}