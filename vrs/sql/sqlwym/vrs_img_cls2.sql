/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : company

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2020-04-18 18:31:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for vrs_img_cls2
-- ----------------------------
DROP TABLE IF EXISTS `vrs_img_cls2`;
CREATE TABLE `vrs_img_cls2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `status` int(255) DEFAULT NULL COMMENT '状态',
  `cid` int(11) DEFAULT NULL COMMENT '外键id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `updateDate` datetime DEFAULT NULL COMMENT '修改日期',
  `pid` int(11) DEFAULT NULL COMMENT '是否为主菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='第二个分类表';
