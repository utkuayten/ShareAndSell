package org.cs320.ozyegin.data_layer;

import org.cs320.ozyegin.model.User;
import org.cs320.ozyegin.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {


    @Query("SELECT wallet FROM Wallet wallet WHERE wallet.owner_id = :owner_id")
    Wallet findByOwner_id(@Param("owner_id") Long owner_id);
}
