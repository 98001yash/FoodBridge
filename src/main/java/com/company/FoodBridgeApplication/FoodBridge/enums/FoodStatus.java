package com.company.FoodBridgeApplication.FoodBridge.enums;

public enum FoodStatus {

    AVAILABLE,   // ready for donation
    PENDING,    // Awaiting pick up confirmation
    PICKED_UP,   // Collected by NGO/delivery
    DELIVERED,   // Successfully delivered
    EXPIRED  //no longer usable
}
