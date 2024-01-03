package org.cs320.ozyegin.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long product_id;
    private Long buyer_id;
    private int quantity;
    private String address;
    private Long seller_id;


    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }
    public Transaction(){
        super();
    }

    public Transaction(Long id, String address, Long product_id, Long buyer_id, int quantity) {
        this.id = id;
        this.address = address;
        this.buyer_id = buyer_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Long getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(Long buyer_id) {
        this.buyer_id = buyer_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", buyer_id=" + buyer_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(product_id, that.product_id) && Objects.equals(buyer_id, that.buyer_id) && Objects.equals(address, that.address) && Objects.equals(seller_id, that.seller_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product_id, buyer_id, quantity, address, seller_id);
    }
}
