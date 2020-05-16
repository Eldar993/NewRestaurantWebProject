package com.example.restaurant.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Bills")
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long order_id;

    private Float paid_sum;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date complete_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Float getPaid_sum() {
        return paid_sum;
    }

    public void setPaid_sum(Float paid_sum) {
        this.paid_sum = paid_sum;
    }

    public Date getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(Date complete_time) {
        this.complete_time = complete_time;
    }
}
