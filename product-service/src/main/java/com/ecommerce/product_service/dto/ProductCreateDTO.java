package com.ecommerce.product_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    private String name;
    private Float price;
    private Long sellerId;
    private String category;
}
