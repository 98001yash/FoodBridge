package com.company.FoodBridgeApplication.FoodBridge.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food_request")
public class FoodRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private FoodReceiver receiver;

    @ManyToOne
    @JoinColumn(name = "donation_idit ")
    private FoodDonation foodDonation;


    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime requestDate = LocalDateTime.now();
}
