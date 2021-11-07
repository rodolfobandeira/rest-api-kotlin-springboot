CREATE TABLE user (
    id bigint not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

INSERT INTO user (id, name, email) VALUES (1, 'Rodolfo Bandeira', 'example@example.com');
