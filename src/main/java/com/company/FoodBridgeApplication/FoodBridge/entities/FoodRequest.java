package com.company.FoodBridgeApplication.FoodBridge.entities;


import com.company.FoodBridgeApplication.FoodBridge.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food_request")
@Builder
public class FoodRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private FoodReceiver receiver;

    @ManyToOne
    @JoinColumn(name = "donation_id")
    private FoodDonation foodDonation;


    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private LocalDateTime requestDate = LocalDateTime.now();
}
