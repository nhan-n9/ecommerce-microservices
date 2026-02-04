package com.ecmicro.ecommerce.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseDTO {
    private Long id;
    private Double quantity;
}
