package com.example.restaurant.service;

import com.example.restaurant.entity.User;
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

    public void deleteUserById(Long userId) {
        final User user = userService.findUser(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: id = " + userId));
        orderService.deleteByUser(user);
        userService.delete(userId);
    }


}
