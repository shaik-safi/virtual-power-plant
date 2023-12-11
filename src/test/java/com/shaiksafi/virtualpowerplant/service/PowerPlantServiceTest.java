package com.shaiksafi.virtualpowerplant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shaiksafi.virtualpowerplant.dto.BatteryDto;
import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDto;
import com.shaiksafi.virtualpowerplant.mapper.BatteryMapper;
import com.shaiksafi.virtualpowerplant.model.Battery;
import com.shaiksafi.virtualpowerplant.repository.BatteryRepository;

//Unit tests for the PowerPlantServiceImpl class
public class PowerPlantServiceTest {

    private PowerPlantServiceImpl powerPlantService;
    private BatteryRepository batteryRepository;
    private BatteryMapper batteryMapper;

    @BeforeEach
    void setUp() {
        // Mock the BatteryRepository and BatteryMapper for testing.
        batteryRepository = mock(BatteryRepository.class);
        batteryMapper = mock(BatteryMapper.class);
        // Initialize the PowerPlantService with the mocked dependencies.
        powerPlantService = new PowerPlantServiceImpl(batteryRepository, batteryMapper);
    }

    //Test the getBatteryStatistics method of PowerPlantServiceImpl.
    @Test
    void testGetBatteryStatistics() {
    	
        // Mocked list of batteries with different watt capacities.
        List<Battery> batteries = Arrays.asList(
                new Battery("Battery1", "560060", 1000),
                new Battery("Battery2", "560100", 3000),
                new Battery("Battery3", "560550", 1500)
        );

        // Mock the behavior of findByPostcodeBetween method in the BatteryRepository.
        when(batteryRepository.findByPostcodeBetween("10000", "30000")).thenReturn(batteries);

        // Mock the behavior of toDto method in the BatteryMapper for each battery.
        when(batteryMapper.toDto(batteries.get(0))).thenReturn(new BatteryDto("Battery1", "560060", 1000));
        when(batteryMapper.toDto(batteries.get(1))).thenReturn(new BatteryDto("Battery2", "560100", 3000));
        when(batteryMapper.toDto(batteries.get(2))).thenReturn(new BatteryDto("Battery3", "560550", 1500));

        // Call the method under test.
        BatteryStatisticsResponseDto response = powerPlantService.getBatteryStatistics("10000", "30000");

        // Assert the expected values based on the mocked data.
        assertEquals(5500.0, response.total());
        assertEquals(1833.33, response.average(), 0.01);
        assertEquals(Arrays.asList("Battery1", "Battery2", "Battery3"), response.names());
    }
}
