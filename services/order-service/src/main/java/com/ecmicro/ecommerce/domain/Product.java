package com.ecmicro.ecommerce.domain;

import com.ecmicro.ecommerce.dto.CategoryInfoDTO;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double quantity;

    private CategoryInfoDTO categoryInfo;
}
