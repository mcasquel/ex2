CREATE DATABASE IF NOT EXISTS Pais;

USE Pais;

CREATE TABLE IF NOT EXISTS paises (
    id int auto_increment primary key not null,
    nome varchar(100),
    populacao bigint,
    area decimal(15,2) 
    );
    

INSERT INTO paises(nome,populacao,area) VALUES('Brasil',225000000,44587.00);
INSERT INTO paises(nome,populacao,area) VALUES('China',45200000000,24577.00);
INSERT INTO paises(nome,populacao,area) VALUES('Canada',2850000,49887.00);
INSERT INTO paises(nome,populacao,area) VALUES('Argentina',40000000,57000.00);
INSERT INTO paises(nome,populacao,area) VALUES('Bolivia',48700000,33478.00);

SELECT * FROM paises;

drop table paises;