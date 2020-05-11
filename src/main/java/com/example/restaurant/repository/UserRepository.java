package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restaurant.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
  List<User> findAllById();// ????
}
