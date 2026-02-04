package com.ecmicro.ecommerce.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;

    private Long customerId;

    private LocalDateTime createdDate;

    private String status;

    private BigDecimal totalPrice;

    private List<ItemInfoDTO> items;
}
