package com.bookkeeping.application.service;

import com.bookkeeping.application.dto.AccountingEntryTypeCreateOrUpdateDTO;
import com.bookkeeping.application.dto.AccountingEntryTypeDTO;
import com.bookkeeping.application.mapper.AccountingEntryTypeMapper;
import com.bookkeeping.domain.model.AccountingEntryType;
import com.bookkeeping.domain.repository.AccountingEntryTypeRepository;
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
public class AccountingEntryTypeService {

    private final AccountingEntryTypeRepository repository;
    private final AccountingEntryTypeMapper mapper;
    private final AuditSessionContext auditSessionContext;

    public AccountingEntryTypeService(final AccountingEntryTypeRepository repository,
                                      final AccountingEntryTypeMapper mapper,
                                      final AuditSessionContext auditSessionContext) {
        this.repository = repository;
        this.mapper = mapper;
        this.auditSessionContext = auditSessionContext;
    }

    @WithSession
    public Uni<AccountingEntryTypeDTO> findById(final UUID id) {
        Objects.requireNonNull(id);
        return repository.findById(id)
                .onItem().ifNull().failWith(() -> new NotFoundException("Entry type not found: " + id))
                .map(mapper::toDTO);
    }

    @WithSession
    public Uni<List<AccountingEntryTypeDTO>> findAllPaged(final Integer page, final Integer size) {
        if (page == null || size == null) {
            return repository.findAll().list()
                    .onItem().transform(list -> list.stream().map(mapper::toDTO).toList());
        }

        int firstResult = page * size;
        return repository.findAll().page(firstResult, size).list()
                .onItem().transform(list -> list.stream().map(mapper::toDTO).toList());
    }

    @WithTransaction
    public Uni<AccountingEntryTypeDTO> create(final AccountingEntryTypeCreateOrUpdateDTO dto, final String userId) {
        Objects.requireNonNull(dto);
        Objects.requireNonNull(userId);
        return auditSessionContext.setUserId(userId)
                .chain(() -> {
                    final AccountingEntryType entity = mapper.toEntity(dto);
                    return repository.persist(entity)
                            .map(mapper::toDTO);
                });
    }

    @WithTransaction
    public Uni<AccountingEntryTypeDTO> update(final UUID id, final AccountingEntryTypeCreateOrUpdateDTO dto, final String userId) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(dto);
        Objects.requireNonNull(userId);
        return auditSessionContext.setUserId(userId)
                .chain(() -> repository.findById(id)
                        .onItem().ifNull().failWith(() -> new NotFoundException("Entry type not found: " + id))
                        .invoke(existing -> {
                            existing.setCode(dto.code());
                            existing.setTransactionType(dto.transactionType());
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
