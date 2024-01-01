package org.cs320.ozyegin.model;

import jakarta.persistence.*;
import org.cs320.ozyegin.dtonutil.ImageUtil;

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

    private String seller_name;

    private Date date;
    private byte[] imageData;


    public Advertisement() {
        super();
    }

    public Advertisement(String title, String description, int quantity, int price, Long seller_id, Date date, byte[] imageData, String seller_name) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.seller_id = seller_id;
        this.date = date;
        this.imageData = imageData;
        this.seller_name = seller_name;
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

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }
    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString(){return date.toString().substring(0,10);};

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImage() {
        if (imageData != null && imageData.length > 0) {
            String base64Image = java.util.Base64.getEncoder().encodeToString(ImageUtil.decompressImage(imageData));
            return "data:image/*;base64," + base64Image;
        } else {
            System.out.println("error getting images");
            return "";
        }
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
                ", seller_name=" + seller_name +
                '}';
    }
}
