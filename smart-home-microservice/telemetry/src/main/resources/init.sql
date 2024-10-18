CREATE TABLE IF NOT EXISTS telemetry (
    id UUID PRIMARY KEY,
    device_id UUID NOT NULL,
    received_at TIMESTAMP NOT NULL,
    data VARCHAR NOT NULL,
    ip_and_port VARCHAR NOT NULL
);
