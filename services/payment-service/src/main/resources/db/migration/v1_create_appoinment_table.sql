CREATE TABLE appointments (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    consultant_id BIGINT NOT NULL,
    appointment_datetime TIMESTAMP NOT NULL,
    status VARCHAR(30) NOT NULL,
    reason VARCHAR(500),
    notes VARCHAR(1000),
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ
);
