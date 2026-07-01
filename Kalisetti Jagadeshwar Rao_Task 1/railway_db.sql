CREATE DATABASE IF NOT EXISTS railway_db;
USE railway_db;

CREATE TABLE IF NOT EXISTS users (
    id          INT AUTO_INCREMENT PRIMARY KEY,  
    login_id    VARCHAR(30)  NOT NULL UNIQUE,  
    password    VARCHAR(30)  NOT NULL,          
    full_name   VARCHAR(60)  NOT NULL,           
    role        VARCHAR(30)  DEFAULT 'Agent'    
);

INSERT INTO users (login_id, password, full_name, role) VALUES
('admin',   'admin123', 'Admin User',       'Administrator'),
('agent1',  'pass123',  'Ravi Kumar',       'Booking Agent'),
('manager', 'mgr2024',  'Sunita Verma',     'Station Manager');

CREATE TABLE IF NOT EXISTS trains (
    train_no    VARCHAR(10)  PRIMARY KEY,        -- Train number (e.g., 12301)
    train_name  VARCHAR(100) NOT NULL,           -- Full train name
    from_station VARCHAR(60) NOT NULL,           -- Origin station
    to_station  VARCHAR(60)  NOT NULL            -- Destination station
);

INSERT INTO trains (train_no, train_name, from_station, to_station) VALUES
('12301', 'Howrah Rajdhani Express',    'Howrah',      'New Delhi'),
('12302', 'New Delhi Rajdhani Express', 'New Delhi',   'Howrah'),
('12951', 'Mumbai Rajdhani Express',    'Mumbai',      'New Delhi'),
('12952', 'New Delhi Mumbai Rajdhani',  'New Delhi',   'Mumbai'),
('12621', 'Tamil Nadu Express',         'New Delhi',   'Chennai'),
('12622', 'Tamil Nadu Express',         'Chennai',     'New Delhi'),
('12001', 'Bhopal Shatabdi Express',    'New Delhi',   'Bhopal'),
('12627', 'Karnataka Express',          'Bangalore',   'New Delhi'),
('12628', 'Karnataka Express',          'New Delhi',   'Bangalore'),
('12859', 'Gitanjali Express',          'Howrah',      'Mumbai CSMT');

CREATE TABLE IF NOT EXISTS reservations (
    id              INT AUTO_INCREMENT PRIMARY KEY,  
    pnr             VARCHAR(12)  NOT NULL UNIQUE,    
    passenger_name  VARCHAR(60)  NOT NULL,           
    age             INT          NOT NULL,           
    gender          VARCHAR(10)  NOT NULL,           
    contact         VARCHAR(15)  NOT NULL,           
    train_no        VARCHAR(10)  NOT NULL,           
    train_name      VARCHAR(100) NOT NULL,          
    class_type      VARCHAR(5)   NOT NULL,           
    seat_preference VARCHAR(20)  DEFAULT 'None',     
    journey_date    DATE         NOT NULL,           
    quota           VARCHAR(5)   DEFAULT 'GN',       
    from_station    VARCHAR(60)  NOT NULL,           
    to_station      VARCHAR(60)  NOT NULL,           
    special_request VARCHAR(200) DEFAULT '',         
    booking_date    DATE         NOT NULL,           
    status          VARCHAR(15)  DEFAULT 'Confirmed' 
);

INSERT INTO reservations (
    pnr, passenger_name, age, gender, contact,
    train_no, train_name, class_type, seat_preference,
    journey_date, quota, from_station, to_station,
    booking_date, status
) VALUES (
    'AB12345678', 'Rahul Sharma', 28, 'Male', '9876543210',
    '12301', 'Howrah Rajdhani Express', '3A', 'LB',
    '2025-06-15', 'GN', 'New Delhi (NDLS)', 'Howrah (HWH)',
    CURDATE(), 'Confirmed'
);