/*
 Navicat Premium Data Transfer

 Source Server         : 47.106.130.196_3306
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 47.106.130.196:3306
 Source Schema         : ebook

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 31/10/2018 11:51:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) BINARY CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '成都客户');
INSERT INTO `customer` VALUES (2, '绵阳客户');

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) BINARY CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ebook
-- ----------------------------
INSERT INTO `ebook` VALUES (1, 'spring boot');
INSERT INTO `ebook` VALUES (2, '重构');
INSERT INTO `ebook` VALUES (3, '微服务实战');
INSERT INTO `ebook` VALUES (4, 'Mycat从入门到放弃');

-- ----------------------------
-- Table structure for library
-- ----------------------------
DROP TABLE IF EXISTS `library`;
CREATE TABLE `library`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) BINARY CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of library
-- ----------------------------
INSERT INTO `library` VALUES (1, 1, '成都高新区图书馆');
INSERT INTO `library` VALUES (2, 1, '成都天府新区图书馆');
INSERT INTO `library` VALUES (3, 2, '绵阳图书馆1');
INSERT INTO `library` VALUES (4, 2, '绵阳图书馆2');

-- ----------------------------
-- Table structure for library_ebook
-- ----------------------------
DROP TABLE IF EXISTS `library_ebook`;
CREATE TABLE `library_ebook`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL,
  `customer_id` int(11) NULL DEFAULT NULL,
  `library_id` int(11) NULL DEFAULT NULL,
  `ebook_id` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of library_ebook
-- ----------------------------
INSERT INTO `library_ebook` VALUES (1, 1, 1, 1, 1, 1);
INSERT INTO `library_ebook` VALUES (2, 1, 1, 1, 2, 1);
INSERT INTO `library_ebook` VALUES (3, 1, 1, 1, 3, 1);
INSERT INTO `library_ebook` VALUES (4, 1, 1, 2, 1, 1);
INSERT INTO `library_ebook` VALUES (5, 1, 1, 2, 2, 1);
INSERT INTO `library_ebook` VALUES (6, 1, 1, 2, 3, 1);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NULL DEFAULT NULL,
  `name` varchar(255) BINARY CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 1, '订单1');
INSERT INTO `order` VALUES (2, 2, '订单1');

-- ----------------------------
-- Table structure for order_ebook
-- ----------------------------
DROP TABLE IF EXISTS `order_ebook`;
CREATE TABLE `order_ebook`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ebook_id` bigint(20) NULL DEFAULT NULL,
  `customer_id` bigint(20) NULL DEFAULT NULL,
  `order_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_ebook
-- ----------------------------
INSERT INTO `order_ebook` VALUES (1, 1, 1, 1);
INSERT INTO `order_ebook` VALUES (2, 2, 1, 1);
INSERT INTO `order_ebook` VALUES (3, 3, 1, 1);

-- ----------------------------
-- Table structure for order_library
-- ----------------------------
DROP TABLE IF EXISTS `order_library`;
CREATE TABLE `order_library`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NULL DEFAULT NULL,
  `library_id` int(11) NULL DEFAULT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_library
-- ----------------------------
INSERT INTO `order_library` VALUES (1, 1, 1, 1);
INSERT INTO `order_library` VALUES (2, 1, 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
