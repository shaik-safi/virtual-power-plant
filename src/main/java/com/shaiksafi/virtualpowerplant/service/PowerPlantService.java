package com.shaiksafi.virtualpowerplant.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaiksafi.virtualpowerplant.dto.BatteryDTO;
import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDTO;
import com.shaiksafi.virtualpowerplant.entity.Battery;
import com.shaiksafi.virtualpowerplant.repository.BatteryRepository;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Service class for handling logic
@Slf4j
@Service
public class PowerPlantService {

    private final BatteryRepository batteryRepository;
    private Logger log = LoggerFactory.getLogger(PowerPlantService.class);

    @Autowired
    public PowerPlantService(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    public void addBatteries(List<BatteryDTO> batteryDTOs) {
        log.info("PowerPlantService:addBatteries execution started.");
        List<Battery> batteries = batteryDTOs.stream()
                .map(dto -> new Battery(dto.name(), dto.postcode(), dto.wattCapacity()))
                .collect(Collectors.toList());
        Iterable<Battery> result = batteryRepository.saveAll(batteries);
        log.debug("PowerPlantService:addBatteries received response from Database {}.", result);
        log.info("PowerPlantService:addBatteries execution ended.");
    }

    public BatteryStatisticsResponseDTO getBatteryStatistics(int startPostcode, int endPostcode) {
        log.info("PowerPlantService:getBatteryStatistics execution started for range {} to {}.", startPostcode, endPostcode);
        List<Battery> batteriesInRange = batteryRepository.findByPostcodeBetween(startPostcode, endPostcode);

        double total = batteriesInRange.stream().mapToDouble(Battery::getWattCapacity).sum();

        double average = batteriesInRange.stream().mapToDouble(Battery::getWattCapacity).average().orElse(0.0);

        List<String> names = batteriesInRange.stream()
                .map(Battery::getName)
                .sorted()
                .collect(Collectors.toList());

        BatteryStatisticsResponseDTO responseDTO = new BatteryStatisticsResponseDTO(total, average, names);
        log.debug("PowerPlantService:getBatteryStatistics calculated statistics: {}", responseDTO);
        log.info("PowerPlantService:getBatteryStatistics execution ended.");
        return responseDTO;
    }

    public List<BatteryDTO> getBatteries() {
        log.info("PowerPlantService:getBatteries execution started.");
        Iterable<Battery> batteriesEntities = batteryRepository.findAll();
        List<BatteryDTO> batteriesDTO = new ArrayList<>();

        batteriesEntities.forEach((battery) -> batteriesDTO.add(new BatteryDTO(battery.getName(), battery.getPostcode(), battery.getWattCapacity())));
        log.debug("PowerPlantService:getBatteries retrieved batteries: {}", batteriesDTO);
        log.info("PowerPlantService:getBatteries execution ended.");
        return batteriesDTO;
    }
}

