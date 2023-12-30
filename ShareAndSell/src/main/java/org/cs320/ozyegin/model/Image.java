package org.cs320.ozyegin.model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

    @Lob
    private byte[] imageData;

}
