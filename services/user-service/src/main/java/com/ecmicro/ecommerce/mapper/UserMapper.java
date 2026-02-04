package com.ecmicro.ecommerce.mapper;

import com.ecmicro.ecommerce.domain.Address;
import com.ecmicro.ecommerce.domain.User;
import com.ecmicro.ecommerce.dto.request.UserCreateDTO;
import com.ecmicro.ecommerce.dto.response.UserInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserCreateDTO dto) {
        return User.builder()
                .id(null)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .address(Address.builder()
                        .street(dto.getStreet())
                        .houseNumber(dto.getHouseNumber())
                        .zipCode(dto.getZipCode())
                        .build())
                .build();
    }

    public UserInfoDTO toUserInfo(User user) {
        return UserInfoDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .address(
                    Address.builder()
                        .id(user.getAddress().getId())
                        .street(user.getAddress().getStreet())
                        .houseNumber(user.getAddress().getHouseNumber())
                        .zipCode(user.getAddress().getZipCode())
                        .build()
                )
                .build()

        ;
    }
}
