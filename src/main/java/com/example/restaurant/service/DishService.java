package com.example.restaurant.service;

import com.example.restaurant.dto.DishDetailDto;
import com.example.restaurant.dto.DishDto;
import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishType;
import com.example.restaurant.entity.Ingredient;
import com.example.restaurant.repository.DishRepository;
import com.example.restaurant.repository.DishTypeRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class DishService {
    private final DishRepository dishRepository;
    private final DishTypeRepository dishTypeRepository;
    private final IngredientService ingredientService;

    public DishService(DishRepository dishRepository,
                       DishTypeRepository dishTypeRepository,
                       IngredientService ingredientService) {
        this.dishRepository = dishRepository;
        this.dishTypeRepository = dishTypeRepository;
        this.ingredientService = ingredientService;
    }

    public Dish addIngredient(Long dishId, Long ingredientId) {
        final Optional<Dish> dish = dishRepository.findById(dishId);
        if (dish.isEmpty()) {
//            return "Dish with id = " + dishId + "not found";
            return null;
        }
        final Ingredient ingredient = ingredientService.findById(ingredientId);
        dish.get().addIngredient(ingredient);

        return dishRepository.saveAndFlush(dish.get());
    }

    public Dish removeIngredient(Long dishId, Long ingredientId) {
        final Optional<Dish> optionalDish = dishRepository.findById(dishId);
        if (optionalDish.isEmpty()) {
//            return "Dish with id = " + dishId + "not found";
            return null;
        }
        final Ingredient ingredient = ingredientService.findById(ingredientId);
        final Dish dish = optionalDish.get();
        dish.removeIngredient(ingredient);

        return dishRepository.saveAndFlush(dish);
    }

    //TODO: add implementation
    public static DishDto toDto(Dish dish) {
        if (dish == null) {
            return null;
        }
        DishDto result = new DishDto();
        result.setId(dish.getId());
        result.setName(dish.getName());
        result.setDishTypeId(dish.getDishType().getId());
        result.setPrice(dish.getPrice());
        result.setWeight(dish.getWeight());

        Set<Long> ingredientIds = new HashSet<>();
        for (Ingredient ingredient : dish.getIngredients()) {
            ingredientIds.add(ingredient.getId());
        }
        result.setIngredientIds(ingredientIds);

        return result;
    }

    public static List<DishDto> toDto(List<Dish> dishes) {
        return dishes
                .stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }


    //TODO: add implementation
    public static DishDetailDto toDetailDto(Dish dish) {
        if (dish == null) {
            return null;
        }
        DishDetailDto result = new DishDetailDto();
        result.setDish(toDto(dish));
        result.setDishType(DishTypeService.toDto(dish.getDishType()));
        result.setIngredient(IngredientService.toDto(dish.getIngredients()));
        return result;
    }

    public Dish toEntity(DishDto dto) {
        if (dto == null) {
            return null;
        }
        Dish result = new Dish();
        result.setId(dto.getId());
        DishType dishType = dishTypeRepository.findById(dto.getDishTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Unknown dish type id = "));
        result.setDishType(dishType);
        result.setName(dto.getName());
        result.setPrice(dto.getPrice());
        result.setWeight(dto.getWeight());
        return result;
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public boolean create(Dish dish) {
        if (dish.getId() != null) {
            return false;
        }
        dishRepository.saveAndFlush(dish);
        return true;
    }
    public Dish findById(Long id) {
        Dish dish = dishRepository.findDishById(id);
        return dish;
    }

    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }

    public Dish update(Dish dish) {
        Dish updatedDish = dishRepository.findDishById(dish.getId());
        if (updatedDish == null) {
            return null;
        }
        updatedDish.setDishType(dish.getDishType());
        updatedDish.setName(dish.getName());
        updatedDish.setWeight(dish.getWeight());
        updatedDish.setPrice(dish.getPrice());



        final Dish result = dishRepository.saveAndFlush(updatedDish);
        return result;
    }


}
