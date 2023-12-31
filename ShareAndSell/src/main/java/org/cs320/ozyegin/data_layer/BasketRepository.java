package org.cs320.ozyegin.data_layer;

import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("SELECT basket FROM Basket basket WHERE basket.buyer_id = :buyer_id")
    List<Basket> findBasketByBuyer(@Param("buyer_id") Long buyer_id);
}
