package com.company.FoodBridgeApplication.FoodBridge.service;


import com.company.FoodBridgeApplication.FoodBridge.dtos.FoodReceiverDto;
import com.company.FoodBridgeApplication.FoodBridge.entities.FoodReceiver;
import com.company.FoodBridgeApplication.FoodBridge.enums.ReceiverType;
import com.company.FoodBridgeApplication.FoodBridge.repository.FoodReceiverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodReceiverService {

    private final FoodReceiverRepository foodReceiverRepository;
    private final ModelMapper modelMapper;

    public FoodReceiverDto registerReceiver(FoodReceiverDto foodReceiverDto) {
        log.info("Registering a new food receiver: {}", foodReceiverDto);

        // Manually mapping fields
        FoodReceiver foodReceiver = new FoodReceiver();
        foodReceiver.setName(foodReceiverDto.getName());
        foodReceiver.setContact(foodReceiverDto.getContact());

        // Assign default values for missing fields
        foodReceiver.setEmail(foodReceiverDto.getName().toLowerCase().replace(" ", "_") + "@example.com"); // Temporary email
        foodReceiver.setLocation("Unknown"); // Default location
        foodReceiver.setReceiverType(ReceiverType.INDIVIDUAL); // Default type

        FoodReceiver savedReceiver = foodReceiverRepository.save(foodReceiver);
        return modelMapper.map(savedReceiver, FoodReceiverDto.class);
    }

    public FoodReceiverDto getReceiverById(Long id) {
        FoodReceiver receiver = foodReceiverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receiver not found."));
        return modelMapper.map(receiver, FoodReceiverDto.class);
    }
}
