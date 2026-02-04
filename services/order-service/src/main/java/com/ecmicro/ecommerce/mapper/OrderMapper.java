package com.ecmicro.ecommerce.mapper;

import com.ecmicro.ecommerce.domain.Order;
import com.ecmicro.ecommerce.domain.OrderItem;
import com.ecmicro.ecommerce.domain.Product;
import com.ecmicro.ecommerce.dto.ItemProductDetailDTO;
import com.ecmicro.ecommerce.dto.OrderDTO;
import com.ecmicro.ecommerce.dto.request.OrderCreateDTO;
import com.ecmicro.ecommerce.dto.response.OrderDetailDTO;
import com.ecmicro.ecommerce.dto.response.OrderInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ItemMapper itemMapper;

    public Order toOrder(OrderCreateDTO dto) {
        return Order.builder()
                .id(null)
                .customerId(dto.getCustomerId())
                .createdDate(dto.getCreatedDate())
                .paymentMethod(dto.getPaymentMethod())
                .status(dto.getStatus())
                .totalPrice(dto.getTotalPrice())
                .build();
    }

    public OrderInfoDTO toOrderInfoDTO(Order ord) {
        return new OrderInfoDTO(
                ord.getId(),
                ord.getCustomerId(),
                ord.getCreatedDate(),
                ord.getStatus(),
                ord.getTotalPrice()
        );
    }

    public OrderDetailDTO toOrderDetailDTO(Order ord, List<ItemProductDetailDTO> items) {
        return new OrderDetailDTO(
                ord.getId(),
                ord.getCustomerId(),
                ord.getCreatedDate(),
                ord.getStatus(),
                ord.getTotalPrice(),
                items
        );
    }

    public OrderDTO toOrderDTO(Order ord) {
        return new OrderDTO(
                ord.getId(),
                ord.getCustomerId(),
                ord.getCreatedDate(),
                ord.getStatus(),
                ord.getTotalPrice(),
                // convert List<OrderItem> to List<ItemInfoDTO>
                ord.getItems().stream()
                        .map(itemMapper::toItemInfoDTO)
                        .collect(Collectors.toList())
        );
    }

    public ItemProductDetailDTO toItemProductDetailDTO(OrderItem item, Product prod) {
        return new ItemProductDetailDTO(
                item.getId(),
                item.getOrder().getId(),
                item.getQuantity(),
                prod
        );
    }
}
