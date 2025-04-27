CREATE TABLE `type_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_register` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_register` (`type_register`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `type_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `type_note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_account_course` varchar(36) NOT NULL,
  `account_id_fk` varchar(36) NOT NULL,
  `course_id_fk` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_account_course` (`id_account_course`),
  KEY `const_account_id_fk` (`account_id_fk`),
  KEY `const_course_id_fk` (`course_id_fk`),
  CONSTRAINT `const_account_id_fk` FOREIGN KEY (`account_id_fk`) REFERENCES `account` (`id_account`),
  CONSTRAINT `const_course_id_fk` FOREIGN KEY (`course_id_fk`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `account_discipline` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id_fk` varchar(36) NOT NULL,
  `discipline_id_fk` varchar(36) NOT NULL,
  `account_discipline_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_discipline_id` (`account_discipline_id`),
  KEY `account_id_fk_const` (`account_id_fk`),
  KEY `discipline_id_fk_const` (`discipline_id_fk`),
  CONSTRAINT `account_id_fk_const` FOREIGN KEY (`account_id_fk`) REFERENCES `account` (`id_account`),
  CONSTRAINT `discipline_id_fk_const` FOREIGN KEY (`discipline_id_fk`) REFERENCES `discipline` (`discipline_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `class_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` text,
  `id_account` varchar(36) NOT NULL,
  `active_discipline` tinyint(1) DEFAULT '1',
  `date_init` datetime DEFAULT CURRENT_TIMESTAMP,
  `date_end` datetime DEFAULT NULL,
  `class_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_id` (`class_id`),
  KEY `id_account` (`id_account`),
  CONSTRAINT `class_course_ibfk_1` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `class_course_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `presence` tinyint(1) NOT NULL,
  `id_account` varchar(36) NOT NULL,
  `class_id_fk` varchar(36) NOT NULL,
  `presence_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_account` (`id_account`),
  KEY `class_id_fk_const` (`class_id_fk`),
  CONSTRAINT `class_course_account_ibfk_2` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`),
  CONSTRAINT `class_id_fk_const` FOREIGN KEY (`class_id_fk`) REFERENCES `class_course` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `type_course_id` int DEFAULT NULL,
  `id_account_fk` varchar(36) NOT NULL,
  `course_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id` (`course_id`),
  KEY `type_course_id` (`type_course_id`),
  KEY `fk_id_account` (`id_account_fk`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`type_course_id`) REFERENCES `type_course` (`id`),
  CONSTRAINT `fk_id_account` FOREIGN KEY (`id_account_fk`) REFERENCES `account` (`id_account`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `discipline` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `id_course` int NOT NULL,
  `discipline_id` varchar(36) NOT NULL,
  `active_discipline` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `discipline_id` (`discipline_id`),
  KEY `id_course` (`id_course`),
  CONSTRAINT `discipline_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  UNIQUE KEY `username` (`username`),
  KEY `id_type_account` (`id_type_account`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`id_type_account`) REFERENCES `type_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
