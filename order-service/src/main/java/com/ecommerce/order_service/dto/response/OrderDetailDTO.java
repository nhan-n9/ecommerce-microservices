package com.ecommerce.order_service.dto.response;

import com.ecommerce.order_service.dto.ItemProductDetailDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Long userId;
    private Date orderDate;
    private String status;
    private Float totalPrice;
    private List<ItemProductDetailDTO> items;
}
