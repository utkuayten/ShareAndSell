package org.cs320.ozyegin.service;


import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Transaction;
import org.cs320.ozyegin.model.User;

import java.util.List;

public interface TransactionService {


    Transaction saveTransaction(Transaction transaction, User seller, User Buyer, Advertisement advertisement);

    List<Transaction> findAllTransactions();

    List<Transaction> findBasket(User buyer);
}