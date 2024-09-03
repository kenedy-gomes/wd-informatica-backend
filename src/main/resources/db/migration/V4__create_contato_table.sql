CREATE TABLE contato (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nome VARCHAR(255),
    email TEXT,
    telefone VARCHAR(255),
    assunto VARCHAR(255),
    mensagem VARCHAR(255)
);
