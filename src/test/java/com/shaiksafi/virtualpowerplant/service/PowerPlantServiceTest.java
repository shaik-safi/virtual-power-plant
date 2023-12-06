package com.shaiksafi.virtualpowerplant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shaiksafi.virtualpowerplant.dto.BatteryStatisticsResponseDTO;
import com.shaiksafi.virtualpowerplant.entity.Battery;
import com.shaiksafi.virtualpowerplant.entity.BatteryStatisticsResponse;
import com.shaiksafi.virtualpowerplant.repository.BatteryRepository;

public class PowerPlantServiceTest {

    private PowerPlantService powerPlantService;
    private BatteryRepository batteryRepository;

    @BeforeEach
    void setUp() {
        batteryRepository = mock(BatteryRepository.class);
        powerPlantService = new PowerPlantService(batteryRepository);
    }

    @Test
    void testGetBatteryStatistics() {
        List<Battery> batteries = Arrays.asList(
                new Battery("Battery1", "46845", 100),
                new Battery("Battery2", "54862", 300),
                new Battery("Battery3", "87259", 150)
        );

        when(batteryRepository.findByPostcodeBetween("10000", "30000")).thenReturn(batteries);

        BatteryStatisticsResponseDTO response = powerPlantService.getBatteryStatistics("10000", "30000");

        assertEquals(550.0, response.getTotal());
        assertEquals(183.33, response.getAverage(), 0.01);
        assertEquals(Arrays.asList("Battery1", "Battery2", "Battery3"), response.getNames());
    }
}
