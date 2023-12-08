package com.shaiksafi.virtualpowerplant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import jakarta.validation.Valid;

//Controller class for handling requests related to the virtual power plant.

@RestController
@RequestMapping("/power-plant")
public class PowerPlantController {

    private final PowerPlantService powerPlantService;
    private Logger log = LoggerFactory.getLogger(PowerPlantService.class);
    
    public PowerPlantController(PowerPlantService powerPlantService) {
    	this.powerPlantService = powerPlantService;
    }
    
    
    @PostMapping("/batteries")
    public ResponseEntity<List<BatteryDTO>> addBatteries(@RequestBody @Valid List<BatteryDTO> batteryDTOs) {
        log.info("PowerPlantController:addBatteries execution started.");
        powerPlantService.addBatteries(batteryDTOs);
        log.info("PowerPlantController:addBatteries execution ended.");
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/batteries")
    public ResponseEntity<List<BatteryDTO>> getBatteries() {
        log.info("PowerPlantController:getBatteries execution started.");
        List<BatteryDTO> batteriesDTO = powerPlantService.getBatteries();
        log.debug("PowerPlantController:getBatteries retrieved batteries: {}", batteriesDTO);
        log.info("PowerPlantController:getBatteries execution ended.");
        return ResponseEntity.ok(batteriesDTO);
    }

    @GetMapping("/battery-statistics")
    public ResponseEntity<BatteryStatisticsResponseDTO> getBatteryStatistics(@RequestParam int startPostcode, @RequestParam int endPostcode) {
        log.info("PowerPlantController:getBatteryStatistics execution started for range {} to {}.", startPostcode, endPostcode);
        BatteryStatisticsResponseDTO statisticsResponseDTO = powerPlantService.getBatteryStatistics(startPostcode, endPostcode);
        log.debug("PowerPlantController:getBatteryStatistics calculated statistics: {}", statisticsResponseDTO);
        log.info("PowerPlantController:getBatteryStatistics execution ended.");
        return ResponseEntity.ok().body(statisticsResponseDTO);
    }
}