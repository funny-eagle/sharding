/*
 Navicat MySQL Data Transfer

 Source Server         : 47.106.130.196_3306
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 47.106.130.196:3306
 Source Schema         : ebook_sharding

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 12/11/2018 11:43:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for library_ebook_0
-- ----------------------------
DROP TABLE IF EXISTS `library_ebook_0`;
CREATE TABLE `library_ebook_0`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(11) NULL DEFAULT NULL,
  `customer_id` bigint(11) NULL DEFAULT NULL,
  `library_id` bigint(11) NULL DEFAULT NULL,
  `ebook_id` bigint(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 267680348010184705 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
