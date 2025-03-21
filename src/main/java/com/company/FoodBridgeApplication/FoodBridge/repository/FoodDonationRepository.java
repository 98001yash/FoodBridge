package com.company.FoodBridgeApplication.FoodBridge.repository;

import com.company.FoodBridgeApplication.FoodBridge.entities.FoodDonation;
import com.company.FoodBridgeApplication.FoodBridge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodDonationRepository extends JpaRepository<FoodDonation,Long> {
    List<FoodDonation> findByDonor(User donor);
}
