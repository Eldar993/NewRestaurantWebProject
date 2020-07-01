package com.example.restaurant.service;

import com.example.restaurant.entity.DishType;
import com.example.restaurant.entity.Ingredient;
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
    private final IngredientService ingredientService;

    public DeleteService(UserService userService,
                         OrderService orderService,
                         DishTypeService dishTypeService,
                         DishService dishService, IngredientService ingredientService) {
        this.userService = userService;
        this.orderService = orderService;
        this.dishTypeService = dishTypeService;
        this.dishService = dishService;
        this.ingredientService = ingredientService;
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

    public void deleteIngredientById(Long ingredientId) {
        final Ingredient ingredient = ingredientService.findById(ingredientId);
        dishService.findAllByIngredient(ingredient)
                .forEach(d -> {
                    dishService.deleteById(d.getId());
               });
        ingredientService.deleteById(ingredientId);
    }

}
