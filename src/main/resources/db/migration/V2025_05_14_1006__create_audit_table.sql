-- Criação da tabela de auditoria
create table audit_log (
                           id uuid primary key,
                           table_name varchar(100) not null,
                           operation_type varchar(10) not null, -- INSERT, UPDATE, DELETE
                           record_id uuid not null,
                           old_data jsonb,
                           new_data jsonb,
                           changed_by uuid,
                           changed_at timestamp not null
);
