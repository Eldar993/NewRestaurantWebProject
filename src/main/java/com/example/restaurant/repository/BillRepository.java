package com.example.restaurant.repository;

import com.example.restaurant.entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bills,Long> {
}
