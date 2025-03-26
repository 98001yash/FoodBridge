package com.company.FoodBridgeApplication.FoodBridge.service;


import com.company.FoodBridgeApplication.FoodBridge.dtos.FoodRequestDto;
import com.company.FoodBridgeApplication.FoodBridge.entities.FoodDonation;
import com.company.FoodBridgeApplication.FoodBridge.entities.FoodReceiver;
import com.company.FoodBridgeApplication.FoodBridge.entities.FoodRequest;
import com.company.FoodBridgeApplication.FoodBridge.enums.RequestStatus;
import com.company.FoodBridgeApplication.FoodBridge.repository.FoodDonationRepository;
import com.company.FoodBridgeApplication.FoodBridge.repository.FoodReceiverRepository;
import com.company.FoodBridgeApplication.FoodBridge.repository.FoodRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodRequestService {

    private final FoodRequestRepository foodRequestRepository;
    private final FoodDonationRepository foodDonationRepository;
    private final FoodReceiverRepository foodReceiverRepository;
    private final ModelMapper modelMapper;

    public FoodRequestDto requestFood(FoodRequestDto requestDto){
        log.info("New Food request received: {}", requestDto);

        FoodDonation donation = foodDonationRepository.findById(requestDto.getFoodDonationId())
                .orElseThrow(()->new RuntimeException("Food donation not found"));

        FoodReceiver receiver = foodReceiverRepository.findById(requestDto.getReceiverId())
                .orElseThrow(()->new RuntimeException("Receiver not found."));

        FoodRequest foodRequest = FoodRequest.builder()
                .foodDonation(donation)
                .receiver(receiver)
                .status(RequestStatus.PENDING)
                .requestDate(LocalDateTime.now())
                .build();

        FoodRequest savedRequest = foodRequestRepository.save(foodRequest);
        return modelMapper.map(savedRequest, FoodRequestDto.class);
    }

    public FoodRequestDto updateRequestStatus(Long requestId, RequestStatus status){
        FoodRequest foodRequest = foodRequestRepository.findById(requestId)
                .orElseThrow(()->new RuntimeException("Food request not found"));

        foodRequest.setStatus(status);
        FoodRequest updatedRequest = foodRequestRepository.save(foodRequest);
        return modelMapper.map(updatedRequest, FoodRequestDto.class);
    }

    public List<FoodRequestDto> getAllRequests(){
        return foodRequestRepository.findAll()
                .stream()
                .map(request->modelMapper.map(request, FoodRequestDto.class))
                .collect(Collectors.toList());
    }
}
