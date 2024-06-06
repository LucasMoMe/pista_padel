CREATE DATABASE pistapadel;

USE pistapadel;

CREATE TABLE ADMINISTRADOR (
    usuario VARCHAR(50) PRIMARY KEY,
    contraseña VARCHAR(255) NOT NULL
);

INSERT INTO administrador(usuario, contraseña) VALUES ('admin', md5('admin'));

CREATE TABLE USUARIOS (
    correo VARCHAR(255) PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    socio BOOLEAN NOT NULL
);

INSERT INTO USUARIOS(correo, usuario, nombre, apellidos, contraseña, socio) VALUES ('admin', 'admin', 'admin', 'admin', md5('admin'), '1');

CREATE TABLE PISTA (
   codigo INT AUTO_INCREMENT PRIMARY KEY,
   disponibilidad BOOLEAN NOT NULL
);

CREATE TABLE RESERVAS (
   fk_correo VARCHAR(255),
   fk_codigo INT,
   dia DATE NOT NULL,
   hora TIME NOT NULL,
   pago BOOLEAN NOT NULL,
   FOREIGN KEY (fk_correo) REFERENCES USUARIOS(correo),
   FOREIGN KEY (fk_codigo) REFERENCES PISTA(codigo)
);
