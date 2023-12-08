package com.shaiksafi.virtualpowerplant.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

//public record BatteryDTO(String name, String postcode, double wattCapacity) {}
public record BatteryDTO(
        @NotBlank(message = "Name cannot be blank") String name,
        @NotNull(message = "Postcode cannot be null")
        @Positive(message = "Postcode must be a positive value")
        @Digits(integer = 6, fraction = 0, message = "Invalid postcode format, must be a positive numeric value with 6 digits") Integer postcode,
        @NotNull(message = "Watt capacity cannot be null")
        @Positive(message = "Watt capacity must be a positive value")
        @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "Watt capacity must be a positive numeric value with up to 2 decimal places") double wattCapacity
) {}