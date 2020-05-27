package com.example.restaurant.entity;

import com.example.restaurant.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    @Column
    private LocalDateTime createdAt;


    private Long user_id;

    @Enumerated(EnumType.ORDINAL)
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

    public void setCreatedAt(LocalDateTime order_time) {
        this.createdAt = order_time;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                createdAt.equals(order.createdAt) &&
                user_id.equals(order.user_id) &&
                orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, user_id, orderStatus);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_time=" + createdAt +
                ", user_id=" + user_id +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
