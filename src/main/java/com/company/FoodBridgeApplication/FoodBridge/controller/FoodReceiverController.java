package com.company.FoodBridgeApplication.FoodBridge.controller;


import com.company.FoodBridgeApplication.FoodBridge.dtos.FoodReceiverDto;
import com.company.FoodBridgeApplication.FoodBridge.service.FoodReceiverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receivers")
@RequiredArgsConstructor
@Slf4j
public class FoodReceiverController {

    private final FoodReceiverService foodReceiverService;

    @PostMapping
    public ResponseEntity<FoodReceiverDto> registerReceiver(@RequestBody FoodReceiverDto foodReceiverDto) {
        log.info("Received request to register food receiver: {}", foodReceiverDto);
        FoodReceiverDto savedReceiver = foodReceiverService.registerReceiver(foodReceiverDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReceiver);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FoodReceiverDto> getReceiverById(@PathVariable Long id){
        log.info("Fetching food Receiver by ID: {}",id);
        FoodReceiverDto receiverDto = foodReceiverService.getReceiverById(id);
        return ResponseEntity.ok(receiverDto);
    }
}
