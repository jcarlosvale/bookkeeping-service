package com.bookkeeping.domain.repository;

import com.bookkeeping.domain.model.Vehicle;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class VehicleRepository implements PanacheRepository<Vehicle> {

    public Uni<Vehicle> findById(final UUID id) {
        return find("id", id).firstResult();
    }

    public Uni<Vehicle> save(final Vehicle entity) {
        return persist(entity);
    }

    public Uni<Void> deleteById(final UUID id) {
        return delete("id", id).replaceWithVoid();
    }
}
