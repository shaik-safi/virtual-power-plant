package com.shaiksafi.virtualpowerplant.service;

import java.util.List;

import com.shaiksafi.virtualpowerplant.dto.BatteryDto;
import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDto;

//PowerPlantService defines the interface for business logic related to the virtual power plant.
public interface PowerPlantService {
	// Add batteries to the system.
	public List<BatteryDto> addBatteries(List<BatteryDto> batteryDtos);
	// Get battery statistics for a given postcode range.
	public BatteryStatisticsResponseDto getBatteryStatistics(String startPostcode, String endPostcode);
	// Get all batteries from the system.
	public List<BatteryDto> getBatteries();
}
