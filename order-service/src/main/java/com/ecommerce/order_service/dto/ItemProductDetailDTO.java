package com.ecommerce.order_service.dto;

import com.ecommerce.order_service.domain.Product;
import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemProductDetailDTO {
    private Long id;

    private Long orderId;

    private Integer quantity;

    private Product product;
}
