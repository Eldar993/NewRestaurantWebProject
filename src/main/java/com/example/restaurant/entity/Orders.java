package com.example.restaurant.entity;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    private Long id;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date order_time;

    private Long user_id;


}
