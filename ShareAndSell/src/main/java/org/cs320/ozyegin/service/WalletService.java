package org.cs320.ozyegin.service;


import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletService {

    Wallet saveWallet(Wallet wallet, User user);

    Wallet updateBalance(Wallet wallet, int newBalance);

    Wallet findWalletByOwner(User user);

    Wallet findWalletByUserId(Long user_id);

    Wallet findWalletById(Long id);

    void deleteWallet(Wallet wallet);

    List<Wallet> findAllWallets();

}
