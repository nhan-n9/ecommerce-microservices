package com.ecmicro.ecommerce.mapper;

import com.ecmicro.ecommerce.domain.Order;
import com.ecmicro.ecommerce.domain.OrderItem;
import com.ecmicro.ecommerce.domain.Product;
import com.ecmicro.ecommerce.dto.ItemProductDetailDTO;
import com.ecmicro.ecommerce.dto.response.OrderDetailDTO;
import com.ecmicro.ecommerce.dto.response.OrderInfoDTO;
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
