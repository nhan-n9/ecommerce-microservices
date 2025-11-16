package com.ecommerce.order_service.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "prod_id")
    private Long prodId;

    private Integer quantity;
}
