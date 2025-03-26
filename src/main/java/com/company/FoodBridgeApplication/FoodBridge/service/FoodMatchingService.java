package com.company.FoodBridgeApplication.FoodBridge.service;


import com.company.FoodBridgeApplication.FoodBridge.entities.FoodDonation;
import com.company.FoodBridgeApplication.FoodBridge.entities.FoodReceiver;
import com.company.FoodBridgeApplication.FoodBridge.enums.DonationStatus;
import com.company.FoodBridgeApplication.FoodBridge.repository.FoodDonationRepository;
import com.company.FoodBridgeApplication.FoodBridge.repository.FoodReceiverRepository;
import com.company.FoodBridgeApplication.FoodBridge.strategies.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodMatchingService {


    private final FoodDonationRepository foodDonationRepository;
    private final FoodReceiverRepository foodReceiverRepository;

    private static final double MAX_DISTANCE_KM = 10.0; // max search radius in km
    public List<FoodDonation> findMatchingDonations(Long receiverId, String requiredFoodType){
        log.info("Finding matching food donations for receiver ID: {}",receiverId);

        FoodReceiver receiver = foodReceiverRepository.findById(receiverId)
                .orElseThrow(()->new RuntimeException("Food Receiver not found"));

        List<FoodDonation> allDonations = foodDonationRepository.findAll();

        return allDonations.stream()
                .filter(donation -> donation.getStatus() == DonationStatus.AVAILABLE)
                .filter(donation -> donation.getExpiryDate().isAfter(LocalDate.now()))
                .filter(donation -> donation.getFoodType().equalsIgnoreCase(requiredFoodType))
                .filter(donation -> DistanceCalculator.calculateDistance(
                        receiver.getLatitude(), receiver.getLongitude(),
                        donation.getLatitude(), donation.getLongitude()) <= MAX_DISTANCE_KM)
                .sorted((d1, d2) -> {
                    double dist1 = DistanceCalculator.calculateDistance(receiver.getLatitude(), receiver.getLongitude(), d1.getLatitude(), d1.getLongitude());
                    double dist2 = DistanceCalculator.calculateDistance(receiver.getLatitude(), receiver.getLongitude(), d2.getLatitude(), d2.getLongitude());
                    return Double.compare(dist1, dist2); // Sort by closest first
                })
                .collect(Collectors.toList());
    }
}
