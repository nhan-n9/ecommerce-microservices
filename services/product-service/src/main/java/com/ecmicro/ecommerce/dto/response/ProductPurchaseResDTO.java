package com.ecmicro.ecommerce.dto.response;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseResDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Double purchaseQuantity;
}
