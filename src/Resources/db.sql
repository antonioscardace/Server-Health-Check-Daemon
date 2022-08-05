CREATE DATABASE IF NOT EXISTS `server_check`;
USE `server_check`;

CREATE TABLE IF NOT EXISTS `servers` (
    `address` VARCHAR(128) NOT NULL,
    `address_type` ENUM('url', 'ip') NOT NULL,
    PRIMARY KEY(`address`)
);

CREATE TABLE IF NOT EXISTS `accounts` (
    `contact` VARCHAR(64) NOT NULL,
    `contact_type` ENUM('email', 'telegram') NOT NULL,
    PRIMARY KEY(`contact`)
);

CREATE TABLE IF NOT EXISTS `observes` (
    `contact` VARCHAR(64) NOT NULL,
    `address` VARCHAR(128) NOT NULL,
    PRIMARY KEY(`contact`, `address`),
    FOREIGN KEY (`contact`) REFERENCES accounts(`contact`),
    FOREIGN KEY (`address`) REFERENCES servers(`address`)
);
