CREATE TABLE plano_requests (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    plan_id VARCHAR(36),
    status VARCHAR(255),
    solicitado BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_plan FOREIGN KEY (plan_id) REFERENCES planos(id)
);
