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

 Date: 26/04/2020 14:45:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_payorder_log
-- ----------------------------
DROP TABLE IF EXISTS `vrs_payorder_log`;
CREATE TABLE `vrs_payorder_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ordercode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `hprice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核销后的金额',
  `status` int(255) NULL DEFAULT NULL COMMENT '支付状态',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付备注',
  `paySta` int(255) NULL DEFAULT NULL COMMENT '1 微信 2支付宝',
  `uid` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建订单时间',
  `tradeNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_payorder_log
-- ----------------------------
INSERT INTO `vrs_payorder_log` VALUES (11, 'dsda', 312.00, '21', 1, '3123', 1, 1, '2020-04-16 10:44:38', 'dasda');

SET FOREIGN_KEY_CHECKS = 1;
