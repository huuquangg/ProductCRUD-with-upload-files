package com.cpyproject2spring.demo2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// @Builder force all fields to be required not nullable
@Entity
@Data
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String productName;
    private int productManuYear;
    private double productPrice;
    private String productUrl;

    public Product() {
    }
    // Constructor already created by Lombok

}
