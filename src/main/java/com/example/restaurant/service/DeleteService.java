package com.example.restaurant.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteService {
    private final UserService userService;
    private final OrderService orderService;

    public DeleteService(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }


}
