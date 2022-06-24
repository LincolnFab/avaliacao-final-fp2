-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_banco
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_banco
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_banco` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_banco` ;

-- -----------------------------------------------------
-- Table `db_banco`.`banco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_banco`.`banco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_banco`.`agencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_banco`.`agencia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `banco_id` INT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `banco_id` (`banco_id` ASC) VISIBLE,
  CONSTRAINT `agencia_ibfk_1`
    FOREIGN KEY (`banco_id`)
    REFERENCES `db_banco`.`banco` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_banco`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_banco`.`cliente` (
  `cpf` VARCHAR(15) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_banco`.`conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_banco`.`conta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `agencia_id` INT NOT NULL,
  `cliente_cpf` VARCHAR(15) NOT NULL,
  `tipo_conta` VARCHAR(10) NOT NULL,
  `saldo` DOUBLE NOT NULL,
  `limite` DOUBLE NULL DEFAULT NULL,
  `data_abertura` DATETIME NOT NULL,
  `senha` VARCHAR(25) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`, `agencia_id`),
  INDEX `agencia_id` (`agencia_id` ASC) VISIBLE,
  INDEX `cliente_cpf` (`cliente_cpf` ASC) VISIBLE,
  CONSTRAINT `conta_ibfk_1`
    FOREIGN KEY (`agencia_id`)
    REFERENCES `db_banco`.`agencia` (`id`),
  CONSTRAINT `conta_ibfk_2`
    FOREIGN KEY (`cliente_cpf`)
    REFERENCES `db_banco`.`cliente` (`cpf`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_banco`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_banco`.`funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `agencia_id` INT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `cpf` VARCHAR(15) NOT NULL,
  `login` VARCHAR(25) NOT NULL,
  `senha` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `agencia_id` (`agencia_id` ASC) VISIBLE,
  CONSTRAINT `funcionario_ibfk_1`
    FOREIGN KEY (`agencia_id`)
    REFERENCES `db_banco`.`agencia` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_banco`.`operacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_banco`.`operacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `conta_id` INT NOT NULL,
  `conta_agencia_id` INT NOT NULL,
  `conta_id_destino` INT NOT NULL,
  `agencia_id_destino` INT NOT NULL,
  `data` DATETIME NOT NULL,
  `valor` DOUBLE NOT NULL,
  `status` TINYINT(1) NOT NULL,
  `tipo_operacao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `conta_id`, `conta_agencia_id`),
  INDEX `conta_id` (`conta_id` ASC, `conta_agencia_id` ASC) VISIBLE,
  CONSTRAINT `operacao_ibfk_1`
    FOREIGN KEY (`conta_id` , `conta_agencia_id`)
    REFERENCES `db_banco`.`conta` (`id` , `agencia_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;





INSERT INTO banco (id, nome) VALUES 
	(1, "Caixa Econômica Federal");

INSERT INTO agencia (id, banco_id, nome) VALUES
	(1, 1, "Caixa Presidente Epitácio");

INSERT INTO funcionario (id, agencia_id, nome, cpf, login, senha) VALUES
	(1, 1, "Lincoln Fabiano", "060.928.521-10", "L", "1");

SELECT * FROM agencia;

SELECT * FROM cliente;

SELECT * FROM funcionario;

SELECT * FROM operacao;

SELECT * FROM operacao WHERE tipo_operacao = 'deposito_caixa';

SELECT * FROM operacao WHERE tipo_operacao = 'deposito_atendente';

SELECT * FROM operacao WHERE tipo_operacao = 'transferencia';

SELECT * FROM conta;

SELECT * FROM conta WHERE id = 2 AND agencia_id = 1;







SET SQL_SAFE_UPDATES = 0;
DELETE FROM operacao;
DELETE FROM conta;
DELETE FROM cliente;
SET SQL_SAFE_UPDATES = 1;


