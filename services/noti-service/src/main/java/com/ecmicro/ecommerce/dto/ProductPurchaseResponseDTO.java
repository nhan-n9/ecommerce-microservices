package com.ecmicro.ecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductPurchaseResponseDTO {
    private Long id;

    private String name;

    private BigDecimal price;

    private Double purchaseQuantity;
}
