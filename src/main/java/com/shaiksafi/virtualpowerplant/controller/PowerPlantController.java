package com.shaiksafi.virtualpowerplant.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shaiksafi.virtualpowerplant.dto.BatteryDTO;
import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDTO;
import com.shaiksafi.virtualpowerplant.service.PowerPlantService;

//Controller class for handling requests related to the virtual power plant.

@RestController
@RequestMapping("/power-plant")
public class PowerPlantController {

    private final PowerPlantService powerPlantService;
    
    public PowerPlantController(PowerPlantService powerPlantService) {
    	this.powerPlantService = powerPlantService;
    }
    
    
    @PostMapping("/batteries")
    public ResponseEntity<Void> addBatteries(@RequestBody List<BatteryDTO> batteryDTOs) {
        powerPlantService.addBatteries(batteryDTOs);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/battery-statistics")
    public ResponseEntity<BatteryStatisticsResponseDTO> getBatteryStatistics(@RequestParam String start, @RequestParam String end) {
        BatteryStatisticsResponseDTO responseDTO = powerPlantService.getBatteryStatistics(start, end);
        return ResponseEntity.ok().body(responseDTO);
    }
}

