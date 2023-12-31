package org.cs320.ozyegin.model;


import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long seller_id;
    private Long buyer_id;
    private Long product_id;
    private int price;
    private int quantity;
    private String status;
    public Transaction(){
        super();
    }

    public Transaction(Long id, Long seller_id, Long product_id,Long buyer_id, int price, int quantity,String status) {
        this.id = id;
        this.seller_id = seller_id;
        this.buyer_id = buyer_id;
        this.product_id = product_id;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
