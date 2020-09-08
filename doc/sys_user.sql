/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50627
Source Host           : 127.0.0.1:3306
Source Database       : zgdb

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2020-09-08 18:46:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  `salt` varchar(256) DEFAULT NULL COMMENT '盐值',
  `telphone` varchar(128) NOT NULL,
  `age` int(64) unsigned zerofill DEFAULT NULL COMMENT '年龄',
  `gender` varchar(16) DEFAULT NULL COMMENT '性别',
  `register_mode` varchar(64) DEFAULT NULL COMMENT '支付方式：1微信，2支付宝',
  `third_party_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_telphone` (`telphone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('53', 'lisi', 'd9725c9cef2a830ea7bac94e9f14391f', null, '110', '0000000000000000000000000000000000000000000000000000000000000012', '男', null, null);
INSERT INTO `sys_user` VALUES ('54', 'zhangsan', 'd9725c9cef2a830ea7bac94e9f14391f', null, '120', '0000000000000000000000000000000000000000000000000000000000000012', '男', null, null);
INSERT INTO `sys_user` VALUES ('55', 'wangwu', 'd9725c9cef2a830ea7bac94e9f14391f', null, '119', '0000000000000000000000000000000000000000000000000000000000000012', '男', null, null);
