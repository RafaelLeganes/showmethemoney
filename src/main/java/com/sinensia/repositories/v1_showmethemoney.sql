SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';



CREATE SCHEMA IF NOT EXISTS `showmethemoney` DEFAULT CHARACTER SET utf8 ;

USE `showmethemoney` ;



-- -----------------------------------------------------

-- Table `showmethemoney`.`usuarios`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `showmethemoney`.`usuarios` (

  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT ,

  `nombre` VARCHAR(45) NOT NULL ,

  `correo` VARCHAR(80) NOT NULL ,

  `password` VARCHAR(64) NOT NULL ,

  PRIMARY KEY (`idUsuario`) ,

  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `showmethemoney`.`categoriasPersonalizadas`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `showmethemoney`.`categoriasPersonalizadas` (

  `idUsuario` INT(11) NOT NULL ,

  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT ,

  `nombre` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`idCategoria`, `idUsuario`, `nombre`) ,

  INDEX `fk_usuarios_has_categorias_usuarios1_idx` (`idUsuario` ASC) ,

  CONSTRAINT `fk_usuarios_has_categorias_usuarios1`

    FOREIGN KEY (`idUsuario` )

    REFERENCES `showmethemoney`.`usuarios` (`idUsuario` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;





-- -----------------------------------------------------

-- Table `showmethemoney`.`movimientos`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `movimientos` (

  `idmovimientos` INT(11) NOT NULL AUTO_INCREMENT ,

  `importe` FLOAT(8,2) NOT NULL ,

  `tipo` CHAR(1) NOT NULL ,

  `fecha` DATE NOT NULL ,

  `idCategoria` INT(11) NOT NULL ,

  `idUsuario` INT(11) NOT NULL ,

  `nombre` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`idmovimientos`) ,

  INDEX `fk_movimientos_categoriasPersonalizadas1_idx` (`idCategoria` ASC, `idUsuario` ASC) ,

  CONSTRAINT `fk_movimientos_categoriasPersonalizadas1`

    FOREIGN KEY (`idCategoria` , `idUsuario`)

    REFERENCES `categoriasPersonalizadas` (`idCategoria` , `idUsuario` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8;








SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



