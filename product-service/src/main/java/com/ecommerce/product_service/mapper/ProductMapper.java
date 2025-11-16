package com.ecommerce.product_service.mapper;

import com.ecommerce.product_service.domain.Product;
import com.ecommerce.product_service.dto.ProductCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product getProductFromProductCreate(ProductCreateDTO dto) {
        return new Product(null, dto.getName(), dto.getPrice(), dto.getSellerId(), dto.getCategory());
    }
}
