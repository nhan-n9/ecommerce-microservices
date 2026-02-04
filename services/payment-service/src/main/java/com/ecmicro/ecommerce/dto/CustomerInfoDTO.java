package com.ecmicro.ecommerce.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInfoDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
