CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_course` int NOT NULL,
  `id_account` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_course` (`id_course`),
  KEY `id_account` (`id_account`),
  CONSTRAINT `account_course_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`),
  CONSTRAINT `account_course_ibfk_2` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account_discipline` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `notes` decimal(2,2) NOT NULL,
  `id_discipline` int NOT NULL,
  `id_type_note` int NOT NULL,
  `id_account_course` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_type_note_re` (`id_type_note`),
  KEY `id_discipline_refe` (`id_discipline`),
  KEY `id_account_course_refe` (`id_account_course`),
  CONSTRAINT `id_account_course_refe` FOREIGN KEY (`id_account_course`) REFERENCES `account_course` (`id`),
  CONSTRAINT `id_discipline_refe` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id`),
  CONSTRAINT `id_type_note_re` FOREIGN KEY (`id_type_note`) REFERENCES `type_note` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `type_course_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type_course_id` (`type_course_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`type_course_id`) REFERENCES `type_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `discipline` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `id_course` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_course` (`id_course`),
  CONSTRAINT `discipline_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `type_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `type_note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci