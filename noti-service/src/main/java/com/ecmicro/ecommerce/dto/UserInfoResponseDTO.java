package com.ecmicro.ecommerce.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
