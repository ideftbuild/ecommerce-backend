-- Seed default role permissions

-- SUPER_ADMIN gets all permissions
INSERT INTO role_permissions (role_id, permission_id)
SELECT
    r.id,
    p.id
FROM roles r
CROSS JOIN permissions p
WHERE r.name = 'SUPER_ADMIN'
ON CONFLICT DO NOTHING;

-- ADMIN permissions
INSERT INTO role_permissions (role_id, permission_id)
SELECT
    r.id,
    p.id
FROM roles r
CROSS JOIN permissions p
WHERE r.name = 'ADMIN'
AND p.name IN (
    -- Users
    'user:read',
    'user:create',
    'user:update',
    'user:delete',
    'user:restore',

    -- Roles
    'role:read',
    'role:create',
    'role:update',
    'role:delete',
    'role:restore',
    'role:assign',
    'role:unassign',

    -- Permissions
    'permission:read',
    'permission:assign',
    'permission:unassign',

    -- Products
    'product:read',
    'product:create',
    'product:update',
    'product:delete',
    'product:restore',

    -- Categories
    'category:read',
    'category:create',
    'category:update',
    'category:delete',
    'category:restore',

    -- Inventory
    'inventory:read',
    'inventory:update',

    -- Orders
    'order:read',
    'order:create',
    'order:update',
    'order:cancel',
    'order:refund',

    -- Payments
    'payment:read',
    'payment:create',
    'payment:update',
    'payment:refund',

    -- Shipping
    'shipping:read',
    'shipping:create',
    'shipping:update',
    'shipping:delete',
    'shipping:restore',

    -- Addresses
    'address:read',
    'address:create',
    'address:update',
    'address:delete',
    'address:restore'
)
ON CONFLICT DO NOTHING;

-- USER permissions
INSERT INTO role_permissions (role_id, permission_id)
SELECT
    r.id,
    p.id
FROM roles r
JOIN permissions p ON p.name IN (
    'product:read',
    'category:read',
    'inventory:read',
    'order:read',
    'order:create',
    'cart:read',
    'cart:update',
    'address:read',
    'address:create',
    'address:update'
)
WHERE r.name = 'USER'
ON CONFLICT DO NOTHING;
