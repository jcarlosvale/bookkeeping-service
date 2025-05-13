package com.bookkeeping.application.mapper;

import com.bookkeeping.application.dto.VehicleCreateOrUpdateDTO;
import com.bookkeeping.application.dto.VehicleDTO;
import com.bookkeeping.domain.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VehicleMapperTest {

    private final VehicleMapper mapper = new VehicleMapperImpl();

    @Test
    void testToDTOAndBack() {
        UUID id = UUID.randomUUID();
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("ABC1234");
        vehicle.setModel("Onix");
        vehicle.setManufacturer("Chevrolet");
        vehicle.setChassis("CHS-998877");
        vehicle.setYear(2020);
        vehicle.setPurchaseDate(LocalDate.of(2021, 1, 15));
        vehicle.setPurchaseValue(new BigDecimal("55000.00"));
        vehicle.setSaleDate(LocalDate.of(2023, 5, 1));
        vehicle.setSaleValue(new BigDecimal("40000.00"));
        vehicle.setMaintenanceCost(new BigDecimal("1500.00"));
        vehicle.setLastMaintenance(LocalDate.of(2023, 3, 20));
        vehicle.setColor("Prata");
        vehicle.setEngine("1.0 Turbo");
        vehicle.setFuelType("Gasolina");
        vehicle.setStatus("DISPONIVEL");

        VehicleDTO dto = mapper.toDTO(vehicle);

        assertEquals("ABC1234", dto.licensePlate());
        assertEquals("Onix", dto.model());
        assertEquals("Chevrolet", dto.manufacturer());
        assertEquals("CHS-998877", dto.chassis());
        assertEquals(2020, dto.year());
        assertEquals(new BigDecimal("55000.00"), dto.purchaseValue());
        assertEquals(new BigDecimal("1500.00"), dto.maintenanceCost());

        VehicleCreateOrUpdateDTO createOrUpdateDTO = mapper.toCreateDTO(dto);

        Vehicle convertedBack = mapper.toEntity(createOrUpdateDTO);
        assertEquals("ABC1234", convertedBack.getLicensePlate());
        assertEquals("Onix", convertedBack.getModel());
        assertEquals("CHS-998877", convertedBack.getChassis());
    }

}