package com.ecmicro.ecommerce.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Double quantity;
    private Long sellerId;
    private Long categoryId;
}
