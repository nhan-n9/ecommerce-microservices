package com.ecmicro.ecommerce.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
//  cannot update `id` & `email` -> unique
    private Long id;    // only for validating
    private String firstName;
    private String lastName;
    private String password;
    private String street;
    private String houseNumber;
    private String zipCode;
}
