package com.ecmicro.ecommerce.mapper;

import com.ecmicro.ecommerce.domain.Order;
import com.ecmicro.ecommerce.domain.OrderItem;
import com.ecmicro.ecommerce.domain.Product;
import com.ecmicro.ecommerce.dto.ItemInfoDTO;
import com.ecmicro.ecommerce.dto.ItemProductDetailDTO;
import com.ecmicro.ecommerce.dto.request.ItemCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemMapper {
    public OrderItem toOrderItem(Long id, Long orderId, ItemCreateDTO itemDTO) {
        return OrderItem.builder()
                .id(id)
                .order(
                    Order.builder()
                        .id(orderId)
                        .build()
                )
                .prodId(itemDTO.getId())
                .quantity(itemDTO.getQuantity())
                .build();
    }

    public ItemInfoDTO toItemInfoDTO(OrderItem item) {
        return new ItemInfoDTO(
                item.getId(),
                item.getOrder().getId(),
                item.getProdId(),
                item.getQuantity()
        );
    }
}
