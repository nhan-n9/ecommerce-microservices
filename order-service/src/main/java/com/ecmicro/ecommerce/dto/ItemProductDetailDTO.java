package com.ecmicro.ecommerce.dto;

import com.ecmicro.ecommerce.domain.Product;
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
