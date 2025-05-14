CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE vehicle (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         license_plate VARCHAR(20),
                         model VARCHAR(100) NOT NULL,
                         manufacturer VARCHAR(100),
                         chassis VARCHAR(100) UNIQUE,
                         vehicle_year INT,
                         purchase_date DATE NOT NULL,
                         purchase_value DECIMAL(15,2) NOT NULL,
                         sale_date DATE,
                         sale_value DECIMAL(15,2),
                         maintenance_cost DECIMAL(15,2),
                         last_maintenance DATE,
                         color VARCHAR(50),
                         engine VARCHAR(50),
                         fuel_type VARCHAR(50),
                         status VARCHAR(50)
);

