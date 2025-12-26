package com.ecmicro.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
//@Table(name = "products")   // used when table name differs from class name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;   // should use BigDecimal for monetary values

    private String description;

    @Column(name = "seller_id")
    private Long sellerId;

    private Double quantity;

//  many products can belong to one category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    // custom constructor
//    public Product(Long id, String name, Float price) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }
}
