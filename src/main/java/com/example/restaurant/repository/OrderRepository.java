package com.example.restaurant.repository;

import com.example.restaurant.entity.DishType;
import com.example.restaurant.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findOrderById(Long id);
}
