package com.shaiksafi.virtualpowerplant.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shaiksafi.virtualpowerplant.dto.BatteryDto;
import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDto;
import com.shaiksafi.virtualpowerplant.service.PowerPlantServiceImpl;

import jakarta.validation.Valid;


//PowerPlantController handles incoming HTTP requests related to the virtual power plant system.
@RestController
@RequestMapping("/power-plant")
public class PowerPlantController {

 // Service responsible for handling business logic related to the power plant.
 private final PowerPlantServiceImpl powerPlantService;
 
 // Logger for logging messages in the controller.
 private Logger log = LoggerFactory.getLogger(PowerPlantServiceImpl.class);

 // Constructor to inject the PowerPlantService instance.
 public PowerPlantController(PowerPlantServiceImpl powerPlantService) {
     this.powerPlantService = powerPlantService;
 }

 // Endpoint to add batteries to the system.
 @PostMapping("/batteries")
 public ResponseEntity<?> addBatteries(@RequestBody @Valid List<BatteryDto> batteryRequestBatteryDtos,BindingResult bindingResult) {
     log.info("PowerPlantController:addBatteries execution started.");

     // Check for validation errors in the incoming data.
     if (bindingResult.hasErrors()) {
         List<String> errors = bindingResult.getFieldErrors().stream()
                 .map(FieldError::getDefaultMessage)
                 .collect(Collectors.toList());

         log.error("Validation errors occurred: {}", errors);

         return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
     }

     // Add batteries to the system and return the result.
     List<BatteryDto> batteriesDtos = powerPlantService.addBatteries(batteryRequestBatteryDtos);
     log.info("PowerPlantController:addBatteries execution ended.");

     return new ResponseEntity<>(batteriesDtos, HttpStatus.CREATED);
 }

 // Endpoint to retrieve all batteries from the system.
 @GetMapping("/batteries")
 public ResponseEntity<List<BatteryDto>> getBatteries() {
     log.info("PowerPlantController:getBatteries execution started.");
     List<BatteryDto> batteriesDtos = powerPlantService.getBatteries();
     log.debug("PowerPlantController:getBatteries retrieved batteries: {}", batteriesDtos);
     log.info("PowerPlantController:getBatteries execution ended.");
     return new ResponseEntity<>(batteriesDtos, HttpStatus.OK);
 }

 // Endpoint to retrieve battery statistics for a given postcode range.
 @GetMapping("/battery-statistics")
 public ResponseEntity<BatteryStatisticsResponseDto> getBatteryStatistics(@RequestParam String startPostcode, @RequestParam String endPostcode) {
     log.info("PowerPlantController:getBatteryStatistics execution started for range {} to {}.", startPostcode, endPostcode);
     BatteryStatisticsResponseDto statisticsResponseDto = powerPlantService.getBatteryStatistics(startPostcode, endPostcode);
     log.debug("PowerPlantController:getBatteryStatistics calculated statistics: {}", statisticsResponseDto);
     log.info("PowerPlantController:getBatteryStatistics execution ended.");
     return new ResponseEntity<>(statisticsResponseDto, HttpStatus.OK);
 }
}