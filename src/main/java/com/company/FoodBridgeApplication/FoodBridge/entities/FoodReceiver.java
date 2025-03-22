package com.company.FoodBridgeApplication.FoodBridge.entities;


import com.company.FoodBridgeApplication.FoodBridge.enums.ReceiverType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "food_receiver")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodReceiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String location;

    @Enumerated(EnumType.STRING)
    private  ReceiverType receiverType;

}
