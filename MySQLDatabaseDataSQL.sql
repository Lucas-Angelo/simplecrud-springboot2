-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.23 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para simplecrud
CREATE DATABASE IF NOT EXISTS `simplecrud` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `simplecrud`;

-- Copiando estrutura para tabela simplecrud.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` double(13,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela simplecrud.product: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `name`, `price`) VALUES
	(1, 'Office365', 500.00);
INSERT INTO `product` (`id`, `name`, `price`) VALUES
	(2, 'Windows 10 Pro', 1000.00);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Copiando estrutura para tabela simplecrud.profile
CREATE TABLE IF NOT EXISTS `profile` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `profile` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_profile_user1_idx` (`user_id`),
  CONSTRAINT `fk_profile_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela simplecrud.profile: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` (`id`, `user_id`, `profile`) VALUES
	(1, 1, 1);
INSERT INTO `profile` (`id`, `user_id`, `profile`) VALUES
	(2, 1, 2);
INSERT INTO `profile` (`id`, `user_id`, `profile`) VALUES
	(3, 2, 2);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;

-- Copiando estrutura para tabela simplecrud.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela simplecrud.user: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
	(1, 'Lucas', 'lcs2001_@lucasangelo.com', '$2a$10$1YK50sWYFIIpErSqYqPjuOHxAmfdo/IMZ6x7xYd5Eih90GXYrXsMO');
INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
	(2, 'Ana', 'ana@email.com', '$2a$10$0m/PUUAgM5NMXjaqZKvR/ewnuwJ.QlPwG.tIebOdD19/k64gu9T2K');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Copiando estrutura para tabela simplecrud.user_product
CREATE TABLE IF NOT EXISTS `user_product` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `product_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_product_user_idx` (`user_id`),
  KEY `fk_user_product_product1_idx` (`product_id`),
  CONSTRAINT `fk_user_product_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_user_product_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela simplecrud.user_product: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `user_product` DISABLE KEYS */;
INSERT INTO `user_product` (`id`, `user_id`, `product_id`) VALUES
	(1, 1, 1);
INSERT INTO `user_product` (`id`, `user_id`, `product_id`) VALUES
	(2, 1, 2);
INSERT INTO `user_product` (`id`, `user_id`, `product_id`) VALUES
	(3, 2, 2);
/*!40000 ALTER TABLE `user_product` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
