package com.bookkeeping.application.dto;

import java.util.UUID;

public record AccountingEntryLabelDTO(
    UUID id,
    String code,
    String description
) { }
