package com.company.FoodBridgeApplication.FoodBridge.controller;


import com.company.FoodBridgeApplication.FoodBridge.dtos.FoodRequestDto;
import com.company.FoodBridgeApplication.FoodBridge.enums.RequestStatus;
import com.company.FoodBridgeApplication.FoodBridge.service.FoodRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-requests")
@RequiredArgsConstructor
public class FoodRequestController {

    private final FoodRequestService foodRequestService;


    @PostMapping("/request")
    public ResponseEntity<FoodRequestDto> requestFood(@RequestBody FoodRequestDto foodRequestDto){
        return ResponseEntity.ok(foodRequestService.requestFood(foodRequestDto));
    }

    public ResponseEntity<FoodRequestDto> updateStatus(@PathVariable Long requestId,
                                                       @RequestParam RequestStatus status){
        return ResponseEntity.ok(foodRequestService.updateRequestStatus(requestId, status));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodRequestDto>> getAllRequests(){
        return ResponseEntity.ok(foodRequestService.getAllRequests());
    }
}
