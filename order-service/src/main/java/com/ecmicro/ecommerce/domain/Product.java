package com.ecmicro.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;

    private String name;

    private Float price;

    private Long sellerId;

    private String category;

//    // custom constructor
//    public Product(Long id, String name, Float price) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }
}
