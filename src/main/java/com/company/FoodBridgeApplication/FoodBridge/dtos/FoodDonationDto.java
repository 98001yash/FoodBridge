package com.company.FoodBridgeApplication.FoodBridge.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDonationDto {

    private Long id;
    private String foodName;
    private String description;
    private Integer quantity;
    private LocalDateTime expiryDate;
    private Long donorId;   // we'll only send donor id instead of the Full User object
}
