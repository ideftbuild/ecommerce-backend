-- Enable UUID generation
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Create the permissions table if it doesn't exist
CREATE TABLE IF NOT EXISTS permissions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description TEXT NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Ensure the id column generates UUIDs by default
ALTER TABLE permissions
ALTER COLUMN id
SET DEFAULT gen_random_uuid();

-- Seed default permissions
INSERT INTO permissions (name, description) VALUES

-- Users
('user:read', 'View all users and their profile information.'),
('user:create', 'Create new user accounts.'),
('user:update', 'Update user account details, including profile information and account settings.'),
('user:delete', 'Delete user accounts from the system.'),
('user:restore', 'Restore previously deleted user accounts.'),

-- Roles
('role:read', 'View all roles and their assigned permissions.'),
('role:create', 'Create new roles for managing user access.'),
('role:update', 'Update role details and assigned permissions.'),
('role:delete', 'Delete roles from the system.'),
('role:restore', 'Restore previously deleted roles.'),
('role:assign', 'Assign roles to users.'),
('role:unassign', 'Remove assigned roles from users.'),

-- Permissions
('permission:read', 'View all available permissions and their descriptions.'),
('permission:assign', 'Assign permissions to roles.'),
('permission:unassign', 'Remove assigned permissions from roles.'),

-- Products
('product:read', 'View all products and their details.'),
('product:create', 'Create new products.'),
('product:update', 'Update product details, pricing, and availability.'),
('product:delete', 'Delete products from the catalog.'),
('product:restore', 'Restore previously deleted products.'),

-- Categories
('category:read', 'View all product categories.'),
('category:create', 'Create new product categories.'),
('category:update', 'Update product category details.'),
('category:delete', 'Delete product categories.'),
('category:restore', 'Restore previously deleted product categories.'),

-- Inventory
('inventory:read', 'View inventory levels and stock information.'),
('inventory:update', 'Update inventory quantities and stock records.'),

-- Orders
('order:read', 'View all customer orders and their details.'),
('order:create', 'Create new customer orders.'),
('order:update', 'Update order details and order status.'),
('order:cancel', 'Cancel customer orders.'),
('order:refund', 'Process refunds for customer orders.'),

-- Payments
('payment:read', 'View all payment transactions and payment details.'),
('payment:create', 'Record or initiate new payments.'),
('payment:update', 'Update payment details and transaction records.'),
('payment:refund', 'Process refunds for completed payments.'),

-- Shipping
('shipping:read', 'View all shipping records and delivery information.'),
('shipping:create', 'Create new shipping records for orders.'),
('shipping:update', 'Update shipping details and delivery status.'),
('shipping:delete', 'Delete shipping records from the system.'),
('shipping:restore', 'Restore previously deleted shipping records.'),

-- Addresses
('address:read', 'View all saved addresses.'),
('address:create', 'Create new addresses.'),
('address:update', 'Update existing address details.'),
('address:delete', 'Delete saved addresses.'),
('address:restore', 'Restore previously deleted addresses.'),

-- Cart
('cart:read', 'View shopping carts and their contents.'),
('cart:update', 'Update shopping cart contents.')

ON CONFLICT (name) DO NOTHING;
