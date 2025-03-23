package com.company.FoodBridgeApplication.FoodBridge.service;


import com.company.FoodBridgeApplication.FoodBridge.dtos.FoodReceiverDto;
import com.company.FoodBridgeApplication.FoodBridge.entities.FoodReceiver;
import com.company.FoodBridgeApplication.FoodBridge.exceptions.ResourceNotFoundException;
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


    public FoodReceiverDto registerReceiver(FoodReceiverDto foodReceiverDto){
        log.info("Registering new food Receiver: {}", foodReceiverDto);

        FoodReceiver foodReceiver = modelMapper.map(foodReceiverDto, FoodReceiver.class);
        FoodReceiver savedReceiver = foodReceiverRepository.save(foodReceiver);

        return modelMapper.map(savedReceiver, FoodReceiverDto.class);
    }


    public FoodReceiverDto getReceiverById(Long id){
        FoodReceiver foodReceiver = foodReceiverRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Food receiver not found"));
        return modelMapper.map(foodReceiver, FoodReceiverDto.class);
    }
}
