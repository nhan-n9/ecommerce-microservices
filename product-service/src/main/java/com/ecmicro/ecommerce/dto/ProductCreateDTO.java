package com.ecmicro.ecommerce.dto;

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
