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

 Date: 26/04/2020 14:45:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_userpay_tx
-- ----------------------------
DROP TABLE IF EXISTS `vrs_userpay_tx`;
CREATE TABLE `vrs_userpay_tx`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDate` datetime(0) NULL DEFAULT NULL,
  `status` int(5) NULL DEFAULT NULL COMMENT '0 提现失败 1 提现成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提现记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_userpay_tx
-- ----------------------------
INSERT INTO `vrs_userpay_tx` VALUES (1, 1, '500', '2020-04-09 13:43:39', 1);

SET FOREIGN_KEY_CHECKS = 1;
