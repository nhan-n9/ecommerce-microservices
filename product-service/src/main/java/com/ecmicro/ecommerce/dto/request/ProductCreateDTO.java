package com.ecmicro.ecommerce.dto;

import com.ecmicro.ecommerce.domain.Category;
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
