package com.example.restaurant.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "order_dish")
public class OrderDish {

    @EmbeddedId
    private OrderDishId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    private Order order;

    private Long count = 0L;

    public OrderDish() {

    }

    public OrderDish(Order order, Dish dish) {
        this.order = order;
        this.dish = dish;
        this.id = new OrderDishId(order.getId(), dish.getId());
    }

    public OrderDishId getId() {
        return id;
    }

    public void setId(OrderDishId id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addCount(Long count) {
        this.count += count;
        if (count < 0) {
            throw new IllegalArgumentException("Wrong count");
        }
    }
}
