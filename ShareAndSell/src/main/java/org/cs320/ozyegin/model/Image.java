package org.cs320.ozyegin.model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.util.Arrays;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.cs320.ozyegin.dtonutil.ImageUtil;

@Entity
@Table(name = "images")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String type;
    private Long owner_id;

    //    @Lob
    private byte[] imageData;
    private String purpose;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public byte[] get_image() {
        return ImageUtil.decompressImage(imageData);
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", owner_id=" + owner_id +
                ", imageData=" + Arrays.toString(imageData) +
                '}';
    }
}
