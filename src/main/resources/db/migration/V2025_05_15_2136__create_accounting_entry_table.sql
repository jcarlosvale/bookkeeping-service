create table accounting_entry
(
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    entity_type    varchar(30)    not null, -- VEHICLE, CHEQUE, etc.
    entity_id      uuid           not null,
    entry_type_id  uuid           not null references accounting_entry_type (id),
    entry_label_id uuid           not null references accounting_entry_label (id),
    entry_date     date           not null,
    amount         numeric(15, 2) not null,
    description    varchar(255),
    status         varchar(20)    not null  -- TO_APPROVE, APPROVED, REJECTED
);
