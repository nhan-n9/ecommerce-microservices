package com.ecmicro.ecommerce.mapper;

import com.ecmicro.ecommerce.domain.Category;
import com.ecmicro.ecommerce.domain.Product;
import com.ecmicro.ecommerce.dto.request.ProductCreateDTO;
import com.ecmicro.ecommerce.dto.request.ProductPurchaseDTO;
import com.ecmicro.ecommerce.dto.response.CategoryInfoDTO;
import com.ecmicro.ecommerce.dto.response.ProductDetailDTO;
import com.ecmicro.ecommerce.dto.response.ProductPurchaseResDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductCreateDTO dto) {
        return Product.builder()
                .id(null)
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .sellerId(dto.getSellerId())
                .quantity(dto.getQuantity())
                .category(
                        Category.builder()
                                .id(dto.getCategoryId())
                                .build()
                        )
                .build();
    }

    public CategoryInfoDTO toCategoryInfo(Category dto) {
        return new CategoryInfoDTO(
                dto.getId(),
                dto.getName(),
                dto.getDescription()
        );
    }

    public ProductDetailDTO toProductDetail(Product dto) {
        return ProductDetailDTO.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .categoryInfo(
                        CategoryInfoDTO.builder()
                                .id(dto.getCategory().getId())
                                .name(dto.getCategory().getName())
                                .description(dto.getCategory().getDescription())
                                .build()
                )
                .build();
    }

    public ProductPurchaseResDTO toProductPurchaseRes(Product prod, ProductPurchaseDTO reqProd) {
        return new ProductPurchaseResDTO(
                prod.getId(),
                prod.getName(),
                prod.getPrice(),
                reqProd.getQuantity()
        );
    }
}
