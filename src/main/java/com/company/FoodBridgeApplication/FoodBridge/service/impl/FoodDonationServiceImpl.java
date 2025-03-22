package com.company.FoodBridgeApplication.FoodBridge.service.impl;


import com.company.FoodBridgeApplication.FoodBridge.dtos.FoodDonationDto;
import com.company.FoodBridgeApplication.FoodBridge.entities.FoodDonation;
import com.company.FoodBridgeApplication.FoodBridge.entities.User;
import com.company.FoodBridgeApplication.FoodBridge.exceptions.ResourceNotFoundException;
import com.company.FoodBridgeApplication.FoodBridge.repository.FoodDonationRepository;
import com.company.FoodBridgeApplication.FoodBridge.repository.UserRepository;
import com.company.FoodBridgeApplication.FoodBridge.service.FoodDonationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodDonationServiceImpl implements FoodDonationService {

    private final FoodDonationRepository foodDonationRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Override
    public FoodDonationDto addDonation(FoodDonationDto foodDonationDto) {
        log.info("Adding a new food donation: {}", foodDonationDto);

        User donor = userRepository.findById(foodDonationDto.getDonorId())
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        FoodDonation foodDonation = modelMapper.map(foodDonationDto, FoodDonation.class);
        foodDonation.setDonor(donor); // Set donor

        FoodDonation savedDonation = foodDonationRepository.save(foodDonation);
        return modelMapper.map(savedDonation, FoodDonationDto.class);
    }


    @Override
    public List<FoodDonationDto> getAllDonations() {
       log.info("Fetching food donation by ID");
       List<FoodDonation> donations = foodDonationRepository.findAll();
       return donations.stream()
               .map(donation-> modelMapper.map(donation, FoodDonationDto.class))
               .collect(Collectors.toList());
    }

    @Override
    public FoodDonationDto getFoodDonationById(Long id) {
       log.info("Fetching food donation by ID: {}",id);
       FoodDonation donation = foodDonationRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Food donation not found with ID: "+id));
       return modelMapper.map(donation, FoodDonationDto.class);
    }

    @Override
    public FoodDonationDto updateFoodDonation(Long id, FoodDonationDto foodDonationDto) {
       log.info("Updating food donation with ID: {}",id);

       FoodDonation existingFoodDonation = foodDonationRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Food donation not found with ID: {}"+id));

       modelMapper.map(foodDonationDto, existingFoodDonation);
       FoodDonation updatedFoodDonation = foodDonationRepository.save(existingFoodDonation);

       return modelMapper.map(updatedFoodDonation, FoodDonationDto.class);
    }

    @Override
    public void deleteDonation(Long id) {
     log.info("Deleting food donation with ID:",id);
     FoodDonation donation = foodDonationRepository.findById(id)
             .orElseThrow(()->new ResourceNotFoundException("Food donation not found with ID: "+id));
     foodDonationRepository.delete(donation);
    }
}
