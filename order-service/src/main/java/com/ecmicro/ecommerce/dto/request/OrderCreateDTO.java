package com.ecmicro.ecommerce.dto.request;

import com.ecmicro.ecommerce.domain.OrderItem;
import lombok.*;
import java.util.Date;
import java.util.List;

@Data
public class OrderCreateDTO {
    private Long userId;
    private Date orderDate;
    private String status;
    private Float totalPrice;
    private List<OrderItem> items;
}
