package com.example.restaurant.repository;

import com.example.restaurant.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findIngredientById(Long id);

    void deleteById(Long id);
}
