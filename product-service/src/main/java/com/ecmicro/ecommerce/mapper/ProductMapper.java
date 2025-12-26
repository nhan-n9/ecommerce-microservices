package com.ecmicro.ecommerce.mapper;

import com.ecmicro.ecommerce.domain.Product;
import com.ecmicro.ecommerce.dto.ProductCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product getProductFromProductCreate(ProductCreateDTO dto) {
        return new Product(null, dto.getName(), dto.getPrice(), dto.getSellerId(), dto.getCategory());
    }
}
