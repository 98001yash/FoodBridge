package com.company.FoodBridgeApplication.FoodBridge.controller;

import com.company.FoodBridgeApplication.FoodBridge.entities.FoodDonation;
import com.company.FoodBridgeApplication.FoodBridge.service.FoodMatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-food")
@RequiredArgsConstructor
public class FoodMatchingController {

    private final FoodMatchingService foodMatchingService;

    @GetMapping("/{receiverId}")
    public ResponseEntity<List<FoodDonation>> getMatchingDonations(
            @PathVariable Long receiverId,
            @RequestParam String foodType
    ){
        List<FoodDonation> matchingDonations = foodMatchingService.findMatchingDonations(receiverId, foodType);
        return ResponseEntity.ok(matchingDonations);
    }
}
