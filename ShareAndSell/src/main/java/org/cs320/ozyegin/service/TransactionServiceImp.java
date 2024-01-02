package org.cs320.ozyegin.service;


import org.cs320.ozyegin.data_layer.TransactionRepository;
import org.cs320.ozyegin.data_layer.UserRepository;
import org.cs320.ozyegin.data_layer.WalletRepository;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Basket;
import org.cs320.ozyegin.model.Transaction;
import org.cs320.ozyegin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class TransactionServiceImp implements TransactionService {


    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public WalletRepository walletRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AdvertService advertService;
    @Override
    public Transaction saveTransaction(Transaction transaction, User buyer, Basket basket, String address) {
        transaction.setProduct_id(basket.getProduct_id());
        transaction.setQuantity(basket.getQuantity());
        transaction.setBuyer_id(buyer.getId());
        transaction.setAddress(address);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAllTransactions();
    }

    @Override
    public List<Transaction> createMultipleTransactionsByBasket(List<Basket> basket, String address) {
        List<Transaction> transactions = new LinkedList<>();
        for (Basket item : basket) {
            Advertisement advert = advertService.findAdvertByID(item.getProduct_id());
            User buyer = userRepository.findByID(item.getBuyer_id());
            transactions.add(saveTransaction(new Transaction(), buyer, item, address));
        }
        return transactions;
    }


//    @Override
//    public List<Transaction> findBasket(User user) {
//        return transactionRepository.findBasket(user.getId());
//    }

}
