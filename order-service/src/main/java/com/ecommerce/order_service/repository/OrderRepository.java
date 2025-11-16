package com.ecommerce.order_service.repository;

import com.ecommerce.order_service.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.items WHERE o.id = :ordId")
    Order findOrderWithItems(Long ordId);
}
