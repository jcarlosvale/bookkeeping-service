package com.bookkeeping.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TransactionType {
    INCOME, EXPENSE
}
