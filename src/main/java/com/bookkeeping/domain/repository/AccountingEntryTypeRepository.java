package com.bookkeeping.domain.repository;

import com.bookkeeping.domain.model.AccountingEntryType;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class AccountingEntryTypeRepository implements PanacheRepository<AccountingEntryType> {

    public Uni<AccountingEntryType> findById(final UUID id) {
        return find("id", id).firstResult();
    }

    public Uni<AccountingEntryType> save(final AccountingEntryType entity) {
        return persist(entity);
    }

    public Uni<Void> deleteById(final UUID id) {
        return delete("id", id).replaceWithVoid();
    }
}
