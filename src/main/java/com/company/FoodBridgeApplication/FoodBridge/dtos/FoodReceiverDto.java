package com.company.FoodBridgeApplication.FoodBridge.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodReceiverDto {

    private Long id;
    private String receiverName;
    private String contact;
}