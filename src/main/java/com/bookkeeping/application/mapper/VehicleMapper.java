package com.bookkeeping.application.mapper;

import com.bookkeeping.application.dto.VehicleCreateOrUpdateDTO;
import com.bookkeeping.application.dto.VehicleDTO;
import com.bookkeeping.domain.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface VehicleMapper {

    VehicleDTO toDTO(Vehicle vehicle);

    VehicleCreateOrUpdateDTO toCreateDTO(VehicleDTO vehicleDTO);

    Vehicle toEntity(VehicleCreateOrUpdateDTO dto);
}
