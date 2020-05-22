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

 Date: 14/04/2020 14:40:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vrs_pro
-- ----------------------------
DROP TABLE IF EXISTS `vrs_pro`;
CREATE TABLE `vrs_pro`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pricce` decimal(10, 0) NULL DEFAULT NULL,
  `count` bigint(20) NULL DEFAULT NULL,
  `createDate` datetime(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '0 删除 1 显示',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '海报图片详细情况',
  `cid` int(11) NULL DEFAULT NULL COMMENT '二级分类id',
  `typeid` int(11) NULL DEFAULT NULL COMMENT '字典表id 1 2 3 4',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '海报价格图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_pro
-- ----------------------------
INSERT INTO `vrs_pro` VALUES (1, '海报名册', 55, 50, '2020-04-15 14:20:23', 11, '11', 4, 2);
INSERT INTO `vrs_pro` VALUES (2, '大萨达', 22, 11, NULL, 1, '11', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
