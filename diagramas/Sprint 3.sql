CREATE TABLE USERS(
    id NUMBER PRIMARY KEY,
    username VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL UNIQUE,
    cnpj VARCHAR2(14) NOT NULL UNIQUE,
    password VARCHAR2(100) NOT NULL
);


CREATE TABLE NAVIOS(
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    capacidade_carga VARCHAR2(50) NOT NULL,
    users_id NUMBER,
    CONSTRAINT fk_users_navios FOREIGN KEY (users_id) REFERENCES USERS(id)
);


INSERT INTO USERS (id, username, email, cnpj, password) 
VALUES (1, 'joaodasilva', 'joao@email.com', '12345678000195', 'senha123');

INSERT INTO USERS (id, username, email, cnpj, password) 
VALUES (2, 'mariaferreira', 'maria@email.com', '98765432000167', 'senha456');


INSERT INTO NAVIOS (id, nome, capacidade_carga, users_id) 
VALUES (1, 'Navio Mercante', '5000 toneladas', 1);

INSERT INTO NAVIOS (id, nome, capacidade_carga, users_id) 
VALUES (2, 'Navio Tanque', '7000 toneladas', 2);

INSERT INTO NAVIOS (id, nome, capacidade_carga, users_id) 
VALUES (3, 'Navio de Carga', '8000 toneladas', 1);

DROP TABLE USERS;
DROP TABLE NAVIOS;

CREATE SEQUENCE users_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
    
CREATE SEQUENCE navios_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;    

SELECT * FROM USERS

ALTER TABLE NAVIOS DROP CONSTRAINT FK_USERS_NAVIOS;

ALTER TABLE NAVIOS
ADD CONSTRAINT FK_USERS_NAVIOS
FOREIGN KEY (users_id) REFERENCES USERS(id)
ON DELETE CASCADE;

