package org.cs320.ozyegin.service;


import org.cs320.ozyegin.data_layer.TransactionRepository;
import org.cs320.ozyegin.data_layer.WalletRepository;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Transaction;
import org.cs320.ozyegin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionServiceImp implements TransactionService {


    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public WalletRepository walletRepository;
    @Override
    public Transaction saveTransaction(Transaction transaction, User seller, User buyer, Advertisement advertisement) {
        transaction.setStatus("STATUS_PENDING");
        transaction.setProduct_id(advertisement.getId());
        transaction.setBuyer_id(buyer.getId());
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAllTransactions();
    }



//    @Override
//    public List<Transaction> findBasket(User user) {
//        return transactionRepository.findBasket(user.getId());
//    }

}
