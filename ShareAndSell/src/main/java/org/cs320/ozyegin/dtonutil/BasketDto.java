package org.cs320.ozyegin.dtonutil;

import org.cs320.ozyegin.model.Advertisement;
import org.cs320.ozyegin.service.AdvertService;
import org.cs320.ozyegin.service.AdvertServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class BasketDto {

    private Long id;
    private Long buyer_id;
    private Long product_id;
    private Advertisement product;
    private int quantity;

    public BasketDto(Long id, Long buyer_id, Long product_id, int quantity) {
        this.id = id;
        this.buyer_id = buyer_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

    public Advertisement getProduct() {
        return product;
    }

    public void setProduct(Advertisement product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "BasketDto{" +
                "id=" + id +
                ", buyer_id=" + buyer_id +
                ", product_id=" + product_id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
