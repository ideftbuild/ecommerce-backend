-- Enable UUID generation
-- Ensure pgcrypto is available for gen_random_uuid()
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Create the roles table if it doesn't exist
CREATE TABLE IF NOT EXISTS roles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMPTZ(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMPTZ(6),
    is_system BOOLEAN NOT NULL DEFAULT FALSE
);

ALTER TABLE roles
ALTER COLUMN id
SET DEFAULT gen_random_uuid();

-- Seed default system roles
INSERT INTO roles (
    name,
    description,
    is_system,
    created_at,
    updated_at
)
VALUES
(
    'SUPER_ADMIN',
    'Has unrestricted access to all system features, configurations, and administrative operations.',
    TRUE,
    NOW(),
    NOW()
),
(
    'ADMIN',
    'Has full access to application features and resources within the assigned scope.',
    TRUE,
    NOW(),
    NOW()
),
(
    'USER',
    'Has access to standard features based on assigned permissions.',
    TRUE,
    NOW(),
    NOW()
)

ON CONFLICT (name) DO NOTHING;
