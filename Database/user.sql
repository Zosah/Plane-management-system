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

 Date: 18/11/2019 21:59:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('a12345', 'a12345', '张三', '511823198401103892', '13800138122');
INSERT INTO `user` VALUES ('aa1234', 'aa1234', '小红', '511823198401108334', '13800138000');
INSERT INTO `user` VALUES ('admin', 'admin', '管理员', '440923199710121496', '13104997306');
INSERT INTO `user` VALUES ('b12345', 'bbbbbb', '李四', '511823198401109097', '12345678910');
INSERT INTO `user` VALUES ('c12345', 'c12345', '小C', '511823198401109871', '13800138000');
INSERT INTO `user` VALUES ('d12345', 'd12345', '花花', '511823198401109150', '13544551200');
INSERT INTO `user` VALUES ('dd1234', 'dd1234', '弟弟', '511823198401103891', '13800138000');
INSERT INTO `user` VALUES ('e12345', 'e12345', '小e', '511823198401109353', '13104997306');
INSERT INTO `user` VALUES ('qq0000', '000000', '小明', '511823198401108350', '13800138000');

SET FOREIGN_KEY_CHECKS = 1;
