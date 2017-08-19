/*
Navicat MySQL Data Transfer

Source Server         : xcc
Source Server Version : 50716
Source Host           : 118.178.234.162:3306
Source Database       : xcc_dev

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-04-28 19:17:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for xcc_app_upload_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_app_upload_t`;
CREATE TABLE `xcc_app_upload_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imsi` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `imei` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `app_name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `app_package_name` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT 'app包名',
  `app_trafficExpend` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '流量消耗',
  `app_upload_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上报时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_app_version_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_app_version_t`;
CREATE TABLE `xcc_app_version_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_version` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '当前版本号',
  `app_upgrade_type` int(2) NOT NULL COMMENT '升级方式(1:普通更新；2：强制更新)',
  `app_download_url` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '下载地址',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for xcc_buy_history_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_buy_history_t`;
CREATE TABLE `xcc_buy_history_t` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `pay_imsi_id` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '充值的imsi唯一id',
  `pay_package_type` int(2) NOT NULL COMMENT '购买的套餐类型',
  `pay_type` int(2) NOT NULL COMMENT '支付方式（1：自己支付；2：找人代付）',
  `pay_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
  `pay_money` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '付款金额',
  `order_no` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '订单号',
  `other_pay_openid` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '代付人的openid',
  `order_status` int(2) NOT NULL DEFAULT '1' COMMENT '订单状态（1：下单；2：支付成功）',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=346 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for xcc_cardattr_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_cardattr_t`;
CREATE TABLE `xcc_cardattr_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iccid` varchar(20) COLLATE utf8_bin NOT NULL,
  `nowdate` date NOT NULL COMMENT '当前月份',
  `nowpackage` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '当月套餐',
  `play_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '生效时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `iccid_u` (`iccid`) USING BTREE,
  KEY `iccid_s` (`iccid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=516 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_cardout_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_cardout_t`;
CREATE TABLE `xcc_cardout_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_out_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '卡出库时间',
  `card_out_mchname` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '卡出库厂商名字',
  `card_out_mchno` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '卡出库厂商编号',
  `staff_price` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '销售人员的价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_cardput_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_cardput_t`;
CREATE TABLE `xcc_cardput_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iccid` varchar(20) COLLATE utf8_bin NOT NULL,
  `cardid` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '卡号',
  `operator` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '所属运营商',
  `pay_type` int(2) DEFAULT NULL COMMENT '付费方式（1:月付；2：预付）',
  `silence_date` date DEFAULT NULL COMMENT '沉默期截止时间',
  `silence` int(3) DEFAULT NULL COMMENT '沉默期时长单位 ：天',
  `take_card_date` date DEFAULT NULL COMMENT '拿卡时间',
  `outbound_id` int(11) DEFAULT NULL COMMENT '出库厂商编号',
  `staff` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '销售人员',
  `test_status` int(2) DEFAULT NULL COMMENT 'sm卡的测试状态(1：没检测；2：通过；3：失败)',
  `flow_pool_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '所属流量池',
  `test__error_msg` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '测试失败原因',
  `play_card_date` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '开卡时间',
  `card_test_date` date DEFAULT NULL COMMENT '测试期截止时间',
  `card_test` int(3) DEFAULT NULL COMMENT '测试期周期 单位：天',
  `billing_cycle` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '计费周期',
  `package_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '套餐类型（流量包、流量池）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `iccid_u` (`iccid`) USING BTREE,
  UNIQUE KEY `cardid_u` (`cardid`) USING BTREE,
  KEY `silence_s` (`silence`) USING BTREE,
  KEY `iccid_s` (`iccid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3631 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_flow_pool_record_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_flow_pool_record_t`;
CREATE TABLE `xcc_flow_pool_record_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flow_pool_id` varchar(20) COLLATE utf8_bin NOT NULL,
  `now_date` date DEFAULT NULL COMMENT '当前月',
  `change_before_package` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '当月套餐（当月更改前的套餐）',
  `change_after_package` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '当月更改后的套餐',
  `effect_time` date DEFAULT NULL COMMENT '预计生效时间',
  `change_type` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '更改方式（1：直接修改流量池套餐；2：更改单卡套餐；3：叠加包；4：增减卡）',
  `flow_pool_submit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '提交时间',
  `flow_pool_status` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '提交状态',
  `folw_pool_operator` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '操作人',
  `actual_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '实际生效时间',
  PRIMARY KEY (`id`),
  KEY `flow_pool_i` (`flow_pool_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_flow_pool_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_flow_pool_t`;
CREATE TABLE `xcc_flow_pool_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flow_pool_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '当月时间',
  `flow_pool_id` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '流量池id',
  `flow_pool_package` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '流量池套餐',
  `flow_pool_type` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '流量池付费方式',
  `flow_pool_money` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '流量池套餐费用',
  `flow_pool_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '流量池名称',
  `flow_pool_create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '流量池创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `flow_pool_u` (`flow_pool_id`) USING BTREE,
  KEY `flow_pool_s` (`flow_pool_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_Illegal_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_Illegal_t`;
CREATE TABLE `xcc_Illegal_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(40) COLLATE utf8_bin NOT NULL,
  `classno` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '车架号',
  `hphm` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '车牌号码',
  `engineno` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '发动机号',
  `imsi` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_imsi_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_imsi_t`;
CREATE TABLE `xcc_imsi_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imsi_id` varchar(40) COLLATE utf8_bin NOT NULL COMMENT 'imsi唯一id',
  `imei` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '车载设备唯一id',
  `bus_type` int(2) DEFAULT NULL COMMENT '车辆类型：1：运营车辆；2：私用；3：其他.....',
  `user_phone` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '激活车主的手机号',
  `imsi_re_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '激活时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `imsi_uq` (`imsi_id`) USING BTREE,
  UNIQUE KEY `imei_uq` (`imei`) USING BTREE,
  UNIQUE KEY `imsi_imei_uq` (`imsi_id`,`imei`) USING BTREE,
  KEY `imsi_index` (`imsi_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=338 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for xcc_navigation_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_navigation_t`;
CREATE TABLE `xcc_navigation_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imsi` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `type` int(2) DEFAULT NULL COMMENT '1:图片；2：视频',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_package_limiting_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_package_limiting_t`;
CREATE TABLE `xcc_package_limiting_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `package_type` int(2) NOT NULL COMMENT '套餐类型',
  `if_video` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '是否允许视频',
  `if_download` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '是否允许下载',
  `if_webpage` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '是否允许浏览网页',
  `upload_speed` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '上传速度',
  `download_speed` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '下载速度',
  `max_user_num` int(3) NOT NULL COMMENT '最大用户连接数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `package_type_qu` (`package_type`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

INSERT INTO `xcc_package_limiting_t` VALUES ('1', '0', '2', '2', '1', '100M/s', '100M/s', '10');
INSERT INTO `xcc_package_limiting_t` VALUES ('2', '1', '2', '2', '1', '100M/S', '100M/S', '10');
INSERT INTO `xcc_package_limiting_t` VALUES ('3', '2', '1', '2', '1', '100M/S', '100M/S', '10');
-- ----------------------------
-- Table structure for xcc_package_msg_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_package_msg_t`;
CREATE TABLE `xcc_package_msg_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `package_type` int(2) NOT NULL DEFAULT '0' COMMENT '套餐类型（0：默认的套餐）',
  `package_price` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '套餐价格',
  `traffic_ceiling` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '普通流量上限',
  `day_ceiling` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '套餐时长（单位：月）',
  `package_status` int(2) NOT NULL COMMENT '是否有效（1：有效；2：无效）',
  `package_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '套餐名称',
  `wifi_ceiling` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '套餐wifi时长',
  `package_photo` varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '套餐图片',
  PRIMARY KEY (`id`),
  UNIQUE KEY `package_type_qu` (`package_type`) USING BTREE,
  KEY `package_type_index` (`package_type`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;


INSERT INTO `xcc_package_msg_t` VALUES ('1', '0', '0', '-', '1', '1', '免费试用流量', '100', '');
INSERT INTO `xcc_package_msg_t` VALUES ('2', '1', '198', '-', '12', '1', '标准年费套餐', '100', 'http://oixlf0mzw.bkt.clouddn.com/defaultPackage.png');
INSERT INTO `xcc_package_msg_t` VALUES ('3', '2', '598', '-', '12', '1', '尊享年费套餐', '-', 'http://oixlf0mzw.bkt.clouddn.com/kingPackage.png');

-- ----------------------------
-- Table structure for xcc_share_content_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_share_content_t`;
CREATE TABLE `xcc_share_content_t` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `mch_no` varchar(40) COLLATE utf8_bin NOT NULL,
  `openid` varchar(200) COLLATE utf8_bin NOT NULL,
  `imsi` varchar(40) COLLATE utf8_bin NOT NULL,
  `binding_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `if_staff_rebate` int(2) NOT NULL DEFAULT '1' COMMENT '销售返利状态    1:没有返利；2：已经返利',
  `if_mch_rebate` int(2) NOT NULL DEFAULT '1' COMMENT '商家返利状态    1:没有返利；2：已经返利',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_share_profit_mch_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_share_profit_mch_t`;
CREATE TABLE `xcc_share_profit_mch_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mch_name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '分利商家名称',
  `mch_user_name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '商家注册人的姓名',
  `mch_user_phone` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '商家注册人手机号',
  `brand_no` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '品牌编号',
  `mch_no` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '商家编号',
  `mch_address` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '分利商家地址',
  `mch_user_openid` varchar(200) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mch_id` (`mch_name`,`mch_address`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_share_profit_staff_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_share_profit_staff_t`;
CREATE TABLE `xcc_share_profit_staff_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mch_no` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '销售人员对应的商家编号',
  `staff_name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '销售人员姓名',
  `staff_phone` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '销售人员手机号',
  `imsi_id_list` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '销售人员绑定的imsi号',
  `staff_user_openid` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `one_index` (`mch_no`,`staff_phone`,`staff_user_openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_traffic_record_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_traffic_record_t`;
CREATE TABLE `xcc_traffic_record_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iccid` varchar(20) COLLATE utf8_bin NOT NULL,
  `msg` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '车主订购套餐截止当前日期流量详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_trajectory_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_trajectory_t`;
CREATE TABLE `xcc_trajectory_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trajectory_lat` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '轨迹的维度',
  `trajectory_lng` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '轨迹的精度',
  `time` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `imsi` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6527 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for xcc_user_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_user_t`;
CREATE TABLE `xcc_user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `uuid` varchar(40) COLLATE utf8_bin NOT NULL COMMENT 'UUID（后期作为推送或者IM字段）',
  `weixin_opend_id` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '激活车主在公众号下的唯一id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_qu` (`phone`) USING BTREE,
  UNIQUE KEY `uuid_qu` (`uuid`) USING BTREE,
  UNIQUE KEY `weixin_qu` (`weixin_opend_id`) USING BTREE,
  UNIQUE KEY `user_weinxin_qu` (`phone`,`weixin_opend_id`) USING BTREE,
  KEY `user_index` (`weixin_opend_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=296 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for xcc_user_traffic_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_user_traffic_t`;
CREATE TABLE `xcc_user_traffic_t` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `imsi_id` varchar(40) COLLATE utf8_bin NOT NULL COMMENT 'imsi唯一id',
  `card_package_type_id` int(2) NOT NULL DEFAULT '0' COMMENT '用户当前套餐类型',
  `failure_time` date NOT NULL COMMENT '套餐失效时间',
  `apply_traffic_num` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '该套餐当前使用流量值(默认0)',
  `wifi_length` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '已使用wifi时长',
  `pay_play_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `package_status` int(2) NOT NULL DEFAULT '1' COMMENT '套餐状态（1：已购买；2：过期）',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=295 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for xcc_warning_t
-- ----------------------------
DROP TABLE IF EXISTS `xcc_warning_t`;
CREATE TABLE `xcc_warning_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `warning_no` int(11) NOT NULL COMMENT '预警编号',
  `warning_title` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '预警标题',
  `warning_content` varchar(2000) COLLATE utf8_bin NOT NULL COMMENT '预警内容',
  `warning_mail` varchar(2000) COLLATE utf8_bin NOT NULL COMMENT '预警邮箱',
  `warning_mobile` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '预警的手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `warning_no_u` (`warning_no`) USING BTREE,
  KEY `warning_no_s` (`warning_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Function structure for currval
-- ----------------------------
DROP FUNCTION IF EXISTS `currval`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN  
         DECLARE value INTEGER;  
         SET value = 0;  
         SELECT current_value INTO value  
                   FROM sequence  
                   WHERE name = seq_name;  
         RETURN value;  
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for nextval
-- ----------------------------
DROP FUNCTION IF EXISTS `nextval`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
BEGIN  
         UPDATE sequence  
                   SET current_value = current_value + increment  
                   WHERE name = seq_name;  
         RETURN currval(seq_name);  
END
;;
DELIMITER ;
