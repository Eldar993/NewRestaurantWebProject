package com.example.restaurant.dto;

import java.util.Set;

public class DishDetailDto {
    private DishDto dish;
    private Set<IngredientDto> ingredient;
    private DishTypeDto dishType;

    public DishDto getDish() {
        return dish;
    }

    public void setDish(DishDto dish) {
        this.dish = dish;
    }

    public Set<IngredientDto> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Set<IngredientDto> ingredient) {
        this.ingredient = ingredient;
    }

    public DishTypeDto getDishType() {
        return dishType;
    }

    public void setDishType(DishTypeDto dishType) {
        this.dishType = dishType;
    }
}
