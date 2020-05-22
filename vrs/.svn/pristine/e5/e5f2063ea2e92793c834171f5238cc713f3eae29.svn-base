/*
Navicat MySQL Data Transfer

Source Server         : d
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : company

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-04-10 17:04:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` bigint(32) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '出身日期',
  `pic_id` bigint(32) DEFAULT NULL,
  `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
  `rid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '27bd386e70f280e24c2f4f2a549b82cf', '6', 'admin@example.com', '17699999999', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00', '96', '2017-12-14 00:00:00', '138', 'ccc', '122;121;', '北京市', '北京市市辖区', '东城区', null);
INSERT INTO `sys_user` VALUES ('2', 'test', '临时用户', '6cf3bb3deba2aadbd41ec9a22511084e', '6', 'test@bootdo.com', null, '1', '1', '2017-08-14 13:43:05', '2017-08-14 21:15:36', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('138', 'vryao', '姚总', '6080e4a2a022aefc5f75b2b9fcaf608e', '16', 'chensl09@139.com', null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('139', 'jiaxing', '杨佳兴', '8a9223d09983d3cac6538df9352dd5ec', '17', 'chensl09@139.com', null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('140', 'aaa啊', '爸爸', '0ac15ae6cd34f2b547f15ec0fe4566a6', null, '1851121@qq.com', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('143', '18574392661', null, 'd00aab893ac869b63adc0a4d18a55fb7', null, null, '18574392661', null, null, null, null, null, null, null, null, null, null, null, null, '62');
INSERT INTO `sys_user` VALUES ('144', '15573314821', null, 'a7be7777ef3aaaa37d7bb62445d157cf', null, null, '15573314821', null, null, null, null, null, null, null, null, null, null, null, null, '62');
INSERT INTO `sys_user` VALUES ('145', '15573314897', null, '8928248e5a709171966658192639a400', null, null, '15573314897', null, null, null, null, null, null, null, null, null, null, null, null, '63');
INSERT INTO `sys_user` VALUES ('146', '15578965236', null, '9462e9ead5f4e37d361102c16c86afe1', null, null, '15578965236', null, null, null, null, null, null, null, null, null, null, null, null, '63');
INSERT INTO `sys_user` VALUES ('147', '15574392661', null, '8dd063a36ab260ed765825fe7179360f', null, null, '15574392661', null, null, null, null, null, null, null, null, null, null, null, null, '62');
INSERT INTO `sys_user` VALUES ('148', '17743992661', null, 'de218047f9b0011b41dadb4ca37403c6', null, null, '17743992661', null, null, null, null, null, null, null, null, null, null, null, null, '63');
INSERT INTO `sys_user` VALUES ('149', '15574392668', '15574392668', 'bf0e7839a1ddbf40075617de28d2a7dd', null, null, '15574392668', '1', null, '2020-04-09 13:20:09', '2020-04-09 14:23:46', null, null, null, null, null, null, null, null, '63');
