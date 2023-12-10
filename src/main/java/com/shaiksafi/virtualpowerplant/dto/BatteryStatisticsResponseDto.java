package com.shaiksafi.virtualpowerplant.dto;

import java.util.List;

//BatteryStatisticsResponseDto represents a data transfer object for battery statistics.
public record BatteryStatisticsResponseDto(double total, double average, List<String> names) {}