package com.bookkeeping.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum VehicleStatus {
    IN_STOCK,
    FOR_SALE,
    SOLD,
    IN_MAINTENANCE,
    RESERVED,
    TEST_DRIVE,
    RETIRED,
    DELIVERED,
    AVAILABLE,
    PENDING_DOCUMENTS,
    IN_TRANSFER,
    DAMAGED,
    IN_REPAIR
}
