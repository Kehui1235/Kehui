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

 Date: 20/06/2024 15:45:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '车架编号',
  `vid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车辆唯一标识',
  `battery_id` int NOT NULL COMMENT '电池类型，0三元，1铁锂',
  `milage` int NOT NULL COMMENT '公里数',
  `health_status` int NOT NULL COMMENT '电池健康状态（%）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `vid`(`vid` ASC) USING BTREE,
  INDEX `vid_2`(`vid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '车辆表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (1, 'rdrdrd', 0, 100, 100);
INSERT INTO `car` VALUES (2, 'tr4ey', 1, 600, 95);
INSERT INTO `car` VALUES (3, 'tydy', 0, 300, 98);

SET FOREIGN_KEY_CHECKS = 1;
