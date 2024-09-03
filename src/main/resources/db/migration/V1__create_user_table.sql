-- V1__Create_user_and_address_tables.sql

CREATE TABLE address (
    id VARCHAR(36) PRIMARY KEY,
    cep VARCHAR(9) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    complemento VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_nascimento VARCHAR(50) NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    role VARCHAR (10) NOT NULL,
    avatar_url VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    address_id VARCHAR(36) REFERENCES address(id)
);
