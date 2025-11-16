package com.ecommerce.order_service.dto.response;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {
    private Long id;
    private Long userId;
    private Date orderDate;
    private String status;
    private Float totalPrice;
}
