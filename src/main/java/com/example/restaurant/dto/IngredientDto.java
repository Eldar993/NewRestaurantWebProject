package com.example.restaurant.dto;

import com.example.restaurant.entity.Ingredient;

public class IngredientDto {
    private Long id;
    private String title;
    private int calories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    static Ingredient toDto() {

    }
}
