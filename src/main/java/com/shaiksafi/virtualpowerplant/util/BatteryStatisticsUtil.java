package com.shaiksafi.virtualpowerplant.util;

import java.util.List;
import java.util.stream.Collectors;

import com.shaiksafi.virtualpowerplant.dto.BatteryDto;
import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDto;

public class BatteryStatisticsUtil {

	public static BatteryStatisticsResponseDto calculateBatteryStatistics(List<BatteryDto> batteryDtos) {
		double total = batteryDtos.stream().mapToDouble(dto -> dto.wattCapacity()).sum();
		double average = batteryDtos.stream().mapToDouble(dto -> dto.wattCapacity()).average().orElse(0.0);
		List<String> names = batteryDtos.stream()
		        .map(BatteryDto::name)
		        .sorted()
		        .collect(Collectors.toList());


        return new BatteryStatisticsResponseDto(total, average, names);
    }
}
