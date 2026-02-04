package com.ecmicro.ecommerce.repository;

import com.ecmicro.ecommerce.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<OrderItem, Long> {
}
