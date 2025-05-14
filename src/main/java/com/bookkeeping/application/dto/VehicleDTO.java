package com.bookkeeping.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record VehicleDTO(
    UUID id,
    String licensePlate,
    String chassis,
    String model,
    String manufacturer,
    Integer year,
    LocalDate purchaseDate,
    BigDecimal purchaseValue,
    LocalDate saleDate,
    BigDecimal saleValue,
    BigDecimal maintenanceCost,
    LocalDate lastMaintenance,
    String color,
    String engine,
    String fuelType,
    String status
) { }
