package com.shaiksafi.virtualpowerplant.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

//BatteryDto represents a data transfer object for Battery entities.
public record BatteryDto(
        @NotBlank(message = "Invalid Name: Empty name")
        @NotNull(message = "Invalid Name: Name is NULL")
        @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
        String name,
        @NotNull(message = "Invalid Postcode: Postcode is NULL")
        @Positive(message = "Invalid Postcode: Postcode must be a positive value")
        @NotBlank(message = "Invalid Postcode: Empty postcode")
        @Pattern(regexp = "^\\d{6}$", message = "Invalid Postcode: Must be a numeric value with 6 digits")
        String postcode,
        @NotNull(message = "Invalid Watt capacity: Watt capacity is NULL")
        @Positive(message = "Invalid Watt capacity: Watt capacity must be a positive value")
        @Min(value = 0, message = "Invalid Watt capacity: Must be greater than or equal to zero")
        double wattCapacity
) {}
