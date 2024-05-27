CREATE DATABASE alcaride_bd;

USE alcaride_bd;

CREATE TABLE Usuarios (
    id_Usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
    apellido1 VARCHAR(50) NOT NULL,
	apellido2 VARCHAR(50),
    telefono INT NOT NULL,
    email VARCHAR(50) NOT NULL,
    dni VARCHAR(10) NOT NULL,
    carnet VARCHAR(2) NOT NULL,
    direccion VARCHAR(100) NOT NULL
);

CREATE TABLE Login (
    id_Login INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_Usuario INT NOT NULL,
    nombre_Usuario VARCHAR(20) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    is_Admin BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_Usuario) REFERENCES Usuarios(id_Usuario)
);

CREATE TABLE Motocicletas (
	id_Moto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(10) NOT NULL,
    matricula VARCHAR(8) NOT NULL,
    cilindrada INT NOT NULL,
	tipo_Carnet VARCHAR(2) NOT NULL,
    marca VARCHAR(20) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    precio_Dia DECIMAL(5,2)
);

CREATE TABLE Reservas (
    id_Reserva INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_Moto INT NOT NULL,
    id_Cliente INT,
    id_Admin INT NOT NULL,
    fecha_Realiza DATE NOT NULL,
    fecha_Inicio DATE NOT NULL,
    fecha_Fin DATE NOT NULL,
    estado VARCHAR (15),
    FOREIGN KEY (id_Moto) REFERENCES Motocicletas(id_Moto),
    FOREIGN KEY (id_Admin) REFERENCES Login(id_Login),
    FOREIGN KEY (id_Cliente) REFERENCES Login(id_Login) ON DELETE SET NULL -- Añado para borrar usuarios pero conservar la reserva.
);

CREATE TABLE Gestiones (
    id_Admin INT NOT NULL,
    id_Moto INT NOT NULL,
    fecha_Gestion DATE,
    PRIMARY KEY (id_Admin, id_Moto, fecha_Gestion),
    FOREIGN KEY (id_Admin) REFERENCES Login(id_Login),
    FOREIGN KEY (id_Moto) REFERENCES Motocicletas(id_Moto)
);

INSERT INTO Usuarios (nombre, apellido1, apellido2, telefono, email, dni, carnet, direccion)
VALUES
('Daniel','Fernández', 'Sánchez', '123456789', 'administrador@alcaride.com', '12345678-M', 'A2', 'Rue del Percebe 1. 1º A'),
('Melani','Martinez', 'Sanz', '987654321', 'melani889@gmail.com', '76543210-S', 'A', 'Privet Drive 4, Little Whinging'),
('Jason','Vorhees', NULL, '555555555', 'jasonfriday13@gmail.com', '00542541-L', 'A2', 'Campamento Crystal Lake'),
('Sabrina','Spellman', NULL, '666666666', 'sabrina16@gmail.com', '54488564-G', 'A', '133 Collins Road Greendale, Massachusetts'),
('Stephen','King', NULL, '777777777', 'theking@gmail.com', '98856417-T', 'A2', 'Portland, Maine'),
('Charles','Lee', 'Ray', '444444444', 'goodboy@gmail.com', '36354787-J', 'A', '100 Lakeshore Drive Chicago, Illinois'),
('Frederick Charles','Krueger', NULL, '999999999', 'sweetdreams@gmail.com', '06878745-M', 'A', '1428 Elm Street Springwood, Ohio'),
('Uhtred','of Bebbanburg', NULL, '333333333', 'sajonkiller@gmail.com', '97488584-C', 'A2', 'Castillo de Bebamburgh, Northumbia'),
('Leon Scott','Kennedy', NULL, '222222222', 'kennedy@umbrellacorp.com', '55587463-S', 'A', '113 Lake Shore Drive, Raccoon City');

INSERT INTO Login (id_Usuario, nombre_Usuario, pass, is_Admin)
VALUES ('1', 'Admin', '1234', '1');

INSERT INTO Login (id_Usuario, nombre_Usuario, pass)
VALUES
('2', 'Granger', '4321'),
('3', 'JasonTheKiller', '1111'),
('4', 'Salem16', '2222'),
('5', 'KingSt', '3333'),
('6', 'Chucky', '4444'),
('7', 'Freddy', '5555'),
('8', 'UthredTheViking', '6666'),
('9', 'FuckNemesis', '7777');

INSERT INTO Motocicletas (tipo, matricula, cilindrada, tipo_Carnet, marca, modelo, anio, precio_Dia)
VALUES
('Custom', '7518-KTF', '1753', 'A', 'Harley Davidson', 'Road King', '2022', '224.95'),
('Custom', '9854-MNN', '903', 'A2', 'Kawasaki', 'Vulcan 900', '2015', '130.95'),
('Custom', '5541-KRT', '1768', 'A', 'Indian', 'Challenger Dark Horse', '2023', '245.95'),
('Custom', '6335-MRF', '471', 'A2', 'Honda', 'CMX500 Rebel', '2021', '95.95'),

('Turismo', '8824-KHT', '1238', 'A', 'BMW', 'R 1250 RT', '2022', '235.95'),
('Turismo', '2554-KGR', '1287', 'A', 'Yamaha', 'FJR 1300 AE', '2022', '220.95'),
('Turismo', '5481-LKR', '1883', 'A', 'Honda', 'Goldwing Tour', '2023', '245.95'),
('Turismo', '6893-JTW', '1047', 'A2', 'Suzuki', 'Vstrom 1050', '2020', '180.95'),

('Adventure', '2117-MYP', '1253', 'A', 'BMW', 'G 1250 GS Adventure', '2021', '205.95'),
('Adventure', '9685-JTR', '644', 'A2', 'Kawasaki', 'Versys 650', '2020', '110.95'),
('Adventure', '5571-MRH', '1088', 'A', 'Honda', 'Africa Twin', '2023', '210.95'),
('Adventure', '6893-MWS', '1263', 'A', 'KTM', '1290 Super Adventure', '2021', '245.95'),

('Naked', '2158-KSF', '689', 'A2', 'Yamaha', 'MT-07', '2023', '120.95'),
('Naked', '7239-KBC', '1301', 'A', 'KTM', 'Super Duke R 1290', '2022', '180.95'),
('Naked', '3395-HTY', '754', 'A2', 'Suzuki', 'GSX-S 750', '2016', '165.95'),
('Naked', '1447-JVX', '998', 'A', 'Honda', 'CB 1000 R', '2020', '210.95');

INSERT INTO Reservas (id_Moto, id_Cliente, id_Admin, fecha_Realiza, fecha_Inicio, fecha_Fin, estado)
VALUES
('14', '2', '1', '2024-02-15', '2024-02-25', '2024-02-28', 'Cancelada'),
('14', '2', '1', '2024-02-15', '2024-02-27', '2024-03-01', 'Validada'),
('6', '3', '1', '2024-03-13', '2024-03-19', '2024-03-24', 'Pendiente'),
('12', '8', '1', '2024-04-19', '2024-04-21', '2024-04-26', 'Validada'),
('10', '5', '1', '2024-04-21', '2024-04-23', '2024-04-29', 'Validada'),
('9', '7', '1', '2024-04-25', '2024-04-28', '2024-05-02', 'Validada');