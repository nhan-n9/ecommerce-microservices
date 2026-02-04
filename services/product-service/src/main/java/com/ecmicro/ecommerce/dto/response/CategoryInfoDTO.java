package com.ecmicro.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInfoDTO {
    private Long id;
    private String name;
    private String description;
}
