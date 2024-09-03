-- V4__Create_planos_table.sql

CREATE TABLE planos (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    megas VARCHAR(255),
    description TEXT,
    plano VARCHAR(255),
    servicos TEXT
);
