/*
Navicat MySQL Data Transfer

Source Server         : d
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : company

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-04-10 17:03:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sc_user_coupon_log
-- ----------------------------
DROP TABLE IF EXISTS `sc_user_coupon_log`;
CREATE TABLE `sc_user_coupon_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户id 消费的记录(优惠券)',
  `price` decimal(10,2) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL COMMENT '消费的时间',
  `payStatus` int(11) DEFAULT '0' COMMENT '0 待扣费 1 已扣费 2 已取消',
  `busid` int(11) DEFAULT NULL COMMENT '商家id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc_user_coupon_log
-- ----------------------------
INSERT INTO `sc_user_coupon_log` VALUES ('1', '142', '100.00', '2020-04-22 16:34:17', '0', null);
INSERT INTO `sc_user_coupon_log` VALUES ('2', '142', '100.00', '2020-04-08 17:02:47', '1', null);
INSERT INTO `sc_user_coupon_log` VALUES ('3', '144', '5.00', '2020-04-08 20:56:06', '1', '3');
INSERT INTO `sc_user_coupon_log` VALUES ('4', '144', '15.00', '2020-04-09 11:44:34', '2', '7');
INSERT INTO `sc_user_coupon_log` VALUES ('5', '144', '11.00', '2020-04-09 12:06:03', '2', '7');
INSERT INTO `sc_user_coupon_log` VALUES ('6', '144', '11.00', '2020-04-09 12:19:46', '2', '7');
INSERT INTO `sc_user_coupon_log` VALUES ('7', '144', '22.00', '2020-04-09 12:23:22', '3', '7');
INSERT INTO `sc_user_coupon_log` VALUES ('8', '144', '100.00', '2020-04-09 14:40:27', '3', '7');
INSERT INTO `sc_user_coupon_log` VALUES ('9', '144', '10.00', '2020-04-09 14:45:48', '2', '7');
INSERT INTO `sc_user_coupon_log` VALUES ('10', '144', '5.00', '2020-04-09 14:49:42', '1', '7');
