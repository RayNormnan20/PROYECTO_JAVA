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










