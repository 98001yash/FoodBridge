package com.company.FoodBridgeApplication.FoodBridge.entities;


import com.company.FoodBridgeApplication.FoodBridge.enums.DonationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private User donor;
    private String foodType;
    private String pickupLocation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private DonationStatus status;
}
