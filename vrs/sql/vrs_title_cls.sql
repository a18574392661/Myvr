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

 Date: 14/04/2020 14:40:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_title_cls
-- ----------------------------
DROP TABLE IF EXISTS `vrs_title_cls`;
CREATE TABLE `vrs_title_cls`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT 0,
  `status` int(11) NULL DEFAULT 1 COMMENT '0 隐藏 1 显示',
  `hrefs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '下级跳的链接',
  `isfulls` int(255) NULL DEFAULT NULL COMMENT '首页列表是否展示 0 不展示 1 展示',
  `sort` int(255) NULL DEFAULT NULL,
  `typeid` int(11) NULL DEFAULT NULL COMMENT '1 定制设计 2 换色系统 3 门窗设计 4 高端画册',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_title_cls
-- ----------------------------
INSERT INTO `vrs_title_cls` VALUES (1, '首页', 0, 1, '#11', 0, 1, 0);
INSERT INTO `vrs_title_cls` VALUES (2, '定制设计', 0, 1, '内部链接', 1, 2, 1);
INSERT INTO `vrs_title_cls` VALUES (3, '换色系统', 0, 1, '#', 1, 3, 2);
INSERT INTO `vrs_title_cls` VALUES (4, '门窗设计', 0, 1, 'www.baidu.com', 1, 4, 4);
INSERT INTO `vrs_title_cls` VALUES (5, '高端画册', 6, 1, 'www.baidu', 1, 5, 5);
INSERT INTO `vrs_title_cls` VALUES (6, '品牌墙', 0, 0, '#', 1, NULL, 6);
INSERT INTO `vrs_title_cls` VALUES (7, '定制圈圈', 0, 0, '###', 0, NULL, 7);
INSERT INTO `vrs_title_cls` VALUES (9, '全景导购', 0, 1, '#', 0, 6, 8);

SET FOREIGN_KEY_CHECKS = 1;
