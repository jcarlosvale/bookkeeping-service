package com.bookkeeping.application.service;

import com.bookkeeping.application.dto.VehicleDTO;
import com.bookkeeping.application.dto.VehicleCreateOrUpdateDTO;
import com.bookkeeping.application.mapper.VehicleMapper;
import com.bookkeeping.domain.model.Vehicle;
import com.bookkeeping.domain.repository.VehicleRepository;
import com.bookkeeping.infrastructure.audit.AuditSessionContext;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@ApplicationScoped
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final AuditSessionContext auditSessionContext;

    public VehicleService(final VehicleRepository vehicleRepository,
                          final VehicleMapper vehicleMapper,
                          final AuditSessionContext auditSessionContext) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.auditSessionContext = auditSessionContext;
    }

    @WithSession
    public Uni<VehicleDTO> findById(final UUID id) {
        Objects.requireNonNull(id);
        return vehicleRepository.findById(id)
                .onItem().ifNull().failWith(() -> new NotFoundException("Vehicle not found: " + id))
                .map(vehicleMapper::toDTO);
    }

    @WithSession
    public Uni<List<VehicleDTO>> findAllPaged(final Integer page, final Integer size) {
        if (page == null || size == null) {
            return vehicleRepository.findAll().list()
                    .onItem().transform(list -> list.stream().map(vehicleMapper::toDTO).toList());
        }

        int firstResult = page * size;
        return vehicleRepository.findAll().page(firstResult, size).list()
                .onItem().transform(list -> list.stream().map(vehicleMapper::toDTO).toList());
    }

    @WithTransaction
    public Uni<VehicleDTO> create(final VehicleCreateOrUpdateDTO dto, final String userId) {
        Objects.requireNonNull(dto);
        Objects.requireNonNull(userId);
        return auditSessionContext.setUserId(userId)
                .chain(() -> {
                    final Vehicle entity = vehicleMapper.toEntity(dto);
                    return vehicleRepository.save(entity)
                            .map(vehicleMapper::toDTO);
                });
    }

    @WithTransaction
    public Uni<VehicleDTO> update(final UUID id, final VehicleCreateOrUpdateDTO dto, final String userId) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(dto);
        Objects.requireNonNull(userId);
        return auditSessionContext.setUserId(userId)
                .chain(() ->
                        vehicleRepository.findById(id)
                                .onItem().ifNull().failWith(() -> new NotFoundException("Vehicle not found: " + id))
                                .invoke(existing -> {
                                    existing.setLicensePlate(dto.licensePlate());
                                    existing.setModel(dto.model());
                                    existing.setManufacturer(dto.manufacturer());
                                    existing.setChassis(dto.chassis());
                                    existing.setYear(dto.year());
                                    existing.setPurchaseDate(dto.purchaseDate());
                                    existing.setPurchaseValue(dto.purchaseValue());
                                    existing.setSaleDate(dto.saleDate());
                                    existing.setSaleValue(dto.saleValue());
                                    existing.setMaintenanceCost(dto.maintenanceCost());
                                    existing.setLastMaintenance(dto.lastMaintenance());
                                    existing.setColor(dto.color());
                                    existing.setEngine(dto.engine());
                                    existing.setFuelType(dto.fuelType());
                                    existing.setStatus(dto.status());
                                })
                                .map(vehicleMapper::toDTO)
                );
    }

    @WithTransaction
    public Uni<Void> delete(final UUID id, final String userId) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        return auditSessionContext.setUserId(userId)
                .chain(() -> vehicleRepository.deleteById(id));
    }
}
