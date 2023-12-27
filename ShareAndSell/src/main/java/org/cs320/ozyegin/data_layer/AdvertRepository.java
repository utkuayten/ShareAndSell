package org.cs320.ozyegin.data_layer;

import org.cs320.ozyegin.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdvertRepository extends JpaRepository<Advertisement, Long> {

    @Query("SELECT advert FROM Advertisement advert WHERE advert.title = :title")
    Advertisement findByTitle(@Param("title") String title);

    @Query("SELECT advert FROM Advertisement advert ")
    List<Advertisement> findAllAdverts();


}