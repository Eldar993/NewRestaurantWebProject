package com.example.restaurant.repository;

import com.example.restaurant.entity.Bill;
import com.example.restaurant.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
    Bill findBillById(Long id);
}
