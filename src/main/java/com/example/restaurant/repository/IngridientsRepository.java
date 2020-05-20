package com.example.restaurant.repository;

import com.example.restaurant.entity.Ingridients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngridientsRepository extends JpaRepository<Ingridients,Long> {
    Ingridients findIngridientsById(Long id);
}
