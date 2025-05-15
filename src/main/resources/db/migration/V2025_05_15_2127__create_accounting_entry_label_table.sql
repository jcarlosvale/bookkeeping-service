create table accounting_entry_label
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code        varchar(50)  not null unique,
    description varchar(255)
);