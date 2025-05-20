package com.bookkeeping.application.dto;

import com.bookkeeping.domain.enums.TransactionType;

import java.util.UUID;

public record AccountingEntryTypeDTO(
    UUID id,
    String code,
    TransactionType transactionType,
    String description
) {}
