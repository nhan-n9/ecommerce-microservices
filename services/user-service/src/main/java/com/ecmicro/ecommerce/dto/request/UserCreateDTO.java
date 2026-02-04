package com.ecmicro.ecommerce.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String street;
    private String houseNumber;
    private String zipCode;
}
