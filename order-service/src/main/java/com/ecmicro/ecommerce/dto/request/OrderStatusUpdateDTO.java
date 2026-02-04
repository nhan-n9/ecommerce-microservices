package com.ecmicro.ecommerce.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusUpdateDTO {
    private Long id;
    private Long userId;
    private String status;
}
