package com.ecmicro.ecommerce.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInfoDTO {
    private Long id;
    private String name;
    private String description;
}
