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

 Date: 26/04/2020 14:44:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_user_hx
-- ----------------------------
DROP TABLE IF EXISTS `vrs_user_hx`;
CREATE TABLE `vrs_user_hx`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NULL DEFAULT NULL,
  `firstLevel` decimal(10, 3) NULL DEFAULT NULL,
  `seconLevel` decimal(10, 3) NULL DEFAULT NULL,
  `detaile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色核销提成设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_user_hx
-- ----------------------------
INSERT INTO `vrs_user_hx` VALUES (1, 59, 10.000, 6.000, 'vip用户提成比例');
INSERT INTO `vrs_user_hx` VALUES (2, 70, 5.000, 15.000, '工厂提成比例');
INSERT INTO `vrs_user_hx` VALUES (3, 71, 20.000, 30.010, '企业提成比例');

SET FOREIGN_KEY_CHECKS = 1;
