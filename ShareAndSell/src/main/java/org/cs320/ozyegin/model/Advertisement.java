package org.cs320.ozyegin.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seller_id")
    private Long sellerId;

    @Lob
    @Column(name = "image_data", columnDefinition = "bytea")
    private byte[] image;

    // Constructors, getters, and setters

    public Advertisement() {
        // Default constructor
    }

    public Advertisement(Long sellerId, byte[] image) {
        this.sellerId = sellerId;
        this.image = image;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(id, that.id) && Objects.equals(sellerId, that.sellerId) && Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, sellerId);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
