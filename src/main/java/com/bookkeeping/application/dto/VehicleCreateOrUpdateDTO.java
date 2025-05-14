package com.bookkeeping.application.dto;

import com.bookkeeping.domain.enums.VehicleStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VehicleCreateOrUpdateDTO(
    String licensePlate,
    @NotBlank(message = "Modelo não pode estar vazio(a).")
    String model,
    String manufacturer,
    String chassis,
    Integer year,
    @NotNull(message = "Data de compra não pode estar vazio(a).")
    LocalDate purchaseDate,
    @NotNull(message = "Valor de compra não pode estar vazio(a).")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor de compra deve ser maior que zero.")
    BigDecimal purchaseValue,
    LocalDate saleDate,
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor de venda deve ser maior que zero.")
    BigDecimal saleValue,
    @DecimalMin(value = "0.0", inclusive = false, message = "Custo de manutenção deve ser maior que zero.")
    BigDecimal maintenanceCost,
    LocalDate lastMaintenance,
    String color,
    String engine,
    String fuelType,
    VehicleStatus status
) {}
