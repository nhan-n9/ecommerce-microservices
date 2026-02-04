package com.ecmicro.ecommerce.dto.response;

import com.ecmicro.ecommerce.domain.Address;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Address address;
}
