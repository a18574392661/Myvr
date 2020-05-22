/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : company

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2020-04-18 18:31:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for vrs_img_cls3
-- ----------------------------
DROP TABLE IF EXISTS `vrs_img_cls3`;
CREATE TABLE `vrs_img_cls3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` int(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '外键id',
  `sort` int(11) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='第二个分类表';
