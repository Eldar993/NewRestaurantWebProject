package com.example.restaurant.repository;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.DishType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findAllByDishType(DishType dishType);
}
