package com.shaiksafi.virtualpowerplant.dto;

import java.util.List;

public record BatteryStatisticsResponseDTO(double total, double average, List<String> names) {}