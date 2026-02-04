package com.ecmicro.ecommerce.feign;

import com.ecmicro.ecommerce.dto.response.UserInfoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "user-service",
        url = "${application.config.user-url}"    // config in config-sv
)
public interface CustomerInterface {

    @GetMapping("/{id}")
    Optional<UserInfoResponseDTO> findCustomerById(@PathVariable Long id);
}
