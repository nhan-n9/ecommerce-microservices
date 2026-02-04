package com.ecmicro.ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {
    private Long id;

    private Long orderId;

    private ItemCreateDTO item;
}
