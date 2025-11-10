package com.ecommerce.product_service.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float price;

    @Column(name = "seller_id")
    private Long sellerId;

//    // custom constructor
//    public Product(Long id, String name, Float price) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }
}
