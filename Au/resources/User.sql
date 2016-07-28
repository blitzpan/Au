/*
Navicat MySQL Data Transfer

Source Server         : 115.28.72.34
Source Server Version : 50173
Source Host           : 115.28.72.34:3306
Source Database       : au

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-07-28 12:10:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `User`
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `pw` varchar(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of User
-- ----------------------------
INSERT INTO `User` VALUES ('1', '1028353676', 'DkvkuAkaRTlRji5pVorr7Q==');
