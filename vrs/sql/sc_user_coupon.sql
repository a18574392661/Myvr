/*
Navicat MySQL Data Transfer

Source Server         : d
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : company

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-04-10 17:03:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sc_user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `sc_user_coupon`;
CREATE TABLE `sc_user_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '优惠券码',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户id(一对一用户的优惠券)',
  `price` decimal(10,2) DEFAULT NULL COMMENT '优惠券价格(可以分配)',
  `name` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(11) DEFAULT '1' COMMENT '1 正常 0 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc_user_coupon
-- ----------------------------
INSERT INTO `sc_user_coupon` VALUES ('1', 'dasdadsa', '142', '400.00', '大大大', '2020-04-21 16:13:17', '2020-04-08 16:13:00', '1');
INSERT INTO `sc_user_coupon` VALUES ('2', '697499123233849344', '143', '0.00', '32131', '2020-04-08 17:32:30', null, '1');
INSERT INTO `sc_user_coupon` VALUES ('4', '697559281897046016', '147', '0.00', null, '2020-04-08 21:31:33', null, '1');
INSERT INTO `sc_user_coupon` VALUES ('7', '697741846813605888', '144', '591.00', '优惠券名称', '2020-04-09 09:37:00', '2020-04-10 15:44:20', '1');
