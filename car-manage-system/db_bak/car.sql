/*
 Navicat Premium Data Transfer

 Source Server         : godric
 Source Server Type    : MySQL
 Source Server Version : 50647
 Source Host           : 120.27.242.116:3306
 Source Schema         : car

 Target Server Type    : MySQL
 Target Server Version : 50647
 File Encoding         : 65001

 Date: 05/05/2020 12:03:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car_model
-- ----------------------------
DROP TABLE IF EXISTS `car_model`;
CREATE TABLE `car_model`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `model_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '型号名',
  `stock` int(5) NOT NULL COMMENT '库存',
  `desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `main_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主图url',
  `detail_image_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '详情图url，逗号分隔',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of car_model
-- ----------------------------
INSERT INTO `car_model` VALUES (1, 'aaa,', 99, '', 'aaa', 'aaa,bbb,vvv,ccc,ddd,eee,fff,hhh', '2020-04-28 10:56:06', '2020-04-29 13:42:00');
INSERT INTO `car_model` VALUES (2, 'bbb,', 100, '', 'aaa', 'aaa,bbb,vvv,ccc,ddd,eee,fff,hhh', '2020-04-28 10:59:17', '2020-04-29 13:42:01');
INSERT INTO `car_model` VALUES (3, 'ccc,', 99, '', 'aaa', 'aaa,bbb,vvv,ccc,ddd,eee,fff,hhh', '2020-04-28 10:59:23', '2020-04-29 13:42:02');
INSERT INTO `car_model` VALUES (4, 'ddd,', 99, '', 'aa', 'aaa,bbb,vvv,ccc,ddd,eee,fff,hhh', '2020-04-28 10:59:34', '2020-04-29 13:42:02');
INSERT INTO `car_model` VALUES (5, 'eee,', 99, '', 'aaa', 'aaa,bbb,vvv,ccc,ddd,eee,fff,hhh', '2020-04-28 10:59:37', '2020-04-29 13:42:02');
INSERT INTO `car_model` VALUES (6, 'fff,', 100, '', 'aaa', 'aaa,bbb,vvv,ccc,ddd,eee,fff,hhh', '2020-04-28 10:59:40', '2020-04-29 13:42:03');
INSERT INTO `car_model` VALUES (7, 'ggg,', 100, '', 'aaa', 'aaa,bbb,vvv,ccc,ddd,eee,fff,hhh', '2020-04-28 10:59:44', '2020-04-29 13:42:03');
INSERT INTO `car_model` VALUES (8, 'demoData', 0, '', 'aaa', 'aaa,bbb,vvv,ccc,ddd,eee,fff,hhh', '2020-04-28 11:08:58', '2020-04-29 13:42:04');

-- ----------------------------
-- Table structure for pre_order_record
-- ----------------------------
DROP TABLE IF EXISTS `pre_order_record`;
CREATE TABLE `pre_order_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `car_model_id` int(11) NOT NULL COMMENT '车型id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pre_order_record
-- ----------------------------
INSERT INTO `pre_order_record` VALUES (3, 3, 1, '2020-04-29 14:08:41', '2020-04-29 14:08:41');
INSERT INTO `pre_order_record` VALUES (4, 4, 1, '2020-04-29 14:08:44', '2020-04-29 14:08:44');
INSERT INTO `pre_order_record` VALUES (5, 5, 1, '2020-04-29 14:08:48', '2020-04-29 14:08:48');
INSERT INTO `pre_order_record` VALUES (6, 8, 1, '2020-04-29 14:10:05', '2020-04-29 14:10:05');
INSERT INTO `pre_order_record` VALUES (8, 1, 9, '2020-05-05 01:18:58', '2020-05-05 01:18:58');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE COMMENT '用户名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '1111111', '张三', '1232323232', '2020-04-30 10:50:28', '2020-05-02 22:22:13');
INSERT INTO `user` VALUES (3, '李四', '111111', '李四', '11111111', '2020-05-02 21:08:09', '2020-05-02 21:08:09');
INSERT INTO `user` VALUES (4, '王五', '111111', '王五', '1111112222', '2020-05-02 21:10:19', '2020-05-02 21:10:19');
INSERT INTO `user` VALUES (5, '哈哈', 'hhhhhhhhh', 'haha', '1111111111', '2020-05-02 21:12:58', '2020-05-02 21:12:58');
INSERT INTO `user` VALUES (6, 'qwe', '12121212', 'qwe', '111222333', '2020-05-02 21:18:07', '2020-05-02 21:18:07');
INSERT INTO `user` VALUES (8, 'demoData', 'demoData', 'demoData', 'demoData', '2020-05-04 21:37:51', '2020-05-04 21:37:51');
INSERT INTO `user` VALUES (9, '11', '111111', '1111', '11111111', '2020-05-04 22:15:24', '2020-05-04 22:15:24');
INSERT INTO `user` VALUES (10, '22', '222222', '2222', '22222222', '2020-05-04 22:42:01', '2020-05-04 22:42:01');
INSERT INTO `user` VALUES (11, '33', '333333', '3333', '33333333', '2020-05-04 22:43:43', '2020-05-04 22:43:43');

SET FOREIGN_KEY_CHECKS = 1;
