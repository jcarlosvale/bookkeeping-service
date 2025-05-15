package com.bookkeeping.application.service;

import com.bookkeeping.application.dto.AccountingEntryLabelCreateOrUpdateDTO;
import com.bookkeeping.application.dto.AccountingEntryLabelDTO;
import com.bookkeeping.application.mapper.AccountingEntryLabelMapper;
import com.bookkeeping.domain.model.AccountingEntryLabel;
import com.bookkeeping.domain.repository.AccountingEntryLabelRepository;
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
public class AccountingEntryLabelService {

    private final AccountingEntryLabelRepository repository;
    private final AccountingEntryLabelMapper mapper;
    private final AuditSessionContext auditSessionContext;

    public AccountingEntryLabelService(final AccountingEntryLabelRepository repository,
                                       final AccountingEntryLabelMapper mapper,
                                       final AuditSessionContext auditSessionContext) {
        this.repository = repository;
        this.mapper = mapper;
        this.auditSessionContext = auditSessionContext;
    }

    @WithSession
    public Uni<AccountingEntryLabelDTO> findById(final UUID id) {
        Objects.requireNonNull(id);
        return repository.findById(id)
                .onItem().ifNull().failWith(() -> new NotFoundException("Label not found: " + id))
                .map(mapper::toDTO);
    }

    @WithSession
    public Uni<List<AccountingEntryLabelDTO>> findAllPaged(final Integer page, final Integer size) {
        if (page == null || size == null) {
            return repository.findAll().list()
                    .onItem().transform(list -> list.stream().map(mapper::toDTO).toList());
        }

        int firstResult = page * size;
        return repository.findAll().page(firstResult, size).list()
                .onItem().transform(list -> list.stream().map(mapper::toDTO).toList());
    }

    @WithTransaction
    public Uni<AccountingEntryLabelDTO> create(final AccountingEntryLabelCreateOrUpdateDTO dto, final String userId) {
        Objects.requireNonNull(dto);
        Objects.requireNonNull(userId);
        return auditSessionContext.setUserId(userId)
                .chain(() -> {
                    final AccountingEntryLabel entity = mapper.toEntity(dto);
                    return repository.save(entity)
                            .map(mapper::toDTO);
                });
    }

    @WithTransaction
    public Uni<AccountingEntryLabelDTO> update(final UUID id, final AccountingEntryLabelCreateOrUpdateDTO dto, final String userId) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(dto);
        Objects.requireNonNull(userId);
        return auditSessionContext.setUserId(userId)
                .chain(() ->
                        repository.findById(id)
                                .onItem().ifNull().failWith(() -> new NotFoundException("Label not found: " + id))
                                .invoke(existing -> {
                                    existing.setCode(dto.code());
                                    existing.setDescription(dto.description());
                                })
                                .map(mapper::toDTO)
                );
    }

    @WithTransaction
    public Uni<Void> delete(final UUID id, final String userId) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(userId);
        return auditSessionContext.setUserId(userId)
                .chain(() -> repository.deleteById(id));
    }
}
