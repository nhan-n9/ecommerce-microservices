package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.sellerId = ?1")
    List<Product> findAllBySellerId(Long sellerId);
}
