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

 Date: 18/04/2020 18:27:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_pro_cen_tit
-- ----------------------------
DROP TABLE IF EXISTS `vrs_pro_cen_tit`;
CREATE TABLE `vrs_pro_cen_tit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `proid` bigint(20) NULL DEFAULT NULL,
  `tid` int(11) NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片标签中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_pro_cen_tit
-- ----------------------------
INSERT INTO `vrs_pro_cen_tit` VALUES (3, 32, 3);
INSERT INTO `vrs_pro_cen_tit` VALUES (4, 32, 5);
INSERT INTO `vrs_pro_cen_tit` VALUES (5, 33, 2);
INSERT INTO `vrs_pro_cen_tit` VALUES (6, 33, 4);
INSERT INTO `vrs_pro_cen_tit` VALUES (7, 34, 2);
INSERT INTO `vrs_pro_cen_tit` VALUES (8, 34, 4);
INSERT INTO `vrs_pro_cen_tit` VALUES (9, 35, 2);
INSERT INTO `vrs_pro_cen_tit` VALUES (10, 35, 4);
INSERT INTO `vrs_pro_cen_tit` VALUES (19, 36, 2);
INSERT INTO `vrs_pro_cen_tit` VALUES (20, 36, 5);

SET FOREIGN_KEY_CHECKS = 1;
