package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.domain.OrderItem;
import com.ecmicro.ecommerce.dto.request.ItemRequestDTO;
import com.ecmicro.ecommerce.mapper.ItemMapper;
import com.ecmicro.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper mapper;

    public Long createProductItem(ItemRequestDTO itemRequest) {
        OrderItem item = mapper.toOrderItem(itemRequest.getId(), itemRequest.getOrderId(), itemRequest.getItem());

        return itemRepository.save(item).getId();
    }
}
