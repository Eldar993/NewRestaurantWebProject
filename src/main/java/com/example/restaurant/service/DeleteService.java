package com.example.restaurant.service;

import com.example.restaurant.entity.DishType;
import com.example.restaurant.entity.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteService {
    private final UserService userService;
    private final OrderService orderService;
    private final DishTypeService dishTypeService;
    private final DishService dishService;

    public DeleteService(UserService userService,
                         OrderService orderService,
                         DishTypeService dishTypeService,
                         DishService dishService) {
        this.userService = userService;
        this.orderService = orderService;
        this.dishTypeService = dishTypeService;
        this.dishService = dishService;
    }

    public void deleteUserById(Long userId) {
        final User user = userService.findUser(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: id = " + userId));
        orderService.deleteByUser(user);
        userService.delete(userId);
    }

    public void deleteDishTypeById(Long dishTypeId) {
        final DishType dishType = dishTypeService.findById(dishTypeId);
        dishService.findAllByDishType(dishType)
                .forEach(d -> {
                    orderService.deleteDishFromOrder(d);
                    dishService.deleteById(d.getId());
                });

        dishTypeService.deleteById(dishTypeId);
    }

}
