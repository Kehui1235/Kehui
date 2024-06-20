/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 20/06/2024 15:50:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for warningrule
-- ----------------------------
DROP TABLE IF EXISTS `warningrule`;
CREATE TABLE `warningrule`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '序号',
  `ruleid` bigint UNSIGNED NOT NULL COMMENT '规则编号',
  `alarmname` int NULL DEFAULT NULL COMMENT '报警名称，0电压差，1电池差',
  `battery` int NULL DEFAULT NULL COMMENT '电池类型',
  `rule` int NOT NULL COMMENT '预警等级',
  `min` float NOT NULL COMMENT '最小值',
  `max` float NULL DEFAULT NULL COMMENT '最大值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `wb_id`(`ruleid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '警告电池规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warningrule
-- ----------------------------
INSERT INTO `warningrule` VALUES (1, 1, 0, 0, 0, 5, NULL);
INSERT INTO `warningrule` VALUES (2, 1, 0, 0, 1, 3, 5);
INSERT INTO `warningrule` VALUES (3, 1, 0, 0, 2, 1, 3);
INSERT INTO `warningrule` VALUES (4, 1, 0, 0, 3, 0.6, 1);
INSERT INTO `warningrule` VALUES (5, 1, 0, 0, 4, 0.2, 0.6);
INSERT INTO `warningrule` VALUES (6, 1, 0, 0, 5, 0, 0.2);
INSERT INTO `warningrule` VALUES (7, 1, 0, 1, 0, 2, NULL);
INSERT INTO `warningrule` VALUES (8, 1, 0, 1, 1, 1, 2);
INSERT INTO `warningrule` VALUES (9, 1, 0, 1, 2, 0.7, 1);
INSERT INTO `warningrule` VALUES (10, 1, 0, 1, 3, 0.4, 0.7);
INSERT INTO `warningrule` VALUES (11, 1, 0, 1, 4, 0.2, 0.4);
INSERT INTO `warningrule` VALUES (12, 1, 0, 1, 5, 0, 0.2);
INSERT INTO `warningrule` VALUES (13, 2, 1, 0, 0, 3, NULL);
INSERT INTO `warningrule` VALUES (14, 2, 1, 0, 1, 1, 3);
INSERT INTO `warningrule` VALUES (15, 2, 1, 0, 2, 0.2, 1);
INSERT INTO `warningrule` VALUES (16, 2, 1, 0, 3, 0, 0.2);
INSERT INTO `warningrule` VALUES (17, 2, 1, 1, 0, 1, NULL);
INSERT INTO `warningrule` VALUES (18, 2, 1, 1, 1, 0.5, 1);
INSERT INTO `warningrule` VALUES (19, 2, 1, 1, 2, 0.2, 0.5);
INSERT INTO `warningrule` VALUES (20, 2, 1, 1, 3, 0, 0.2);

SET FOREIGN_KEY_CHECKS = 1;
