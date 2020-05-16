package com.example.restaurant.repository;

import com.example.restaurant.entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillsRepository extends JpaRepository<Bills,Long> {
}
