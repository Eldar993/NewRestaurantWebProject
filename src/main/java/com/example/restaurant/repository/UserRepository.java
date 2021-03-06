package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restaurant.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserById(Long id);
    Optional<User> findByName(String name);
}
