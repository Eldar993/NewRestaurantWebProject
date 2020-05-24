package com.example.restaurant.service;

import com.example.restaurant.dto.DishDetailDto;
import com.example.restaurant.dto.DishDto;
import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.Ingredient;
import com.example.restaurant.repository.DishRepository;
import com.example.restaurant.repository.DishTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class DishService {
    private final DishRepository dishRepository;
    private final DishTypeRepository dishTypeRepository;
    private final IngredientsService ingredientsService;

    public DishService(DishRepository dishRepository,
                       DishTypeRepository dishTypeRepository,
                       IngredientsService ingredientsService) {
        this.dishRepository = dishRepository;
        this.dishTypeRepository = dishTypeRepository;
        this.ingredientsService = ingredientsService;
    }

    public Dish addIngredient(Long dishId, Long ingredientId) {
        final Optional<Dish> dish = dishRepository.findById(dishId);
        if (dish.isEmpty()) {
//            return "Dish with id = " + dishId + "not found";
            return null;
        }
        final Ingredient ingredient = ingredientsService.findById(ingredientId);
        dish.get().addIngredient(ingredient);

        return dishRepository.saveAndFlush(dish.get());
    }

    public Dish removeIngredient(Long dishId, Long ingredientId) {
        final Optional<Dish> optionalDish = dishRepository.findById(dishId);
        if (optionalDish.isEmpty()) {
//            return "Dish with id = " + dishId + "not found";
            return null;
        }
        final Ingredient ingredient = ingredientsService.findById(ingredientId);
        final Dish dish = optionalDish.get();
        dish.removeIngredient(ingredient);

        return dishRepository.saveAndFlush(dish);
    }

    //TODO: add implementation
    public static DishDto toDto(Dish dish) {
        return null;
    }

    //TODO: add implementation
    public static DishDetailDto toDetailDto(Dish dish) {
        return null;
    }
}
