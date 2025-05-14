create trigger audit_vehicle_trigger
    after insert or update or delete on vehicle
    for each row execute function audit_trigger_fn();
