package com.example.restaurant.repository;

import com.example.restaurant.entity.DishType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishTypeRepository extends JpaRepository<DishType, Long> {
}
