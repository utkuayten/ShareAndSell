package org.cs320.ozyegin.service;


import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.model.Wallet;

public interface WalletService {

    Wallet saveWallet(Wallet wallet, User user);

    Wallet updateBalance(Wallet wallet, int newBalance);

    Wallet findWalletByOwner(User user);

}
