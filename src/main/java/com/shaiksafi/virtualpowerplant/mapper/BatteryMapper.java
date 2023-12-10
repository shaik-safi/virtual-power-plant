package com.shaiksafi.virtualpowerplant.mapper;

import org.springframework.stereotype.Component;

import com.shaiksafi.virtualpowerplant.dto.BatteryDto;
import com.shaiksafi.virtualpowerplant.model.Battery;

//BatteryMapper is responsible for converting between Battery entities and BatteryDto objects.
@Component
public class BatteryMapper {

	// Convert Battery entity to BatteryDto.
    public BatteryDto toDto(Battery battery) {
        return new BatteryDto(
                battery.getName(),
                battery.getPostcode(),
                battery.getWattCapacity()
        );
    }
    
    // Convert BatteryDto to Battery entity.
    public Battery toEntity(BatteryDto batteryDto) {
        Battery battery = new Battery();
        battery.setName(batteryDto.name());
        battery.setPostcode(batteryDto.postcode());
        battery.setWattCapacity(batteryDto.wattCapacity());
        return battery;
    }
}
