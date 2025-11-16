package com.ecommerce.order_service.mapper;

import com.ecommerce.order_service.domain.Order;
import com.ecommerce.order_service.dto.response.OrderInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderInfoDTO getOrderInfoDTO(Order ord) {
        return new OrderInfoDTO(
                ord.getId(),
                ord.getUserId(),
                ord.getOrderDate(),
                ord.getStatus(),
                ord.getTotalPrice()
        );
    }
}
