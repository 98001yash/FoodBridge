package com.company.FoodBridgeApplication.FoodBridge.repository;

import com.company.FoodBridgeApplication.FoodBridge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
