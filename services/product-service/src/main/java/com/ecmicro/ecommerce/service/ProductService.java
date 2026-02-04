package com.ecmicro.ecommerce.service;

import com.ecmicro.ecommerce.domain.Product;
import com.ecmicro.ecommerce.dto.request.ProductCreateDTO;
import com.ecmicro.ecommerce.dto.request.ProductPurchaseDTO;
import com.ecmicro.ecommerce.dto.response.ProductDetailDTO;
import com.ecmicro.ecommerce.dto.response.ProductPurchaseResDTO;
import com.ecmicro.ecommerce.exception.ProductPurchaseException;
import com.ecmicro.ecommerce.mapper.ProductMapper;
import com.ecmicro.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final List<String> ALLOWED_SORT_FIELDS = Arrays.asList("name", "price");

    public Page<ProductDetailDTO> findAllByPagination(int page, int size, String sortBy, boolean ascending) {
        // validate the passed in orderBy value
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        List<ProductDetailDTO> detailDTOs = productRepository
                                                .findAll(pageable)    // must pass "pageable" -> to correctly apply pagination
                                                .stream()   // convert to stream - a sequence of elements supporting sequential operations
                                                .map(mapper::toProductDetail)
                                                .toList();

        // convert to Page<ProductDetailDTO> with params: content, Pageable object, total elements
        return new PageImpl<>(detailDTOs, pageable, detailDTOs.size());
    }

    public ProductDetailDTO findById(Long id) {
        return mapper.toProductDetail(productRepository.findById(id).get());
    }

    public List<ProductDetailDTO> findBySellerId(Long sellerId) {
        // can additionally check whether the seller with that id exists

        return productRepository.findAllBySellerId(sellerId).stream().map(mapper::toProductDetail).toList();
    }

    public List<ProductDetailDTO> findByProdIds(List<Long> prodIds) {
        List<ProductDetailDTO> products = new ArrayList<>();

        for (Long id : prodIds) {
            Optional<Product> prod = productRepository.findById(id);
            // (==) prod.ifPresent(products::add);
            prod.ifPresent(product -> products.add(mapper.toProductDetail(product)));
        }
        return products;
    }

    public Long createNewProduct(ProductCreateDTO prod) {
        return productRepository.save(mapper.toProduct(prod)).getId();
    }

    public List<ProductPurchaseResDTO> purchaseProducts(List<ProductPurchaseDTO> requestedProds) {
        // extract ids from requested products & sort them
        for (ProductPurchaseDTO dto : requestedProds) {
            if (dto.getId() == null) {
                throw new ProductPurchaseException("ProductPurchaseDTO contains null id");
            }
        }
        List<Long> ids = requestedProds.stream().sorted(Comparator.comparing(ProductPurchaseDTO::getId)).map(ProductPurchaseDTO::getId).toList();
        List<Product> reqProds = productRepository.findAllById(ids);
        List<ProductPurchaseResDTO> resProds = new ArrayList<>();

        if (reqProds.size() != requestedProds.size()) {
            throw new ProductPurchaseException("Some products was not found!");
        }
        // sort requested products by id to align with fetched products
        var sortedReqProds = requestedProds.stream().sorted(Comparator.comparing(ProductPurchaseDTO::getId)).toList();

        // loop through each corresponding prod & requested prod
        for (int i=0; i < ids.size(); i++) {    // loop & process with corresponding indices
            var prod = reqProds.get(i);
            var reqProd = sortedReqProds.get(i);

            if (prod.getQuantity() < reqProd.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID: " + reqProd.getId());
            }

            // if enough quantity -> subtract and save
            prod.setQuantity(prod.getQuantity() - reqProd.getQuantity());
            productRepository.save(prod);
            resProds.add(mapper.toProductPurchaseRes(prod, reqProd));
        }

        return resProds;
    }
}
