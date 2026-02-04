package com.ecmicro.ecommerce.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {
    private Long id;

    private Long userId;

    private LocalDateTime orderDate;

    private String status;

    private BigDecimal totalPrice;
}
