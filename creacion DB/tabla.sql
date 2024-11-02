-- Schema: empresa_xyz
CREATE SCHEMA IF NOT EXISTS empresa_xyz;

-- Table: inventario
CREATE TABLE IF NOT EXISTS empresa_xyz.inventario (
    nombre VARCHAR(250) NOT NULL,
    descripcion VARCHAR(500),
    precio_ven DOUBLE NOT NULL,
    unidades_disponibles INT NOT NULL,
    PRIMARY KEY (nombre)
);

-- Table: ventas
CREATE TABLE IF NOT EXISTS empresa_xyz.ventas (
    fecha_hora DATETIME NOT NULL,
    producto VARCHAR(250) NOT NULL,
    cantidad INT NOT NULL,
    precio_uni DOUBLE NOT NULL,
    precio_total DOUBLE NOT NULL,
    FOREIGN KEY (producto) REFERENCES empresa_xyz.inventario(nombre)
);
