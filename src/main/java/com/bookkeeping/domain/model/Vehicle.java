package com.bookkeeping.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "license_plate", nullable = false, length = 20)
    private String licensePlate;

    @Column(nullable = false, length = 100)
    private String model;

    @Column(length = 100)
    private String manufacturer;

    @Column(length = 100)
    private String chassis;

    @Column(name = "vehicle_year")
    private Integer year;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "purchase_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal purchaseValue;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "sale_value", precision = 15, scale = 2)
    private BigDecimal saleValue;

    @Column(name = "maintenance_cost", precision = 15, scale = 2)
    private BigDecimal maintenanceCost;

    @Column(name = "last_maintenance")
    private LocalDate lastMaintenance;

    @Column(length = 50)
    private String color;

    @Column(length = 50)
    private String engine;

    @Column(name = "fuel_type", length = 50)
    private String fuelType;

    @Column(length = 50)
    private String status;

    // === Getters ===

    public UUID getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getChassis() {
        return chassis;
    }

    public Integer getYear() {
        return year;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public BigDecimal getSaleValue() {
        return saleValue;
    }

    public BigDecimal getMaintenanceCost() {
        return maintenanceCost;
    }

    public LocalDate getLastMaintenance() {
        return lastMaintenance;
    }

    public String getColor() {
        return color;
    }

    public String getEngine() {
        return engine;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getStatus() {
        return status;
    }

    // === Setters ===
    public void setId(final UUID id) {
        this.id = id;
    }

    public void setLicensePlate(final String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setChassis(final String chassis) {
        this.chassis = chassis;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public void setPurchaseDate(final LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseValue(final BigDecimal purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public void setSaleDate(final LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public void setSaleValue(final BigDecimal saleValue) {
        this.saleValue = saleValue;
    }

    public void setMaintenanceCost(final BigDecimal maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public void setLastMaintenance(final LocalDate lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public void setEngine(final String engine) {
        this.engine = engine;
    }

    public void setFuelType(final String fuelType) {
        this.fuelType = fuelType;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
