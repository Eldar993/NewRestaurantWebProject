package com.example.restaurant.repository;

import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.User;
import com.example.restaurant.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);

    Optional<Order> findFirstByUserAndStatus(User user, OrderStatus status);

    Long countAllByUserAndStatusNot(User user, OrderStatus status);

    List<Order> findAllByUser(User user);

    List<Order> findByUserAndStatus(User user, OrderStatus status);

    List<Order> findByStatus(OrderStatus status);

    void deleteByUser(User user);
}
