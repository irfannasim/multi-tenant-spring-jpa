/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : his_master_db

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-04-01 12:18:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');

-- ----------------------------
-- Table structure for `tenants`
-- ----------------------------
DROP TABLE IF EXISTS `tenants`;
CREATE TABLE `tenants` (
  `ID` bigint(20) NOT NULL,
  `DB_NAME` varchar(255) DEFAULT NULL,
  `DB_PASSWORD` varchar(255) DEFAULT NULL,
  `DB_SERVER_PORT` varchar(255) DEFAULT NULL,
  `DB_SERVER_URL` varchar(255) DEFAULT NULL,
  `DB_USERNAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `IS_SSL_ENABLED` tinyint(1) NOT NULL DEFAULT '0',
  `POOL_CONFIGURATION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKg4uinect7r2dpjieb1tfh5om8` (`POOL_CONFIGURATION_ID`),
  CONSTRAINT `FKg4uinect7r2dpjieb1tfh5om8` FOREIGN KEY (`POOL_CONFIGURATION_ID`) REFERENCES `tenant_hikari_pool_configuration` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tenants
-- ----------------------------
INSERT INTO `tenants` VALUES ('1', 'his_tenant_1', 'root', '3306', 'localhost', 'root', 'his_tenant_1', '0', '1');

-- ----------------------------
-- Table structure for `tenant_hikari_pool_configuration`
-- ----------------------------
DROP TABLE IF EXISTS `tenant_hikari_pool_configuration`;
CREATE TABLE `tenant_hikari_pool_configuration` (
  `ID` bigint(20) NOT NULL,
  `CONNECTION_TIMEOUT` bigint(20) DEFAULT NULL,
  `IDLE_TIMEOUT` bigint(20) DEFAULT NULL,
  `LEAK_DETECTION_THRESHOLD` bigint(20) DEFAULT NULL,
  `MAX_LIFE_TIME` bigint(20) DEFAULT NULL,
  `MAX_POOL_SIZE` int(11) DEFAULT NULL,
  `MINIMUM_IDLE` int(11) DEFAULT NULL,
  `POOL_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tenant_hikari_pool_configuration
-- ----------------------------
INSERT INTO `tenant_hikari_pool_configuration` VALUES ('1', '60000', '10000', '2000', '1800000', '25', '10', 'Tenant1OrganizationPool');
