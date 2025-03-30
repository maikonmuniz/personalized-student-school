
CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `id_account` varchar(36) NOT NULL,
  `id_type_account` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_account` (`id_account`),
  KEY `id_type_account` (`id_type_account`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`id_type_account`) REFERENCES `type_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_course` int NOT NULL,
  `id_account` bigint NOT NULL,
  `id_account_course` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_account_course` (`id_account_course`),
  KEY `id_course` (`id_course`),
  KEY `id_account` (`id_account`),
  CONSTRAINT `account_course_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`),
  CONSTRAINT `account_course_ibfk_2` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account_discipline` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_discipline` int NOT NULL,
  `id_account_course` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_discipline_refe` (`id_discipline`),
  KEY `id_account_course_refe` (`id_account_course`),
  CONSTRAINT `id_account_course_refe` FOREIGN KEY (`id_account_course`) REFERENCES `account_course` (`id`),
  CONSTRAINT `id_discipline_refe` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `class_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` text,
  `id_account` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_account` (`id_account`),
  CONSTRAINT `class_course_ibfk_1` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `class_course_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `presence` tinyint(1) NOT NULL,
  `id_account` varchar(36) NOT NULL,
  `id_class_course` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_class_course` (`id_class_course`),
  KEY `id_account` (`id_account`),
  CONSTRAINT `class_course_account_ibfk_1` FOREIGN KEY (`id_class_course`) REFERENCES `class_course` (`id`),
  CONSTRAINT `class_course_account_ibfk_2` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `type_course_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type_course_id` (`type_course_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`type_course_id`) REFERENCES `type_course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `discipline` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `id_course` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_course` (`id_course`),
  CONSTRAINT `discipline_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `test` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `note` decimal(2,2) NOT NULL,
  `id_type_note` int NOT NULL,
  `id_class_course_account` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_class_course_account` (`id_class_course_account`),
  KEY `id_type_note` (`id_type_note`),
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`id_class_course_account`) REFERENCES `class_course_account` (`id`),
  CONSTRAINT `test_ibfk_2` FOREIGN KEY (`id_type_note`) REFERENCES `type_note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `type_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_register` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_register` (`type_register`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `type_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `type_note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;