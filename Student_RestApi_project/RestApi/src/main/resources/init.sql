-- =============================================
-- PostgreSQL Setup Script for Student Management
-- =============================================

-- Run this as a superuser (postgres) to create the database.
-- Then connect to 'studentdb' and run the table + data scripts.

-- Step 1: Create the database (run from psql or pgAdmin as superuser)
-- CREATE DATABASE studentdb;

-- Step 2: Connect to the database
-- \c studentdb

-- Step 3: Create the students table
CREATE TABLE IF NOT EXISTS students (
    roll    INTEGER      PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    email   VARCHAR(150) NOT NULL UNIQUE,
    marks   REAL         CHECK (marks >= 0 AND marks <= 100)
);

-- Step 4: Insert sample data (matching the original ArrayList)
INSERT INTO students (roll, name, email, marks) VALUES
    (1, 'tarun',  'abc@123',       23.20),
    (2, 'mukesh', 'dgs@123.com',   93.89),
    (3, 'rahul',  'eyb@123.com',   83.89),
    (4, 'shruti', 'kdd@123.com',   13.89)
ON CONFLICT (roll) DO NOTHING;
