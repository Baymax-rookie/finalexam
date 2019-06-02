/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : hello

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 02/06/2019 14:31:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for moves
-- ----------------------------
DROP TABLE IF EXISTS `moves`;
CREATE TABLE `moves`  (
  `queue` int(10) NOT NULL AUTO_INCREMENT,
  `x` int(10) DEFAULT NULL,
  `y` int(10) DEFAULT NULL,
  PRIMARY KEY (`queue`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moves
-- ----------------------------
INSERT INTO `moves` VALUES (1, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
