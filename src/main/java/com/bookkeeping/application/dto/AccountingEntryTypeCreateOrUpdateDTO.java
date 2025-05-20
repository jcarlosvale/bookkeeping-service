package com.bookkeeping.application.dto;

import com.bookkeeping.domain.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountingEntryTypeCreateOrUpdateDTO(
    @NotBlank(message = "Código não pode estar vazio.")
    String code,

    @NotNull(message = "Tipo de transação não pode ser nulo.")
    TransactionType transactionType,

    @NotBlank(message = "Descrição não pode estar vazia.")
    String description
) {}
