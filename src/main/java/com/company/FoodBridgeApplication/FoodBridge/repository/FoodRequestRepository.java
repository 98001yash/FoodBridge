package com.company.FoodBridgeApplication.FoodBridge.repository;

import com.company.FoodBridgeApplication.FoodBridge.entities.FoodRequest;
import com.company.FoodBridgeApplication.FoodBridge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRequestRepository extends JpaRepository<FoodRequest,Long> {
    List<FoodRequest> findByReceiver(User receiver);
}
