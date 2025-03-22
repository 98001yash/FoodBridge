package com.company.FoodBridgeApplication.FoodBridge.dtos;

import com.company.FoodBridgeApplication.FoodBridge.enums.DonationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDonationDto {

    private Long id;
    private Long donorId;  // Only send donorId, not full User object
    private String foodType;
    private String pickupLocation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    private DonationStatus status; // Status of the donation
}
