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

 Date: 18/04/2020 18:35:28
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
  `sort` bigint(255) NULL DEFAULT 1 COMMENT '排序',
  `updateDate` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示编号可以自己输入 不输入生成',
  `simg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '海报价格图' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vrs_pro
-- ----------------------------
INSERT INTO `vrs_pro` VALUES (1, '海报名册', 55, 50, '2020-04-15 14:20:23', 1, '11', 4, 2, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (2, '大萨dasads', 2222, 11, '2020-04-28 20:44:58', 1, '/files/qjt/d3e158fadcbb42f38167f73c331f6ad3.jpg', 1, 2, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (4, '新的vr图啊', 22, 22, '2020-04-14 19:22:31', 1, '/files/pro/aa5fde9ab3cc4483a7a7668f541082eb.png', 4, 4, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (5, '大萨达', 22, 22, '2020-04-14 19:23:24', 1, '/files/pro/143d809891904aaf9f134f7b5e4dd6bf.jpg', 4, 3, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (6, '换色', 33, 111, '2020-04-14 19:24:23', 1, '/files/pro/63a4f5917ad3472ab5fc4671894a432e.jpg', 5, 3, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (7, '大萨达1', 22, 22, '2020-04-14 19:25:16', 1, '', 6, 3, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (8, '31', 22, 22, '2020-04-14 20:26:05', 1, '', 1, 3, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (9, '换色改二级', 7477, 777, '2020-04-14 20:43:53', 1, '/files/qjt/96aa3929c2c14bad9c89bbd10d43b310.jpg', 5, 2, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (10, '换色系统的小图片', 22, 33, '2020-04-14 20:49:25', 1, '/files/pro/d4117ae9eb88426880db088d78d967ff.jpg', 1, 3, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (11, '我草你妈', 222, 3333, '2020-04-14 20:55:18', 1, '/files/pro/7fe7c06b72204b4c83d9c3a1180ac2b5.jpg', 1, 3, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (12, '打撒', 22, 233, '2020-04-14 20:58:41', 1, '/files/pro/22911b7e6b5141eca7858389e9ce36a3.png', 2, 5, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (13, '额', 222, 11, '2020-04-14 21:03:40', 1, '/files/pro/f53be6f6144c44c0bb658144d42d1a11.png', 1, 2, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (14, '门窗海报1', 222, 33, '2020-04-14 21:11:07', 1, '/files/pro/684cc31cae7047ca99abac18bd371364.png', 4, 4, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (15, '门窗海报2', 22, 33, '2020-04-14 21:11:23', 1, '/files/pro/a5d8e6bad308467f84c75829b40c90a8.jpg', 4, 4, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (16, '啊啊', 22, 33, '2020-04-15 10:03:53', 1, '/files/qjt/6ab5fa6b7f0e4f93a5744c010683687a.jpg', NULL, 2, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (17, '二级分类唱片', 22, 33, '2020-04-15 10:09:11', 1, '/files/qjt/3864e8cda32843698ff150f725add679.gif', NULL, 2, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (18, '门窗1海报呀', 22, 3355, '2020-04-15 10:14:26', 1, '/files/qjt/4753952ff6b5442ea5077aadd4c36d25.jpg', 1, 5, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (20, 'dasas1', 22, 2, '2020-04-15 10:17:16', 1, '/files/qjt/7d6a425bbe3242fca4bebeffb49979dc.png', NULL, 2, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (21, '换色', 11, 22, '2020-04-16 11:09:47', 1, '/files/qjt/fb77e44e0e504c8fa3cab3a2ee0a1dad.png', 1, 5, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (22, '海报画册人啊', 22, 22, '2020-04-16 11:10:31', 1, '/files/qjt/56b5c1f164d34d77a50b253404539f70.png', 5, 3, 1, NULL, NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (23, '大萨达大啊大大', 22, 33, '2020-04-16 14:19:32', 1, '/files/qjt/5e386e52a3cd4b3c84362aaadc3ce6c0.png', 1, 3, 1, '2020-04-30 14:41:21', NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (24, '新加的', 1312, 2, '2020-04-16 14:31:12', 1, '', 5, 3, 1, '2020-04-16 14:34:41', NULL, '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (31, '达到', NULL, NULL, '2020-04-17 11:13:43', 1, '/files/qjt/26289c6b2ad44295a5f49a9e3d7ff209.jpg', 5, 3, 3, NULL, 'code111', '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (32, '标题', NULL, NULL, '2020-04-17 11:20:09', 1, '/files/qjt/5d3cf84d8db249adbcd34080bed2bcf1.png', 2, 4, 1, NULL, 'code111ss', '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (33, '达到', NULL, NULL, '2020-04-17 11:20:44', 1, '/files/qjt/dacfcb8014ee4520b0dcd9f9e7c16474.png', 2, 5, 33, NULL, '1578909255', '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (34, '大萨达', NULL, NULL, '2020-04-17 12:00:58', 1, '', NULL, 2, NULL, NULL, '11766461121', '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (35, 'dasda1', NULL, NULL, '2020-04-17 12:03:06', 1, '/files/qjt/c66fa331634a4f30baec4b1283e45c81.gif', NULL, 2, 33, NULL, '12117219887', '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');
INSERT INTO `vrs_pro` VALUES (36, '测试选中ddss', 22, 33, '2020-04-17 13:52:45', 1, '/files/qjt/64c1f9eed2ca4977a9af683457e28516.png', NULL, 2, 44, '2020-04-17 16:20:56', '22', '/files/pro/e2332995c9094fd9a547093e08a3ffdd.jpg');

SET FOREIGN_KEY_CHECKS = 1;
