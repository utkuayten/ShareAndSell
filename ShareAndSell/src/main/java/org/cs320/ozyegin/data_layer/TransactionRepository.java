package org.cs320.ozyegin.data_layer;

import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.seller_id = :seller_id")
    Transaction findBySeller_id(@Param("seller_id") Long seller_id);

    @Query("SELECT transaction FROM Transaction transaction")
    List<Transaction> findAllTransactions();

//    public static final String STATUS_IN_BASKET = "STATUS_IN_BASKET";
//    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.buyer_id = :buyer_id and transaction.status = " + STATUS_IN_BASKET)
//    List<Transaction> findBasket(@Param("buyer_id") Long buyerId);


}
