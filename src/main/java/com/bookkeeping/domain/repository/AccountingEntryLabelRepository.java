package com.bookkeeping.domain.repository;

import com.bookkeeping.domain.model.AccountingEntryLabel;
import com.bookkeeping.domain.model.Vehicle;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class AccountingEntryLabelRepository implements PanacheRepository<AccountingEntryLabel> {

    public Uni<AccountingEntryLabel> findById(final UUID id) {
        return find("id", id).firstResult();
    }

    public Uni<AccountingEntryLabel> save(final AccountingEntryLabel entity) {
        return persist(entity);
    }

    public Uni<Void> deleteById(final UUID id) {
        return delete("id", id).replaceWithVoid();
    }
}
