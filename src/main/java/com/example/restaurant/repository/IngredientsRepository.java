package com.example.restaurant.repository;

import com.example.restaurant.entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredients,Long> {
    Ingredients findIngredientById(Long id);
}
