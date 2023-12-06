package com.shaiksafi.virtualpowerplant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaiksafi.virtualpowerplant.dto.BatteryDTO;
import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDTO;
import com.shaiksafi.virtualpowerplant.entity.Battery;
import com.shaiksafi.virtualpowerplant.repository.BatteryRepository;

//Service class for handling logic
@Service
public class PowerPlantService {

    private final BatteryRepository batteryRepository;
    
    public PowerPlantService(BatteryRepository batteryRepository){
    	this.batteryRepository = batteryRepository;
    }

    public void addBatteries(List<BatteryDTO> batteryDTOs) {
        List<Battery> batteries = batteryDTOs.stream()
                .map(dto -> new Battery(dto.name(), dto.postcode(), dto.wattCapacity()))
                .collect(Collectors.toList());
        batteryRepository.saveAll(batteries);
    }


    public BatteryStatisticsResponseDTO getBatteryStatistics(String start, String end) {
        List<Battery> batteriesInRange = batteryRepository.findByPostcodeBetween(start, end);

        double total = batteriesInRange.stream().mapToDouble(Battery::getWattCapacity).sum();

        double average = batteriesInRange.stream().mapToDouble(Battery::getWattCapacity).average().orElse(0.0);

        List<String> names = batteriesInRange.stream()
                .map(Battery::getName)
                .sorted()
                .collect(Collectors.toList());

        BatteryStatisticsResponseDTO responseDTO = new BatteryStatisticsResponseDTO(total, average, names);
        return responseDTO;
    }
}

