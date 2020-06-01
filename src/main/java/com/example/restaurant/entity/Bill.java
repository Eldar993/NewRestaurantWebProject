package com.example.restaurant.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Order order;

    private Float paidSum;

    @Column
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

    public Float getPaidSum() {
        return paidSum;
    }

    public void setPaidSum(Float paidSum) {
        this.paidSum = paidSum;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime complete_time) {
        this.completedAt = complete_time;
    }
}
