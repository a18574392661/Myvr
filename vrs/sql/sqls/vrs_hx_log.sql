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

 Date: 26/04/2020 14:45:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_hx_log
-- ----------------------------
DROP TABLE IF EXISTS `vrs_hx_log`;
CREATE TABLE `vrs_hx_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL COMMENT '核销人id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '核销给谁的id ',
  `remker` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注1',
  `createDate` datetime(0) NULL DEFAULT NULL,
  `price` decimal(10, 3) NULL DEFAULT NULL COMMENT '金额',
  `status` int(10) NULL DEFAULT NULL COMMENT '收益 0 减少 1 增加',
  `level` int(255) NULL DEFAULT NULL COMMENT '核销级别(1 级或者2级)',
  `ordercode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '哪个订单核销的',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_hx_log
-- ----------------------------
INSERT INTO `vrs_hx_log` VALUES (7, 161, 162, '开通会员', '2020-04-26 10:08:44', 200.000, 1, 1, NULL);
INSERT INTO `vrs_hx_log` VALUES (8, 161, 164, '开通会员', '2020-04-26 10:08:45', 48.000, 1, 2, NULL);
INSERT INTO `vrs_hx_log` VALUES (9, 161, 164, '开通会员', '2020-04-26 10:09:35', 60.000, 1, 2, NULL);

SET FOREIGN_KEY_CHECKS = 1;
