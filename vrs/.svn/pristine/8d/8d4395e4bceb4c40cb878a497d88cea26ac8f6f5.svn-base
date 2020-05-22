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

 Date: 26/04/2020 14:45:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_user_shop
-- ----------------------------
DROP TABLE IF EXISTS `vrs_user_shop`;
CREATE TABLE `vrs_user_shop`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `proid` int(10) NULL DEFAULT NULL,
  `uid` int(10) NULL DEFAULT NULL,
  `count` int(10) NULL DEFAULT NULL,
  `createDate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户加入图片购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_user_shop
-- ----------------------------
INSERT INTO `vrs_user_shop` VALUES (1, '美女画dasd', 99, 1, 2, '2020-04-23 11:37:24');
INSERT INTO `vrs_user_shop` VALUES (2, '换色现代风111', 98, 1, 1, '2020-04-24 20:01:10');

SET FOREIGN_KEY_CHECKS = 1;
