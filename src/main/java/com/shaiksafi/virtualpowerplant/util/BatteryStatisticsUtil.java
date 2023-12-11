package com.shaiksafi.virtualpowerplant.util;

import java.util.List;
import java.util.stream.Collectors;

import com.shaiksafi.virtualpowerplant.dto.BatteryDto;
import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDto;

//Utility class for calculating battery statistics.
public class BatteryStatisticsUtil {
	
	//Calculates battery statistics based on the provided list of BatteryDto objects.
	public static BatteryStatisticsResponseDto calculateBatteryStatistics(List<BatteryDto> batteryDtos) {
		// Calculate total watt capacity of all batteries
		double total = batteryDtos.stream().mapToDouble(dto -> dto.wattCapacity()).sum();
		// Calculate average watt capacity of all batteries
		double average = batteryDtos.stream().mapToDouble(dto -> dto.wattCapacity()).average().orElse(0.0);
		// Extract and sort battery names
		List<String> names = batteryDtos.stream()
		        .map(BatteryDto::name)
		        .sorted()
		        .collect(Collectors.toList());

		// Create and return the BatteryStatisticsResponseDto
        return new BatteryStatisticsResponseDto(total, average, names);
    }
}
