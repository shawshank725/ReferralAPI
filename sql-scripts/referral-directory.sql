CREATE DATABASE  IF NOT EXISTS `referral_directory`;
USE `referral_directory`;

CREATE TABLE if not exists `user`  (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(1000) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `generated_code` varchar(45) DEFAULT NULL,
  `referred_code` varchar(45) DEFAULT NULL,
  `profile_completed` varchar(5) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE if not exists `referral_table`  (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `referrer_code` varchar(1000) DEFAULT NULL,
  `referred_code` varchar(45) DEFAULT NULL,
  `successful` varchar(5) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


