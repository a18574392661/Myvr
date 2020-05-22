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

 Date: 14/04/2020 14:40:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_img_cls
-- ----------------------------
DROP TABLE IF EXISTS `vrs_img_cls`;
CREATE TABLE `vrs_img_cls`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDate` datetime(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 删除 1正常',
  `updateDate` datetime(0) NULL DEFAULT NULL,
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '首页图',
  `cid` int(11) NULL DEFAULT NULL COMMENT '字典表菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_img_cls
-- ----------------------------
INSERT INTO `vrs_img_cls` VALUES (3, '大萨达111', '名字2', NULL, '1', NULL, 0, NULL, 2);
INSERT INTO `vrs_img_cls` VALUES (4, '大萨达', NULL, NULL, '1', NULL, 3, NULL, 2);
INSERT INTO `vrs_img_cls` VALUES (10, '大达大厦', NULL, NULL, '1', '2020-04-14 11:47:37', 11, NULL, 2);
INSERT INTO `vrs_img_cls` VALUES (11, 'dasdada啊啊', NULL, '2020-04-14 10:38:23', '1', '2020-04-14 10:47:21', 0, NULL, 2);
INSERT INTO `vrs_img_cls` VALUES (12, '嗯嗯', NULL, '2020-04-14 14:08:55', '1', NULL, 0, NULL, 2);
INSERT INTO `vrs_img_cls` VALUES (13, '饿饿饿饿e', NULL, '2020-04-14 14:09:09', '1', '2020-04-14 14:10:00', 12, NULL, 2);

SET FOREIGN_KEY_CHECKS = 1;
