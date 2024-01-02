package org.cs320.ozyegin.model;


import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long product_id;
    private Long buyer_id;
    private int quantity;
    private String status;
    private String address;
    public Transaction(){
        super();
    }

    public Transaction(Long id, String address, Long product_id, Long buyer_id, int quantity, String status) {
        this.id = id;
        this.address = address;
        this.buyer_id = buyer_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", buyer_id=" + buyer_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
}
