package com.example.restaurant.dto;

import java.util.Set;

public class DishDetailDto {
    private Long id;

    private int price;

    private String name;

    private int weight;
    private Set<IngredientDto> ingredient;
    private DishTypeDto dishType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
