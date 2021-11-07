CREATE TABLE role(
    id BIGINT NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO role (name) VALUES ('READ_WRITE');