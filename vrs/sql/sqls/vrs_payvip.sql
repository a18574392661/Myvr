/*
 Navicat Premium Data Transfer

 Source Server         : 线下数据库
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : company

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 26/04/2020 14:45:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_payvip
-- ----------------------------
DROP TABLE IF EXISTS `vrs_payvip`;
CREATE TABLE `vrs_payvip`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `month` int(11) NOT NULL COMMENT '开通月份',
  `price` decimal(10, 2) NOT NULL COMMENT '对应价格',
  `remker` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标注',
  `vipId` int(11) NULL DEFAULT NULL COMMENT '对应会员表id',
  `status` int(5) NULL DEFAULT 0 COMMENT '0 关闭 1开启 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_payvip
-- ----------------------------
INSERT INTO `vrs_payvip` VALUES (1, 2, 100.00, '2个月', 2, 1);
INSERT INTO `vrs_payvip` VALUES (2, 6, 180.00, '半年', 2, 0);
INSERT INTO `vrs_payvip` VALUES (3, 12, 666.00, '一年', 2, 1);
INSERT INTO `vrs_payvip` VALUES (4, 3, 150.00, '3个月', 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
