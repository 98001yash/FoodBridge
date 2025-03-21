package com.company.FoodBridgeApplication.FoodBridge.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequestDto {

    private Long id;
    private Long foodDonationId;
    private Long receiverId;
    private String status;  // PENDING, APPROVED, REJECTED
}
