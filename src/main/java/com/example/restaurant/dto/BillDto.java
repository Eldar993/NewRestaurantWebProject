package com.example.restaurant.dto;

import com.example.restaurant.entity.Order;

import java.time.LocalDateTime;

public class BillDto {
    private Long id;
    private Order order;
    private Float paid_sum;
    private LocalDateTime completedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Float getPaid_sum() {
        return paid_sum;
    }

    public void setPaid_sum(Float paid_sum) {
        this.paid_sum = paid_sum;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
