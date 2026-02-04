package com.ecmicro.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)   // enable auditing (e.g. auto set createdAt, updatedAt)
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @CreatedDate    // auto set when first created
    @Column(updatable = false, nullable = false, name = "created_date")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    private String status;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(
//          fetch = FetchType.EAGER,  // use when want automatically fetch all items by default
            mappedBy = "order",   // map to 'order' field
            cascade = CascadeType.ALL,  // e.g. delete an Order, all OrderItem children are also deleted
// If a child entity (OrderItem) is removed from the parent's collection & is no longer referenced by others -> will be deleted from the db
            orphanRemoval = true
    )
//    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();
}