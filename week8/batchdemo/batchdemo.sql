/*
Navicat MySQL Data Transfer

Source Server         : qwl
Source Server Version : 50644
Source Host           : localhost:3306
Source Database       : batchdemo

Target Server Type    : MYSQL
Target Server Version : 50644
File Encoding         : 65001

Date: 2020-06-08 17:55:22
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '严曼', '29');
INSERT INTO `person` VALUES ('2', '谢怜', '78');
INSERT INTO `person` VALUES ('3', '诸葛', '105');
INSERT INTO `person` VALUES ('4', '金觅', '87');
INSERT INTO `person` VALUES ('5', '沈旋', '73');
INSERT INTO `person` VALUES ('6', '闻人友', '36');
INSERT INTO `person` VALUES ('7', '孔海萱', '6');
INSERT INTO `person` VALUES ('8', '万俟真', '92');
INSERT INTO `person` VALUES ('9', '华春梦', '49');
INSERT INTO `person` VALUES ('10', '蒋玉翠', '78');
