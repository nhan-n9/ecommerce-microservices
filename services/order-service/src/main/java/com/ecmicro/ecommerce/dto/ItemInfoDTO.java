package com.ecmicro.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoDTO {
    private Long id;

    private Long orderId;

    private Long prodId;

    private Double quantity;
}
