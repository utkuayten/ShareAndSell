package org.cs320.ozyegin.service;

import org.cs320.ozyegin.data_layer.AdvertRepository;
import org.cs320.ozyegin.data_layer.WalletRepository;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    public WalletRepository walletRepository;


    @Override
    public Wallet saveWallet(Wallet wallet, User user) {
        wallet.setOwner_id(user.getId());
        wallet.setBalance(0);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletByOwner_id(User user) {
        return walletRepository.findByOwner_id(user.getId());
    }


}