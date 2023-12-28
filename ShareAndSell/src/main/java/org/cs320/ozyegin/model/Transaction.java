package org.cs320.ozyegin.model;


import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int seller_id;
    private int buyer_id;
    private int product_id;
    private int price;
    private int quantity;

    public Transaction(){
        super();
    }

    public Transaction(Long id, int seller_id, int product_id, int price, int quantity) {
        this.id = id;
        this.seller_id = seller_id;
        this.product_id = product_id;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
