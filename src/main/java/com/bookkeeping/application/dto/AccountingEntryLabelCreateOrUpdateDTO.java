package com.bookkeeping.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountingEntryLabelCreateOrUpdateDTO(

    @NotBlank(message = "Código não pode estar vazio(a).")
    @Size(max = 50, message = "Código deve ter no máximo 50 caracteres.")
    String code,

    String description
) {}
