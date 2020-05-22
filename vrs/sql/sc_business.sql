/*
Navicat MySQL Data Transfer

Source Server         : d
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : company

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-04-10 17:03:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sc_business
-- ----------------------------
DROP TABLE IF EXISTS `sc_business`;
CREATE TABLE `sc_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `addres` varchar(255) DEFAULT NULL COMMENT '商家地址',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL,
  `longitude` double(6,3) DEFAULT NULL COMMENT '经度',
  `latitude` double(6,3) DEFAULT NULL COMMENT '纬度',
  `status` int(11) DEFAULT '1' COMMENT '0 删除 1正常',
  `detailed` text COMMENT '商家介绍',
  `img` varchar(255) DEFAULT '' COMMENT '商家首页图',
  `uid` bigint(20) DEFAULT NULL COMMENT '商家跟用户关联 添加商家添加用户 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商家表 封面图 经度纬度 富文本详细信息 等';

-- ----------------------------
-- Records of sc_business
-- ----------------------------
INSERT INTO `sc_business` VALUES ('3', 'new商家', 'DASDA', null, null, '113.000', '28.000', '0', '<h2>大萨达撒大所<span style=\"font-weight: bold;\"></span></h2><div><span style=\"font-weight: bold;\">的萨达阿达</span></div>', '/files/business/27798c1a308f41bea8fe0aae7ccb463a.jpg', null);
INSERT INTO `sc_business` VALUES ('4', '名称', '地址啊', null, null, '113.000', '28.000', '1', '<p>大萨达</p>', '/files/business/df9bdb46922a4498b9b2f274f2729669.jpg', null);
INSERT INTO `sc_business` VALUES ('5', '修改的地址名字', '大大啊', '2020-04-08 01:06:14', '2020-04-08 01:08:55', '112.998', '28.184', '1', '<p>大<span style=\"color: inherit;\">修改的地址富文本</span></p><p>修改的地址222</p>', '/files/business/95db8bea309247d092db2d2092b1c27f.jpg', null);
INSERT INTO `sc_business` VALUES ('6', '大萨达', '', '2020-04-08 01:31:06', '2020-04-08 01:37:21', '112.982', '28.194', '1', '<p><img src=\"/files/text/abb1d05f9f2c4f3f9f8e1a9803086146.jpg\" style=\"max-width:30%;\">是adadadaad<br></p><p><br></p>', '/files/business/fd66cdff74ee439f9e86c8af42b03f78.jpg', '148');
INSERT INTO `sc_business` VALUES ('7', 'dasdas啊', '大萨达所', '2020-04-09 13:20:09', '2020-04-09 14:23:46', null, null, '1', '<p>dasdasdas</p>', '', '145');
