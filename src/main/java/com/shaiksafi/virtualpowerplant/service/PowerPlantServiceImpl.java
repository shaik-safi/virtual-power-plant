package com.shaiksafi.virtualpowerplant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaiksafi.virtualpowerplant.dto.BatteryDto;
import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDto;
import com.shaiksafi.virtualpowerplant.mapper.BatteryMapper;
import com.shaiksafi.virtualpowerplant.model.Battery;
import com.shaiksafi.virtualpowerplant.repository.BatteryRepository;
import com.shaiksafi.virtualpowerplant.util.BatteryStatisticsUtil;

import lombok.extern.slf4j.Slf4j;

//PowerPlantServiceImpl implements the PowerPlantService interface and contains the business logic.
@Slf4j
@Service
public class PowerPlantServiceImpl implements PowerPlantService {

	// Repository for accessing Battery entities in the database.
    private final BatteryRepository batteryRepository;
    // Mapper for converting between Battery entities and BatteryDto objects.
    private final BatteryMapper batteryMapper;
    // Logger for logging messages in the service.
    private Logger log = LoggerFactory.getLogger(PowerPlantServiceImpl.class);
    
    // Constructor to inject the necessary components.
    @Autowired
    public PowerPlantServiceImpl(BatteryRepository batteryRepository, BatteryMapper batteryMapper) {
        this.batteryRepository = batteryRepository;
        this.batteryMapper = batteryMapper;
    }
    
    // Add batteries to the system.
    public List<BatteryDto> addBatteries(List<BatteryDto> batteryDTOs) {
        log.info("PowerPlantService:addBatteries execution started.");
        
        // Convert BatteryDto objects to Battery entities and save them in the database.
        List<Battery> batteries = batteryDTOs.stream()
                .map(batteryMapper::toEntity)
                .collect(Collectors.toList());

        Iterable<Battery> savedBatteries = batteryRepository.saveAll(batteries);
        log.debug("PowerPlantService:addBatteries received response from Database {}.", savedBatteries);
        log.info("PowerPlantService:addBatteries execution ended.");

        // Convert saved Battery entities back to BatteryDto objects and return the result.
        return StreamSupport.stream(savedBatteries.spliterator(), false)
                .map(batteryMapper::toDto)
                .collect(Collectors.toList());
    }
    
    // Get battery statistics for a given postcode range.
    public BatteryStatisticsResponseDto getBatteryStatistics(String startPostcode, String endPostcode) {
        log.info("PowerPlantService:getBatteryStatistics execution started for range {} to {}.", startPostcode, endPostcode);
        
        // Validate the postcode range
        if (startPostcode.compareTo(endPostcode) >= 0) {
            log.error("Invalid postcode range: startPostcode must be less than endPostcode.");
            throw new IllegalArgumentException("Invalid postcode range: startPostcode must be less than endPostcode.");
        }
        
        // Retrieve batteries within the specified postcode range.
        List<Battery> batteriesInRange = batteryRepository.findByPostcodeBetween(startPostcode, endPostcode);
        
        // Convert Battery entities to BatteryDto objects.
        List<BatteryDto> batteryDtos = batteriesInRange.stream()
                .map(batteryMapper::toDto)
                .collect(Collectors.toList());

        // Calculate battery statistics using a utility method.
        BatteryStatisticsResponseDto responseDto = BatteryStatisticsUtil.calculateBatteryStatistics(batteryDtos);

        log.debug("PowerPlantService:getBatteryStatistics calculated statistics: {}", responseDto);
        log.info("PowerPlantService:getBatteryStatistics execution ended.");
        // Return the calculated statistics.
        return responseDto;
    }
    
    // Get all batteries from the system.
    public List<BatteryDto> getBatteries() {
        log.info("PowerPlantService:getBatteries execution started.");
        // Retrieve all batteries from the database.
        Iterable<Battery> batteriesEntities = batteryRepository.findAll();
        List<BatteryDto> batteriesDTO = new ArrayList<>();
        // Convert Battery entities to BatteryDto objects using the mapper for conversion.
        batteriesEntities.forEach((battery) -> batteriesDTO.add(batteryMapper.toDto(battery)));  // Use the mapper for conversion
        log.debug("PowerPlantService:getBatteries retrieved batteries: {}", batteriesDTO);
        log.info("PowerPlantService:getBatteries execution ended.");
        // Return the list of BatteryDto objects.
        return batteriesDTO;
    }
}