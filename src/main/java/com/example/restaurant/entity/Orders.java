package com.example.restaurant.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    private Long id;

  /*  @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date order_time;*/

    @Column
    private LocalDateTime order_time;

    private Long user_id;


}
