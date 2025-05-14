-- Função de auditoria genérica
create or replace function audit_trigger_fn() returns trigger as $$
begin
insert into audit_log (
    id,
    table_name,
    operation_type,
    record_id,
    old_data,
    new_data,
    changed_by,
    changed_at
)
values (
           gen_random_uuid(),
           TG_TABLE_NAME,
           TG_OP,
           case when TG_OP = 'DELETE' then OLD.id else NEW.id end,
           case when TG_OP in ('UPDATE', 'DELETE') then to_jsonb(OLD) else null end,
           case when TG_OP in ('INSERT', 'UPDATE') then to_jsonb(NEW) else null end,
           current_setting('audit.user_id', true)::uuid,
           current_timestamp
       );
return null;
end;
$$ language plpgsql;
