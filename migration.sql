-- Enable UUID generation
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Create the permissions table if it doesn't exist
CREATE TABLE IF NOT EXISTS permissions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Ensure the id column generates UUIDs by default
ALTER TABLE permissions
ALTER COLUMN id
SET DEFAULT gen_random_uuid();

-- Seed default permissions
INSERT INTO permissions (name) VALUES
-- Users
('user:read'),
('user:create'),
('user:update'),
('user:delete'),

-- Roles
('role:read'),
('role:create'),
('role:update'),
('role:delete'),

-- Permissions
('permission:read'),

-- Products
('product:read'),
('product:create'),
('product:update'),
('product:delete'),

-- Categories
('category:read'),
('category:create'),
('category:update'),
('category:delete'),

-- Inventory
('inventory:read'),
('inventory:update'),

-- Orders
('order:read'),
('order:create'),
('order:update'),
('order:cancel'),
('order:refund'),

-- Payments
('payment:read'),
('payment:create'),
('payment:update'),
('payment:refund'),

-- Shipping
('shipping:read'),
('shipping:create'),
('shipping:update'),
('shipping:delete'),

-- Addresses
('address:read'),
('address:create'),
('address:update'),
('address:delete'),

-- Cart
('cart:read'),
('cart:update')

ON CONFLICT (name) DO NOTHING;
