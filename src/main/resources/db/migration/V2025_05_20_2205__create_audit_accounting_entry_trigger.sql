create trigger audit_accounting_entry_trigger
    after insert or update or delete on accounting_entry
    for each row execute function audit_trigger_fn();
