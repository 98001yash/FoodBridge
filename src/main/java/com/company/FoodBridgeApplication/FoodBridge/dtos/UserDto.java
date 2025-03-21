package com.company.FoodBridgeApplication.FoodBridge.dtos;


import com.company.FoodBridgeApplication.FoodBridge.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Set<Roles> roles;
}
