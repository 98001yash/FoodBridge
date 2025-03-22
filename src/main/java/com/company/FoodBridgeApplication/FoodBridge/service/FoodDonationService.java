package com.company.FoodBridgeApplication.FoodBridge.service;

import com.company.FoodBridgeApplication.FoodBridge.dtos.FoodDonationDto;

import java.util.List;

public interface FoodDonationService {


    FoodDonationDto addDonation(FoodDonationDto foodDonationDto);
    List<FoodDonationDto> getAllDonations();
    FoodDonationDto  getFoodDonationById(Long id);
    FoodDonationDto updateFoodDonation(Long id, FoodDonationDto foodDonationDto);
    void deleteDonation(Long id);
}
