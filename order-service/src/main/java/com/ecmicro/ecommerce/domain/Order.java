package com.ecmicro.ecommerce.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity()
@Table(name = "orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Date orderDate;

    private String status;

    @Column(name = "total_price")
    private Float totalPrice;

    @OneToMany(
//          fetch = FetchType.EAGER,  // use when want automatically fetch all items by default
            mappedBy = "orderId",   // map to orderId field
            cascade = CascadeType.ALL,  // e.g. delete an Order, all OrderItem children are also deleted
            orphanRemoval = true    // If a child entity (OrderItem) is removed from the parent's collection & is no longer referenced by others -> will be deleted from the db
    )
    private List<OrderItem> items = new ArrayList<>();
}