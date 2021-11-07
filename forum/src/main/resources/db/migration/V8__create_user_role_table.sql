CREATE TABLE user_role(
    id BIGINT NOT NULL auto_increment,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id),
    FOREIGN KEY(role_id) REFERENCES role(id)
);

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);