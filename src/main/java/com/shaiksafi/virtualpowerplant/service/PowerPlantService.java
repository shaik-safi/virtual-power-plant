package com.shaiksafi.virtualpowerplant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaiksafi.virtualpowerplant.model.Battery;
import com.shaiksafi.virtualpowerplant.model.BatteryStatisticsResponse;
import com.shaiksafi.virtualpowerplant.repository.BatteryRepository;

@Service
public class PowerPlantService {

	@Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    public PowerPlantService(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    
	public void addBatteries(List<Battery> batteries) {
        batteryRepository.saveAll(batteries);
    }

    public BatteryStatisticsResponse getBatteryStatistics(String start, String end) {
        List<Battery> batteriesInRange = batteryRepository.findByPostcodeBetween(start, end);

        double total = batteriesInRange.stream().mapToDouble(battery -> battery.getWattCapacity()).sum();

        double average = batteriesInRange.stream().mapToDouble(battery -> battery.getWattCapacity()).average().orElse(0.0);

        List<String> names = batteriesInRange.stream().map(battery -> battery.getName()).sorted().collect(Collectors.toList());

        return new BatteryStatisticsResponse(total, average, names);
    }

}
