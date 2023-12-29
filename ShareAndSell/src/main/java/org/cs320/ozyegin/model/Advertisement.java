package org.cs320.ozyegin.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    private int quantity;

    private int price;

    private Long seller_id;

    private Date date;


    public Advertisement() {
        super();
    }

    public Advertisement(String title, String description, int quantity, int price,Long seller_id, Date date) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.seller_id = seller_id;
        this.date = date;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString(){return date.toString().substring(0,10);};

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", seller_id=" + seller_id +
                ", date=" + date +
                '}';
    }
}
