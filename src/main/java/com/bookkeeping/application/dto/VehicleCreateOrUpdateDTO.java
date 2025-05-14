package com.bookkeeping.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VehicleCreateOrUpdateDTO(
    String licensePlate,
    @NotBlank
    String model,
    String manufacturer,
    String chassis,
    Integer year,
    @NotNull
    LocalDate purchaseDate,
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal purchaseValue,
    LocalDate saleDate,
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal saleValue,
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal maintenanceCost,
    LocalDate lastMaintenance,
    String color,
    String engine,
    String fuelType,
    String status
) {}
