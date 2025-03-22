package com.company.FoodBridgeApplication.FoodBridge.controller;


import com.company.FoodBridgeApplication.FoodBridge.dtos.FoodDonationDto;
import com.company.FoodBridgeApplication.FoodBridge.service.FoodDonationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-donations")
@RequiredArgsConstructor
@Slf4j
public class FoodDonationController {

    private final FoodDonationService foodDonationService;

    @PostMapping
    public ResponseEntity<FoodDonationDto> addFoodDonation(@RequestBody FoodDonationDto foodDonationDto){
        log.info("Received request to add a food donation: {}",foodDonationDto);
        FoodDonationDto createdFoodDonation = foodDonationService.addDonation(foodDonationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFoodDonation);
    }


    @GetMapping
    public ResponseEntity<List<FoodDonationDto>> getAllFoodDonations(){
        log.info("Received request to fetch all food donations");
        List<FoodDonationDto> donations = foodDonationService.getAllDonations();
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDonationDto> getFoodDonationById(@PathVariable Long id){
        log.info("Received request to fetch food donation with ID: {}",id);
        FoodDonationDto donation = foodDonationService.getFoodDonationById(id);
        return ResponseEntity.ok(donation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodDonationDto> updateFoodDonation(@PathVariable Long id,  @RequestBody FoodDonationDto foodDonationDto) {
        log.info("Received request to update food donation with ID: {}", id);
        FoodDonationDto updatedDonation = foodDonationService.updateFoodDonation(id, foodDonationDto);
        return ResponseEntity.ok(updatedDonation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodDonation(@PathVariable Long id){
       log.info("Received request to delete food donation with ID: {}",id);

       foodDonationService.deleteDonation(id);
       return ResponseEntity.noContent().build();
    }

}
