package org.cs320.ozyegin.data_layer;

import org.cs320.ozyegin.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT image FROM Image image WHERE image.owner_id = :owner_id")
    Image findByOwner_id(@Param("owner_id") Long owner_id);
}
