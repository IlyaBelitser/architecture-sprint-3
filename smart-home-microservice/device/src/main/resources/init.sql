CREATE TABLE IF NOT EXISTS device (
    id UUID PRIMARY KEY,
    device_type_id BIGINT NOT NULL,
    module_id UUID,
    scenario_id UUID,
    name VARCHAR,
    serial_number VARCHAR,
    current_value DOUBLE PRECISION,
    target_value DOUBLE PRECISION,
    status VARCHAR
);

CREATE TABLE IF NOT EXISTS device_type (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR,
    description VARCHAR
);


INSERT INTO device (id, device_type_id, module_id, scenario_id, name, serial_number)
VALUES ('a0ee6d1c-994e-4299-8452-7ea919119f8c', '1',
        '3fa85f64-5717-4562-b3fc-2c963f66afa6', '3fa85f64-5717-4562-b3fc-2c963f66afa6',
        'Kitchen', 'HD-1');

INSERT INTO device_type (id, name, description)
VALUES (1, 'heating', 'Тип устройства отопительной системы дома'),
       (2, 'dvr', 'Тип устройства видеорегистратора'),
       (3, 'lighting', 'Тип устройства освещения в доме'),
       (4, 'gate', 'Тип устройства ворот дома')
ON CONFLICT DO NOTHING
