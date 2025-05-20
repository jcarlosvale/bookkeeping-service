create table accounting_entry_type
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code             varchar(50)  not null unique,
    transaction_type varchar(10)  not null, -- INCOME or EXPENSE
    description   varchar(255) not null
);
