/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : his_tenant_1

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-04-01 12:19:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_ON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_ON` datetime DEFAULT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_ium5fxjyer4d0eyjklbvuy5yq` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_ON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_ON` datetime DEFAULT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_lqaytvswxwacb7s84gcw7tk7l` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_ON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_ON` datetime DEFAULT NULL,
  `CAN_CREATE` tinyint(1) NOT NULL DEFAULT '1',
  `CAN_DELETE` tinyint(1) NOT NULL DEFAULT '1',
  `CAN_UPDATE` tinyint(1) NOT NULL DEFAULT '1',
  `PERMISSION_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKqk3q34ocu2wspg19qnxbkcapt` (`PERMISSION_ID`),
  KEY `FKj36kgfbx4ia3c71f4bqluag1t` (`ROLE_ID`),
  CONSTRAINT `FKj36kgfbx4ia3c71f4bqluag1t` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `FKqk3q34ocu2wspg19qnxbkcapt` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `permission` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_ON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_ON` datetime DEFAULT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '0',
  `PASSWORD` varchar(255) NOT NULL,
  `TENANT` varchar(255) NOT NULL,
  `USER_TYPE` varchar(255) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_lb5yrvw2c22im784wwrpwuq06` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2020-04-01 11:50:15', null, '1', '$2a$10$aiM9K2FTCHrhQLDe5MSDwOHdkRKM60n5ga6VfCho.yytoh/R.qlWe', 'his_tenant_1', 'ADMIN', 'admin');

-- ----------------------------
-- Table structure for `user_permission`
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_ON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_ON` datetime DEFAULT NULL,
  `PERMISSION_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKsnw1vd0hruqsnp3ob25ddcttt` (`PERMISSION_ID`),
  KEY `FKkbis19cl4blqdrfcs9os76yjs` (`USER_ID`),
  CONSTRAINT `FKkbis19cl4blqdrfcs9os76yjs` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FKsnw1vd0hruqsnp3ob25ddcttt` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `permission` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_ON` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_ON` datetime DEFAULT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKn1rn9qodd3u4le8uf3kl33qe3` (`ROLE_ID`),
  KEY `FKa8x5mvctia7u43u2mm3hyy5bm` (`USER_ID`),
  CONSTRAINT `FKa8x5mvctia7u43u2mm3hyy5bm` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FKn1rn9qodd3u4le8uf3kl33qe3` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
