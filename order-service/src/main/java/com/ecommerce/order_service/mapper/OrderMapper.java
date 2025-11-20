package com.ecommerce.order_service.mapper;

import com.ecommerce.order_service.domain.Order;
import com.ecommerce.order_service.domain.OrderItem;
import com.ecommerce.order_service.domain.Product;
import com.ecommerce.order_service.dto.ItemProductDetailDTO;
import com.ecommerce.order_service.dto.response.OrderDetailDTO;
import com.ecommerce.order_service.dto.response.OrderInfoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ItemProductDetailDTO getItemProductDetailDTO(OrderItem item, Product prod) {
        return new ItemProductDetailDTO(
                item.getId(),
                item.getOrderId(),
                item.getQuantity(),
                prod
        );
    }

    public OrderDetailDTO getOrderDetailDTO(Order ord, List<ItemProductDetailDTO> items) {
        return new OrderDetailDTO(
                ord.getId(),
                ord.getUserId(),
                ord.getOrderDate(),
                ord.getStatus(),
                ord.getTotalPrice(),
                items
        );
    }
}
