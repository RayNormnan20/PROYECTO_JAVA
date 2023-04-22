CREATE SCHEMA proyecto;
USE proyecto;

/*============================================*/

CREATE TABLE productos_electronicos (
  idPro INT AUTO_INCREMENT PRIMARY KEY,
  modelo VARCHAR(50),
  potencia INT
);
CREATE TABLE productos_alimenticios (
  idProAli INT AUTO_INCREMENT PRIMARY KEY,
  fecha_vencimiento varchar(12),
  pais_origen VARCHAR(50)
);
CREATE TABLE clientes (
  idCliente INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50),
  apellido VARCHAR(50),
  correo VARCHAR(50),
  direccion VARCHAR(100)
);
CREATE TABLE productos_base (
  idPro INT AUTO_INCREMENT PRIMARY KEY,
  nombre varchar (50),
  idElectronicos INT,
  idAlimenti INT,
  descrip VARCHAR(50),
  precio FLOAT(10,2),
  stock INT,
  CONSTRAINT relac_prod_elec FOREIGN KEY (idElectronicos) REFERENCES productos_electronicos(idPro),
  CONSTRAINT relac_prod_alim FOREIGN KEY (idAlimenti) REFERENCES productos_alimenticios(idProAli)
);

CREATE TABLE facturas (
  idFactura INT AUTO_INCREMENT PRIMARY KEY,
  idCliente INT,
  subtotal FLOAT(10,2),
  total FLOAT(10,2),
  igv FLOAT(10,2),
  fecha varchar(12),
  CONSTRAINT relac_fact_cliente FOREIGN KEY (idCliente) REFERENCES clientes (idCliente)
);

CREATE TABLE detalle_factura (
  idFactura INT,
  idProducto INT,
  cantidad INT,
  precioUnitario FLOAT(10,2),
  PRIMARY KEY (idFactura, idProducto),
  CONSTRAINT relac_det_factura FOREIGN KEY (idFactura) REFERENCES facturas (idFactura),
  CONSTRAINT relac_det_producto FOREIGN KEY (idProducto) REFERENCES productos_base (idPro)
);

INSERT INTO productos_electronicos (modelo, potencia) VALUES
('Samsung Galaxy', 3500),
('Apple iPhone', 2750),
('Sony Bravia', 150),
('Bose SoundLink', 300),
('Dell XPS', 500),
('HP Spectre', 425),
('LG OLED', 125),
('Sonos Beam', 80),
('Microsoft Xbox', 200),
('Logitech G915', 5);

INSERT INTO productos_alimenticios (fecha_vencimiento, pais_origen) VALUES
('2023-05-31', 'México'),
('2024-01-15', 'Argentina'),
('2023-06-30', 'Estados Unidos'),
('2024-03-01', 'Italia'),
('2023-12-31', 'Colombia'),
('2023-09-30', 'Brasil'),
('2023-10-15', 'España'),
('2023-08-31', 'Chile'),
('2024-04-01', 'Francia'),
('2023-11-30', 'Perú');

INSERT INTO clientes (nombre, apellido, correo, direccion) VALUES
('Juan', 'Pérez', 'juan.perez@example.com', 'Calle 123, Ciudad'),
('María', 'González', 'maria.gonzalez@example.com', 'Avenida 456, Pueblo'),
('Pedro', 'Martínez', 'pedro.martinez@example.com', 'Boulevard 789, Villa'),
('Ana', 'García', 'ana.garcia@example.com', 'Plaza 456, Colonia'),
('Luis', 'Hernández', 'luis.hernandez@example.com', 'Callejón 23, Aldea'),
('Rosa', 'Rodríguez', 'rosa.rodriguez@example.com', 'Carretera 456, Poblado'),
('Jorge', 'Sánchez', 'jorge.sanchez@example.com', 'Calle 789, Ranchería'),
('Fernanda', 'López', 'fernanda.lopez@example.com', 'Avenida 456, Barrio'),
('Daniel', 'Díaz', 'daniel.diaz@example.com', 'Calle 123, Colonia'),
('Paola', 'Ramos', 'paola.ramos@example.com', 'Calle 456, Pueblo');

INSERT INTO productos_base (nombre, idElectronicos, idAlimenti, descrip, precio, stock) VALUES
('Samsung Galaxy', 1, NULL, 'Smartphone con pantalla OLED', 800.00, 50),
('Apple iPhone', 2, NULL, 'Smartphone con procesador A14', 1000.00, 30),
('Sony Bravia', 3, NULL, 'Televisor con resolución 4K', 1500.00, 20),
('Bose SoundLink', 4, NULL, 'Altavoz Bluetooth portátil', 250.00, 75),
('Dell XPS', 5, NULL, 'Laptop con procesador i7', 1500.00, 15),
('HP Spectre', 6, NULL, 'Laptop con pantalla táctil', 1200.00, 25),
('LG OLED', 7, NULL, 'Televisor con HDR y AI ThinQ', 2000.00, 10),
('Sonos Beam', 8, NULL, 'Barra de sonido con Alexa', 400.00, 40),
('Microsoft Xbox', 9, NULL, 'Consola de videojuegos', 500.00, 50),
('Logitech G915', 10, NULL, 'Teclado mecánico inalámbrico', 200.00, 100),
('Leche condensada', NULL, 11, 'Lata de 400 gramos', 3.50, 100),
('Yogurt natural', NULL, 12, 'Envase de 500 gramos', 2.00, 200),
('Manzanas Fuji', NULL, 13, 'Bolsa de 1 kg', 4.00, 50),
('Harina de trigo', NULL, 14, 'Paquete de 1 kg', 1.50, 150),
('Pasta de tomate', NULL, 15, 'Lata de 400 gramos', 1.00, 100),
('Atún enlatado', NULL, 16, 'Lata de 200 gramos', 2.50, 75),
('Galletas de avena', NULL, 17, 'Paquete de 250 gramos', 3.00, 100),
('Café molido', NULL, 18, 'Bolsa de 500 gramos', 7.00, 30),
('Chocolate amargo', NULL, 19, 'Barra de 100 gramos', 2.00, 50)



































/*
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_factura`(
	IN id_cliente INT,
    IN subtotal FLOAT(10,2),
    IN igv FLOAT(10,2),
    IN fecha VARCHAR(12)

)
BEGIN
	DECLARE total_factura FLOAT(10,2);
    DECLARE id_factura INT;
        SET total_factura = subtotal + (subtotal * igv / 100);
        INSERT INTO facturas (idCliente, subtotal, total, igv, fecha)
        VALUES (id_cliente, subtotal, total_factura, igv, fecha);
         SET id_factura = LAST_INSERT_ID();
         SELECT id_factura AS 'ID Factura Creada';

END

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_detalle_factura`(
	IN id_factura INT,
    IN id_producto INT,
    IN cantidad INT,
    IN precio_unitario FLOAT(10,2)
)
BEGIN
	DECLARE subtotal FLOAT(10,2);
    
    SET subtotal = cantidad * precio_unitario;
    
    INSERT INTO detalle_factura (idFactura, idProducto, cantidad, precioUnitario)
    VALUES (id_factura, id_producto, cantidad, precio_unitario);
    
    UPDATE facturas SET subtotal = subtotal + subtotal, total = total + subtotal WHERE idFactura = id_factura;
END

*/

/*
SELECT f.idFactura, c.nombre, c.apellido, c.correo, c.direccion, f.total, f.igv, f.fecha, p.nombre, df.cantidad, df.precioUnitario, df.subtotal
FROM facturas f
JOIN clientes c ON f.idCliente = c.idCliente
JOIN detalle_factura df ON f.idFactura = df.idFactura
JOIN productos_base p ON df.idProducto = p.idPro
WHERE f.idFactura = 1;

CREATE PROCEDURE insertar_factura (
  IN cliente_id INT,
  IN factura_total FLOAT(10,2),
  IN factura_igv FLOAT(10,2),
  IN factura_fecha varchar(12)
)
BEGIN
  INSERT INTO facturas (idCliente, total, igv, fecha)
  VALUES (cliente_id, factura_total, factura_igv, factura_fecha)
END;

CREATE PROCEDURE insertar_detalle_factura (
  IN factura_id INT,
  IN producto_id INT,
  IN cantidad INT,
  IN precio_unitario FLOAT(10,2),
  IN subtotal FLOAT(10,2)
)
BEGIN
  INSERT INTO detalle_factura (idFactura, idProducto, cantidad, precioUnitario, subtotal)
  VALUES (factura_id, producto_id, cantidad, precio_unitario, subtotal)
END;
*/
















/*

INSERT INTO productos_electronicos (modelo, potencia)
VALUES ('Samsung Galaxy S21', 2000),
       ('iPhone 13 Pro', 2500),
       ('Sony Bravia 55"', 1500);

INSERT INTO productos_alimenticios (fecha_vencimiento, pais_origen)
VALUES ('2023-06-30', 'Perú'),
       ('2022-12-31', 'Colombia'),
       ('2024-03-15', 'Ecuador');

INSERT INTO clientes (nombre, apellido, correo, direccion)
VALUES ('Juan', 'Pérez', 'juan.perez@gmail.com', 'Av. Los Pinos 123'),
       ('María', 'Gómez', 'maria.gomez@hotmail.com', 'Calle Las Flores 456');

INSERT INTO productos_base (nombre, idElectronicos, idAlimenti, descrip, precio, stock)
VALUES ('Samsung Galaxy S21', 1, NULL, 'Teléfono móvil', 1299.99, 10),
       ('iPhone 13 Pro', 2, NULL, 'Teléfono móvil', 1599.99, 5),
       ('Sony Bravia 55"', 3, NULL, 'Televisor', 1899.99, 3),
       ('Café Juan Valdez', NULL, 1, 'Café molido', 12.99, 100),
       ('Chocolate Milka', NULL, 2, 'Tableta de chocolate', 3.99, 50),
       ('Atún Isabel', NULL, 3, 'Lata de atún', 1.99, 20);

INSERT INTO facturas (idCliente, total, igv, fecha) VALUES (1, 1200.00, 216.00, '2023-04-15');
INSERT INTO facturas (idCliente, total, igv, fecha) VALUES (2, 50.00, 9.00, '2023-04-14');


INSERT INTO detalle_factura (idFactura, idProducto, cantidad, precioUnitario, subtotal) VALUES (1, 1, 2, 1000.00, 2000.00);
INSERT INTO detalle_factura (idFactura, idProducto, cantidad, precioUnitario, subtotal) VALUES (2, 2, 10, 5.00, 50.00);

INSERT INTO detalle_factura (idFactura, idProducto, cantidad, precioUnitario, subtotal)
VALUES (1, 1, 1, 1299.99, 1299.99),
       (1, 2, 1, 1599.99, 1599.99),
       (1, 4, 2, 12.99, 25.98);
/*




