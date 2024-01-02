package org.cs320.ozyegin.data_layer;

import jakarta.transaction.Transactional;
import org.cs320.ozyegin.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdvertRepository extends JpaRepository<Advertisement, Long> {

    @Query("SELECT advert FROM Advertisement advert WHERE advert.title = :title")
    Advertisement findByTitle(@Param("title") String title);
    @Query("SELECT advert FROM Advertisement advert WHERE advert.id = :id")
    Advertisement findByID(@Param("id") Long id);

    @Query("SELECT advert FROM Advertisement advert WHERE LOWER(advert.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Advertisement> findByPartialTitle(@Param("query") String query);

    @Query("SELECT advert FROM Advertisement advert ")
    List<Advertisement> findAllAdverts();

    @Query("SELECT advert FROM Advertisement advert WHERE advert.seller_id <> :userId")
    List<Advertisement> findAllAdvertsExcludingUser(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Advertisement advert SET advert.quantity = :quantity WHERE advert.id = :product_id ")
    void updateAdvertQuantityByProductId(@Param("product_id") Long product_id, @Param("quantity") int quantity);

    @Query("SELECT advert.quantity FROM Advertisement advert WHERE advert.id = :id")
    int getQuantById(@Param("id") Long id);

    @Query("UPDATE Advertisement advert SET advert.active = false WHERE advert.id = :id")
    void updateAdvertStat(@Param("id") Long id);

}
