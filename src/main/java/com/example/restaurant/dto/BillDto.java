package com.example.restaurant.dto;

import java.time.LocalDateTime;

public class BillDto {
    private Long id;
    private Long order_id;
    private Float paid_sum;
    private LocalDateTime complete_time;

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

    public LocalDateTime getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(LocalDateTime complete_time) {
        this.complete_time = complete_time;
    }
}
