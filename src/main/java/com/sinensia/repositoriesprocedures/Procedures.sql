-- -----------------------------------------------------

-- Usuarios

-- -----------------------------------------------------
DELIMITER $$
CREATE PROCEDURE get_usuario(IN nom VARCHAR(45), IN pass VARCHAR(64))
BEGIN
	SELECT * FROM usuarios WHERE nombre=nom AND password=pass;
END$$

DELIMITER $$
CREATE PROCEDURE add_usuario(IN nom VARCHAR(45),IN mail VARCHAR(80), IN pass VARCHAR(64), OUT idUsuario INT)
BEGIN
	INSERT INTO Usuarios(nombre, correo, password) VALUE (nom, mail, pass);
	set idUsuario = LAST_INSERT_ID();
END$$

DELIMITER $$
CREATE PROCEDURE modify_usuario(IN nom VARCHAR(45),IN mail VARCHAR(80), IN pass VARCHAR(64), IN id INT)
BEGIN
	UPDATE Usuarios SET nombre=nom, correo=mail, password=pass WHERE idUsuario=id;
END$$

DELIMITER $$
CREATE PROCEDURE remove_usuario(IN id INT)
BEGIN
	DELETE FROM Usuarios WHERE idUsuario=id;
END$$

-- -----------------------------------------------------

-- Movimientos

-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE get_movimientos(IN pidUsuario INT, IN pidCategory INT, IN pfecha DATE)
BEGIN
	SELECT * FROM Movimientos WHERE idUsuario=pidUsuario and idCategoria=pidCategory and fecha BETWEEN pfecha 
	AND LAST_DAY(pfecha) order by fecha asc;
END$$

DELIMITER $$
CREATE PROCEDURE add_movimiento(IN pimporte FLOAT(8,2),IN pfecha DATE, IN pidCategoria INT, IN pidUsuario INT, IN pnombre VARCHAR(45),
	OUT pidMovimiento INT)
BEGIN
	INSERT INTO Movimientos(importe, fecha, idCategoria, idUsuario, nombre) VALUE (pimporte,pfecha,pidCategoria,pidUsuario,pnombre);
	SET pidMovimiento = LAST_INSERT_ID();
END$$

DELIMITER $$
CREATE PROCEDURE remove_movimiento(IN pidMovimiento INT)
BEGIN
	DELETE FROM Movimientos WHERE idMovimientos=pidMovimiento;
END$$

DELIMITER $$
CREATE PROCEDURE modify_movimiento(IN pimporte FLOAT(8,2),IN pfecha DATE, IN pnombre VARCHAR(45), IN pidMovimiento INT)
BEGIN
	UPDATE Movimientos SET importe=pimporte, fecha=pfecha, nombre=pnombre WHERE idMovimientos=pidMovimiento;
END$$

DELIMITER $$
CREATE PROCEDURE remove_categoria_movimientos(IN pidCategoria INT, IN pidUsuario INT)
BEGIN
	DELETE FROM Movimientos WHERE idCategoria=pidCategoria AND idUsuario=pidUsuario;
END$$

DELIMITER $$
CREATE PROCEDURE remove_usuario_movimientos(IN pidUsuario INT)
BEGIN
	DELETE FROM Movimientos WHERE idUsuario=pidUsuario;
END$$

-- -----------------------------------------------------

-- Categorias

-- -----------------------------------------------------

DELIMITER $$
CREATE PROCEDURE get_categorias(IN pidUsuario INT)
BEGIN
	SELECT * FROM Categoriaspersonalizadas WHERE idUsuario=pidUsuario;
END$$

DELIMITER $$
CREATE PROCEDURE get_categorias_paginadas(IN pidUsuario INT, IN inicio INT, IN registros INT)
BEGIN
	SELECT * FROM categoriaspersonalizadas WHERE idUsuario=pidUsuario LIMIT inicio, registros;
END$$

DELIMITER $$
CREATE PROCEDURE get_categoriabyid(IN pidUsuario INT, IN pidCategoria INT)
BEGIN
	SELECT * FROM Categoriaspersonalizadas WHERE idUsuario=pidUsuario and idCategoria=pidCategoria;
END$$

DELIMITER $$
CREATE PROCEDURE add_categorias_inicio(IN pidUsuario INT, IN pnombre VARCHAR(45), IN ptipo CHAR(1), OUT pidCategoria INT)
BEGIN
	INSERT INTO Categoriaspersonalizadas(idUsuario, nombre, tipo) VALUE (pidUsuario,pnombre,ptipo);
	SET pidCategoria = LAST_INSERT_ID();
END$$

DELIMITER $$
CREATE PROCEDURE add_categoria(IN pidUsuario INT, IN pnombre VARCHAR(45), IN ptipo CHAR(1), OUT pidCategoria INT)
BEGIN
	INSERT INTO Categoriaspersonalizadas(idUsuario, nombre, tipo) VALUE (pidUsuario,pnombre,ptipo);
	SET pidCategoria = LAST_INSERT_ID();
END$$

DELIMITER $$
CREATE PROCEDURE modify_categoria(IN pnombre VARCHAR(45),IN ptipo CHAR(1), IN pidCategoria INT, IN pidUsuario INT)
BEGIN
	UPDATE Categoriaspersonalizadas SET nombre=pnombre, tipo=ptipo WHERE idCategoria=pidCategoria AND idUsuario=pidUsuario;
END$$

DELIMITER $$
CREATE PROCEDURE remove_categoria(IN pidCategoria INT, IN pidUsuario INT)
BEGIN
	DELETE FROM Categoriaspersonalizadas WHERE idCategoria=pidCategoria and idUsuario=pidUsuario;
END$$

DELIMITER $$
CREATE PROCEDURE remove_all_user(IN pidUsuario INT)
BEGIN
	DELETE FROM Categoriaspersonalizadas WHERE idUsuario=pidUsuario;
END$$

DELIMITER $$
CREATE PROCEDURE get_numero_categorias(IN pidUsuario INT)
BEGIN
	SELECT count(*) FROM Categoriaspersonalizadas WHERE idUsuario=pidUsuario;
END$$