package com.example.restaurant.dto;

import com.example.restaurant.entity.User;
import com.example.restaurant.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderDto {
    private Long id;
    private LocalDateTime createdAt;
    private User user;
    private OrderStatus orderStatus;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
