/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : company

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2020-04-13 15:51:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for all_article
-- ----------------------------
DROP TABLE IF EXISTS `all_article`;
CREATE TABLE `all_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `context` longtext COMMENT '内容',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `qrcode_url` varchar(255) DEFAULT NULL COMMENT '二维码URL',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `category_id` int(2) DEFAULT NULL COMMENT '所属分类',
  `img` varchar(255) DEFAULT NULL COMMENT '图片',
  `bz` varchar(255) DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
