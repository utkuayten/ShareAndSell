package org.cs320.ozyegin.data_layer;

import jakarta.transaction.Transactional;
import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("SELECT basket FROM Basket basket WHERE basket.buyer_id = :buyer_id")
    List<Basket> findBasketByBuyer(@Param("buyer_id") Long buyer_id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Basket basket WHERE basket.id = :id")
    void deleteByBasketId(@Param("id") Long id);

    @Query("SELECT basket FROM Basket basket WHERE basket.id = :id")
    Basket findBasketById(@Param("id") Long id);

    @Query("SELECT basket FROM Basket basket WHERE basket.product_id = :product_id and  basket.buyer_id =:buyer_id")
    Basket findBasketByProductId(@Param("product_id") Long product_id, @Param("buyer_id") Long buyer_id);

    @Transactional
    @Modifying
    @Query("UPDATE Basket basket SET basket.quantity = :quantity WHERE basket.product_id = :product_id AND basket.buyer_id = :buyer_id")
    void updateBasketQuantityByProductId(@Param("product_id") Long product_id, @Param("buyer_id") Long buyer_id, @Param("quantity") int quantity);

    @Transactional
    @Modifying
    @Query("delete Basket basket  WHERE basket.buyer_id = :buyer_id ")
    void deleteBasketByUser(@Param("buyer_id") Long buyer_id);

}
