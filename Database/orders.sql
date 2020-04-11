/*
 Navicat Premium Data Transfer

 Source Server         : Mydatabase
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : java_data

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 18/11/2019 21:59:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `flight_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_airport` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reach_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reach_airport` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乘车人姓名',
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '乘车人身份证',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乘车人手机',
  PRIMARY KEY (`date`, `flight_num`, `id`, `order_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('20191105103922', '2019-11-05', '春秋航空9C8960', '广州', '白云机场', '上海', '虹桥机场', 'aa1234', '小红', '511823198401108334', '13800138000');
INSERT INTO `orders` VALUES ('20191105104005', '2019-11-05', '春秋航空9C8960', '上海', '虹桥机场', '广州', '白云机场', 'aa1234', '小红', '511823198401108334', '13800138000');
INSERT INTO `orders` VALUES ('20191105175946', '2019-11-05', '航班22', '广州', '白云机场', '上海', '虹桥机场', 'a12345', '张三', '511823198401103892', '13800138122');
INSERT INTO `orders` VALUES ('20191105100101', '2019-11-06', '春秋航空9C8960', '上海', '虹桥机场', '广州', '白云机场', 'a12345', '张三', '511823198401103892', '13800138123');

SET FOREIGN_KEY_CHECKS = 1;
