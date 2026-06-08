-- ============================================================
-- YatraAir Flight Booking System - Database Schema
-- Run this script in MySQL to create the database and tables
-- ============================================================

CREATE DATABASE IF NOT EXISTS yatraair;
USE yatraair;

-- Drop tables in reverse order of dependencies to avoid constraint violations
DROP TABLE IF EXISTS support_messages;
DROP TABLE IF EXISTS support_tickets;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS flights;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS locations;

-- ============================================================
-- USERS TABLE
-- Stores passenger / user account information
-- ============================================================
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- FLIGHTS TABLE
-- Stores details of available flights
-- ============================================================
CREATE TABLE flights (
    flight_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_code VARCHAR(10) NOT NULL UNIQUE,
    source VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    departure_date VARCHAR(20) NOT NULL,
    departure_time VARCHAR(10) NOT NULL,
    available_seats INT NOT NULL,
    price DOUBLE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
);

-- ============================================================
-- BOOKINGS TABLE
-- Stores bookings made by users on flights
-- ============================================================
CREATE TABLE bookings (
    booking_id VARCHAR(10) PRIMARY KEY,
    user_id INT NOT NULL,
    flight_id INT NOT NULL,
    passenger_name VARCHAR(100) NOT NULL,
    seat_number VARCHAR(5) NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DOUBLE NOT NULL,
    booking_status VARCHAR(20) NOT NULL DEFAULT 'CONFIRMED',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE CASCADE
);

-- ============================================================
-- PAYMENTS TABLE
-- Stores transactional payment logs for bookings
-- ============================================================
CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id VARCHAR(10) NOT NULL,
    amount DOUBLE NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE
);

-- ============================================================
-- LOCATIONS TABLE
-- Stores valid airports / cities for flight searches
-- ============================================================
CREATE TABLE locations (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    city_name VARCHAR(50) NOT NULL,
    airport_name VARCHAR(100) NOT NULL,
    city_code VARCHAR(5) NOT NULL UNIQUE
);

-- ============================================================
-- SUPPORT TICKETS TABLE
-- ============================================================
CREATE TABLE support_tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    subject VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    status VARCHAR(50) DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ============================================================
-- SUPPORT MESSAGES TABLE
-- ============================================================
CREATE TABLE support_messages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT NULL,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    sender VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- ============================================================
-- SEED SAMPLE DATA
-- ============================================================

-- Seed Users (default password is 'password123')
INSERT INTO users (user_id, full_name, email, password, phone) VALUES
(1, 'Gaurav Chandra', 'gaurav.chandra@gmail.com', 'password123', '+977 9812345678'),
(2, 'Aryan Shah', 'aryan.shah@gmail.com', 'password123', '+977 9876543210');

-- Seed Locations
INSERT INTO locations (location_id, city_name, airport_name, city_code) VALUES
(1, 'Kathmandu', 'Tribhuvan International Airport', 'KTM'),
(2, 'Pokhara', 'Pokhara International Airport', 'PKR'),
(3, 'Biratnagar', 'Biratnagar Airport', 'BIR'),
(4, 'Bhairahawa', 'Gautam Buddha International Airport', 'BWA'),
(5, 'Nepalgunj', 'Nepalgunj Airport', 'KEP'),
(6, 'Lukla', 'Tenzing-Hillary Airport', 'LUA');

-- Seed Flights
INSERT INTO flights (flight_id, flight_code, source, destination, departure_date, departure_time, available_seats, price, status) VALUES
(1, 'YS101', 'Kathmandu (KTM)', 'Pokhara (PKR)', '28 APR 2026', '10:00AM', 10, 5000.0, 'ACTIVE'),
(2, 'YS205', 'Pokhara (PKR)', 'Kathmandu (KTM)', '15 MAY 2026', '02:30PM', 15, 5500.0, 'ACTIVE'),
(3, 'YS310', 'Kathmandu (KTM)', 'Biratnagar (BIR)', '10 JUN 2026', '08:00AM', 20, 4500.0, 'ACTIVE'),
(4, 'YS420', 'Kathmandu (KTM)', 'Lukla (LUA)', '20 JUN 2026', '06:00AM', 5, 8000.0, 'ACTIVE'),
(5, 'YS502', 'Kathmandu (KTM)', 'Bhairahawa (BWA)', '25 JUN 2026', '11:15AM', 18, 4800.0, 'ACTIVE');

-- Seed Bookings
INSERT INTO bookings (booking_id, user_id, flight_id, passenger_name, seat_number, booking_date, amount, booking_status) VALUES
('BK001', 1, 1, 'Gaurav Chandra', 'A2', '2026-04-01 10:00:00', 5000.0, 'CONFIRMED'),
('BK002', 1, 2, 'Gaurav Chandra', 'B5', '2026-04-10 14:30:00', 5500.0, 'CONFIRMED'),
('BK003', 1, 3, 'Gaurav Chandra', 'C1', '2026-03-01 08:00:00', 4500.0, 'CANCELLED'),
('BK004', 1, 4, 'Gaurav Chandra', 'D3', '2026-04-15 06:00:00', 8000.0, 'CONFIRMED');

-- Seed Payments
INSERT INTO payments (payment_id, booking_id, amount, payment_method, payment_date) VALUES
(1, 'BK001', 5000.0, 'E-SEWA', '2026-04-01 10:05:00'),
(2, 'BK002', 5500.0, 'KHALTI', '2026-04-10 14:35:00'),
(3, 'BK004', 8000.0, 'CREDIT_CARD', '2026-04-15 06:05:00');
