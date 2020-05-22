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

 Date: 18/04/2020 18:36:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_imgs_syt
-- ----------------------------
DROP TABLE IF EXISTS `vrs_imgs_syt`;
CREATE TABLE `vrs_imgs_syt`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(255) NULL DEFAULT NULL,
  `updateDatee` datetime(0) NULL DEFAULT NULL,
  `cid` int(11) NULL DEFAULT NULL COMMENT '产品图片id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '缩月图标' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_imgs_syt
-- ----------------------------
INSERT INTO `vrs_imgs_syt` VALUES (1, '/files/pro/18b7909b6eef4fcca1ccf53377edae47.png', 1, '2020-04-18 12:24:19', 33);
INSERT INTO `vrs_imgs_syt` VALUES (2, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg', 2, '2020-04-09 10:09:16', 33);
INSERT INTO `vrs_imgs_syt` VALUES (3, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg', 3, '2020-04-17 11:26:07', 33);
INSERT INTO `vrs_imgs_syt` VALUES (4, '/files/vr/05c75531025243f9a7c34bd16f73dd06.jpg', 2, NULL, 33);
INSERT INTO `vrs_imgs_syt` VALUES (5, '/files/vr/1278e57b66264743b5e3c137cb5b4394.jpg', 3, NULL, 33);
INSERT INTO `vrs_imgs_syt` VALUES (6, '/files/vr/296f61633abf47aa968494063e037a43.png', 3, NULL, 33);
INSERT INTO `vrs_imgs_syt` VALUES (7, '/files/vr/d787466ccf7a4a48a6d47ca82dbf9bb2.jpg', 3, '2020-04-18 12:16:02', 33);

SET FOREIGN_KEY_CHECKS = 1;
