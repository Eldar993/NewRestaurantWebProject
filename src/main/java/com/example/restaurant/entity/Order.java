package com.example.restaurant.entity;

import com.example.restaurant.enums.OrderStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    @Column
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToMany
    List<Dish> dishes;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                createdAt.equals(order.createdAt) &&
                Objects.equals(user, order.user) &&
                orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, user, orderStatus);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_time=" + createdAt +
                ", user=" + user +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
