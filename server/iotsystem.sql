/*
Navicat MySQL Data Transfer

Source Server         : qwk
Source Server Version : 50173
Source Host           : your host
Source Database       : iotsystem

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-07-31 21:00:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bindinfo
-- ----------------------------
DROP TABLE IF EXISTS `bindinfo`;
CREATE TABLE `bindinfo` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  `btime` datetime NOT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bindinfo
-- ----------------------------
INSERT INTO `bindinfo` VALUES ('1', '1', '2', '2017-07-05 08:17:17');
INSERT INTO `bindinfo` VALUES ('2', '6', '1', '2017-07-20 08:17:21');
INSERT INTO `bindinfo` VALUES ('3', '6', '2', '2017-07-06 08:17:25');

-- ----------------------------
-- Table structure for gateway
-- ----------------------------
DROP TABLE IF EXISTS `gateway`;
CREATE TABLE `gateway` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(40) NOT NULL,
  `gpasswd` varchar(40) NOT NULL,
  `gonline` int(11) NOT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gateway
-- ----------------------------
INSERT INTO `gateway` VALUES ('1', '1', '123', '1');
INSERT INTO `gateway` VALUES ('3', '2', '22', '0');

-- ----------------------------
-- Table structure for temhumlig
-- ----------------------------
DROP TABLE IF EXISTS `temhumlig`;
CREATE TABLE `temhumlig` (
  `thlid` int(11) NOT NULL AUTO_INCREMENT,
  `gid` int(11) NOT NULL,
  `temp` float NOT NULL,
  `humi` float NOT NULL,
  `light` float NOT NULL,
  `thltime` datetime NOT NULL,
  PRIMARY KEY (`thlid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of temhumlig
-- ----------------------------
INSERT INTO `temhumlig` VALUES ('1', '1', '24', '76', '500', '2017-07-15 10:49:37');
INSERT INTO `temhumlig` VALUES ('2', '1', '27', '76', '500', '2017-07-15 10:50:12');
INSERT INTO `temhumlig` VALUES ('3', '2', '28', '67', '478', '2017-07-11 08:11:48');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uemail` varchar(320) DEFAULT NULL,
  `uname` varchar(60) DEFAULT NULL,
  `upasswd` varchar(40) DEFAULT NULL,
  `uphone` varchar(11) DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', '860223178@qq.com', 'wfdsfsd', '7c4a8d09ca3762af61e59520943dc26494f8941b', '17854267528', '2017-06-02 17:16:19');
