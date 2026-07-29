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
