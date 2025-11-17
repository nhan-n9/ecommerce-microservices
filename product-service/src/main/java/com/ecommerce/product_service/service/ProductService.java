package com.ecommerce.product_service.service;

import com.ecommerce.product_service.domain.Product;
import com.ecommerce.product_service.dto.ProductCreateDTO;
import com.ecommerce.product_service.mapper.ProductMapper;
import com.ecommerce.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final List<String> ALLOWED_SORT_FIELDS = Arrays.asList("name", "price");

    public Page<Product> findAllByPagination(int page, int size, String sortBy, boolean ascending) {
        // validate the passed in orderBy value
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findBySellerId(Long sellerId) {
        // can additionally check whether the seller with that id exists

        return productRepository.findAllBySellerId(sellerId);
    }

    public List<Product> findByProdIds(List<Long> prodIds) {
        List<Product> products = new ArrayList<>();

        for (Long id : prodIds) {
            Optional<Product> prod = productRepository.findById(id);
            if (prod.isPresent()) products.add(prod.get());     // (==) prod.ifPresent(products::add);
        }

        return products;
    }

    public Long createNewProduct(ProductCreateDTO prod) {
        return productRepository.save(mapper.getProductFromProductCreate(prod)).getId();
    }
}
