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


