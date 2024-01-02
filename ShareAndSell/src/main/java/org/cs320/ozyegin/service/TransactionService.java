package org.cs320.ozyegin.service;


import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Basket;
import org.cs320.ozyegin.model.Transaction;
import org.cs320.ozyegin.model.User;

import java.util.List;

public interface TransactionService {


    Transaction saveTransaction(Transaction transaction, User buyer, Basket basket, String address, User seller);

    List<Transaction> findAllTransactions();

    List<Transaction> createMultipleTransactionsByBasket(List<Basket> basket, String address);

}