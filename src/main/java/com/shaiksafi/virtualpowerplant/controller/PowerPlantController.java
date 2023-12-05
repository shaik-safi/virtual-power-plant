package com.shaiksafi.virtualpowerplant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shaiksafi.virtualpowerplant.model.Battery;
import com.shaiksafi.virtualpowerplant.model.BatteryStatisticsResponse;
import com.shaiksafi.virtualpowerplant.service.PowerPlantService;

//Controller class for handling requests related to the virtual power plant.

@RestController
@RequestMapping("/power-plant")
public class PowerPlantController {
	
	@Autowired
    private PowerPlantService powerPlantService;
	
	//Endpoint for adding batteries
    @PostMapping("/batteries")
    public ResponseEntity<Void> addBatteries(@RequestBody List<Battery> batteries) {
        powerPlantService.addBatteries(batteries);
        return ResponseEntity.ok().build();
    }
    
    //Endpoint for retrieving statistics for batteries within the range
    @GetMapping("/battery-statistics")
    public ResponseEntity<BatteryStatisticsResponse> getBatteryStatistics(@RequestParam String start, @RequestParam String end) {
        BatteryStatisticsResponse statisticsResponse = powerPlantService.getBatteryStatistics(start, end);
        return ResponseEntity.ok().body(statisticsResponse);
    }

}
