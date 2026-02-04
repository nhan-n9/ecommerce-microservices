package com.ecmicro.ecommerce.repository;

import com.ecmicro.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.sellerId = ?1")
    List<Product> findAllBySellerId(Long sellerId);
}
