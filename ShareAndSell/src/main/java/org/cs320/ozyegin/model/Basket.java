package org.cs320.ozyegin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long buyer_id;
    private Long product_id;
    private int quantity;

    public Basket() {
        super();
    }

    public Basket(Long id, Long buyer_id, Long product_id, int quantity) {
        this.id = id;
        this.buyer_id = buyer_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }


    public Long getId() {
        return id;
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
        return "Basket{" +
                "id=" + id +
                ", buyer_id=" + buyer_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}
