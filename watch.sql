/*
 Navicat MySQL Data Transfer

 Source Server         : test-sneaker
 Source Server Version : 50546
 Source Host           : 115.28.8.173
 Source Database       : watch

 Target Server Version : 50546
 File Encoding         : utf-8

 Date: 03/28/2016 18:07:20 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `watch_alarm`
-- ----------------------------
DROP TABLE IF EXISTS `watch_alarm`;
CREATE TABLE `watch_alarm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mid` bigint(20) NOT NULL COMMENT '指标ID',
  `nid` bigint(20) NOT NULL COMMENT '命名空间ID',
  `threshold` double NOT NULL COMMENT '阀值',
  `tid` bigint(20) NOT NULL COMMENT '模板ID',
  `complare` tinyint(8) NOT NULL DEFAULT '0' COMMENT '比较ID',
  `namespace` varchar(1024) NOT NULL COMMENT '命名空间名称',
  `template` varchar(1024) NOT NULL COMMENT '模板名称',
  `metric` varchar(1024) NOT NULL COMMENT '指标名称',
  `name` varchar(1024) NOT NULL,
  `groups` varchar(1024) NOT NULL COMMENT '被通知者组名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `watch_alarm`
-- ----------------------------
BEGIN;
INSERT INTO `watch_alarm` VALUES ('4', '2', '2', '0', '4', '1', 'application_collect', '简单的通知', 'cpu.timeout', 'alarm1', '1,2,3');
COMMIT;

-- ----------------------------
--  Table structure for `watch_condition`
-- ----------------------------
DROP TABLE IF EXISTS `watch_condition`;
CREATE TABLE `watch_condition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) NOT NULL COMMENT '标题',
  `metric` varchar(1024) NOT NULL COMMENT '标题',
  `mid` bigint(20) NOT NULL COMMENT '指标标题',
  `week` tinyint(8) NOT NULL COMMENT '周期',
  `condition` varchar(4086) DEFAULT NULL COMMENT '查询条件',
  `nid` bigint(20) NOT NULL,
  `namespace` varchar(1024) NOT NULL COMMENT '命名空间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `watch_condition`
-- ----------------------------
BEGIN;
INSERT INTO `watch_condition` VALUES ('1', 'CPU查询', 'cpu.timeout', '2', '3', 'server', '2', 'application_collect'), ('3', 'CPU负载查询', 'cpu.load', '3', '2', 'server', '2', 'application_collect');
COMMIT;

-- ----------------------------
--  Table structure for `watch_contact`
-- ----------------------------
DROP TABLE IF EXISTS `watch_contact`;
CREATE TABLE `watch_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL COMMENT '姓名',
  `phone` varchar(16) NOT NULL COMMENT '手机号码',
  `email` varchar(64) NOT NULL COMMENT '邮件地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `watch_contact`
-- ----------------------------
BEGIN;
INSERT INTO `watch_contact` VALUES ('1', '吴红', '18684997340', 'x010@foxmail.com'), ('2', '张三', '18684997340', 'x010@foxmail.com'), ('3', '赵六', '18684997340', 'x010@foxmail.com');
COMMIT;

-- ----------------------------
--  Table structure for `watch_contact_group`
-- ----------------------------
DROP TABLE IF EXISTS `watch_contact_group`;
CREATE TABLE `watch_contact_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(1024) NOT NULL COMMENT '组件',
  `notic` tinyint(4) NOT NULL DEFAULT '3' COMMENT '通知者',
  `contacts` varchar(4089) NOT NULL COMMENT '通知者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `watch_contact_group`
-- ----------------------------
BEGIN;
INSERT INTO `watch_contact_group` VALUES ('1', '系统监控组', '3', '1,2,3'), ('2', '系统监控组', '3', '1,2,3'), ('3', '系统监控组', '3', '1,2,3');
COMMIT;

-- ----------------------------
--  Table structure for `watch_metric`
-- ----------------------------
DROP TABLE IF EXISTS `watch_metric`;
CREATE TABLE `watch_metric` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `metricname` varchar(1024) NOT NULL COMMENT '指标名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `watch_metric`
-- ----------------------------
BEGIN;
INSERT INTO `watch_metric` VALUES ('2', 'cpu.timeout'), ('3', 'cpu.load'), ('4', 'cpu.uptimes');
COMMIT;

-- ----------------------------
--  Table structure for `watch_metricrecord`
-- ----------------------------
DROP TABLE IF EXISTS `watch_metricrecord`;
CREATE TABLE `watch_metricrecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nid` bigint(20) NOT NULL COMMENT '命名空间ID',
  `namespace` varchar(1024) NOT NULL COMMENT '命名空间名称',
  `mid` bigint(20) NOT NULL COMMENT '指标ID',
  `metricname` varchar(1024) NOT NULL COMMENT '名称',
  `starttime` datetime NOT NULL COMMENT '开始时间',
  `endtime` datetime NOT NULL COMMENT '结束时间',
  `mvalue` double NOT NULL COMMENT '值',
  `mgroup` varchar(1026) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `watch_metricrecord`
-- ----------------------------
BEGIN;
INSERT INTO `watch_metricrecord` VALUES ('1', '2', 'application_collect', '2', 'cpu.timeout', '2016-03-28 17:56:28', '2016-03-28 17:52:28', '14', 'server2'), ('2', '2', 'application_collect', '2', 'cpu.timeout', '2016-03-28 17:56:29', '2016-03-26 23:12:28', '9', 'server'), ('3', '2', 'application_collect', '2', 'cpu.timeout', '2016-03-28 17:56:30', '2016-03-26 23:12:28', '16', 'server2'), ('4', '2', 'application_collect', '2', 'cpu.timeout', '2016-03-28 17:56:31', '2016-03-26 23:12:28', '31', 'server'), ('5', '2', 'application_collect', '2', 'cpu.timeout', '2016-03-28 17:56:32', '2016-03-26 23:12:28', '34', 'server2'), ('6', '2', 'application_collect', '2', 'cpu.timeout', '2016-03-28 17:56:33', '2016-03-26 23:12:28', '12', 'server'), ('7', '2', 'application_collect', '2', 'cpu.timeout', '2016-03-28 17:56:34', '2016-03-26 23:12:28', '18', 'server2');
COMMIT;

-- ----------------------------
--  Table structure for `watch_namespace`
-- ----------------------------
DROP TABLE IF EXISTS `watch_namespace`;
CREATE TABLE `watch_namespace` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(1024) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `watch_namespace`
-- ----------------------------
BEGIN;
INSERT INTO `watch_namespace` VALUES ('2', '2016-03-24 12:55:46', 'application_collect');
COMMIT;

-- ----------------------------
--  Table structure for `watch_template`
-- ----------------------------
DROP TABLE IF EXISTS `watch_template`;
CREATE TABLE `watch_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `watch_template`
-- ----------------------------
BEGIN;
INSERT INTO `watch_template` VALUES ('4', '简单的通知', '通知#{METRICNAME},#{VALUE},#{ALARMNAME}');
COMMIT;

-- ----------------------------
--  Table structure for `watch_user`
-- ----------------------------
DROP TABLE IF EXISTS `watch_user`;
CREATE TABLE `watch_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `watch_user`
-- ----------------------------
BEGIN;
INSERT INTO `watch_user` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
