/*
 Navicat Premium Data Transfer

 Source Server         : 本地7
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : graduation

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 25/10/2020 23:47:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `file_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件路径',
  `creator_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者工号',
  `school_year` char(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学年',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bulletin
-- ----------------------------

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `college_no` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院编号',
  `college_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院名称',
  `college_manager_no` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院负责人编号',
  `college_manager_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院负责人名字',
  `parent_no` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学院信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '2000', '软件学院', '8000442', 'zyx', NULL);
INSERT INTO `college` VALUES (2, '2001', '软件工程', '8000443', 'yhb', '2000');
INSERT INTO `college` VALUES (3, '2002', '信息安全', '8000445', 'yjg', '2000');
INSERT INTO `college` VALUES (4, '3000', '计算机学院', '8000224', 'yjt', NULL);
INSERT INTO `college` VALUES (5, '3001', '计算机信息技术', '8000224', 'yjt', '3000');
INSERT INTO `college` VALUES (6, '4000', '艺术学院', '8001542', 'gts', NULL);
INSERT INTO `college` VALUES (7, '4001', '油画', '8001654', 'rft', '4000');
INSERT INTO `college` VALUES (8, '4002', '水彩', '8001562', 'gyn', '4000');

-- ----------------------------
-- Table structure for exam_group
-- ----------------------------
DROP TABLE IF EXISTS `exam_group`;
CREATE TABLE `exam_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_num` int(11) NOT NULL,
  `leader_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组长编号',
  `member_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组员编号, 用英文逗号隔开',
  `school_year` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学年',
  `college` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '答辩小组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_group
-- ----------------------------

-- ----------------------------
-- Table structure for foreign_literature
-- ----------------------------
DROP TABLE IF EXISTS `foreign_literature`;
CREATE TABLE `foreign_literature`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题编号',
  `is_pass` tinyint(4) NULL DEFAULT 2,
  `foreign_file` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外文文件路径',
  `translation_file` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '译文文件路径',
  `trial_grade` tinyint(4) NULL DEFAULT NULL COMMENT '指导老师审核评分',
  `trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指导老师审核评语',
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  `modifiable` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '外文资料数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of foreign_literature
-- ----------------------------

-- ----------------------------
-- Table structure for foreign_record
-- ----------------------------
DROP TABLE IF EXISTS `foreign_record`;
CREATE TABLE `foreign_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外文数据唯一编号',
  `foreign_file` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外文文件路径',
  `translation_file` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '译文文件路径',
  `is_pass` tinyint(4) NULL DEFAULT NULL,
  `trial_grade` tinyint(4) NOT NULL,
  `trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmt_create` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '外文审核记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of foreign_record
-- ----------------------------

-- ----------------------------
-- Table structure for open_report
-- ----------------------------
DROP TABLE IF EXISTS `open_report`;
CREATE TABLE `open_report`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题编号',
  `file_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `is_pass` tinyint(4) NULL DEFAULT 2,
  `trial_grade` tinyint(4) NULL DEFAULT NULL,
  `trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blind_trial_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盲审人编号',
  `blind_trial_grade` tinyint(4) NULL DEFAULT NULL COMMENT '盲审人审核成绩',
  `blind_trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盲审人审核评语',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  `modifiable` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '开题报告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of open_report
-- ----------------------------

-- ----------------------------
-- Table structure for open_report_record
-- ----------------------------
DROP TABLE IF EXISTS `open_report_record`;
CREATE TABLE `open_report_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_pass` tinyint(4) NULL DEFAULT NULL,
  `trial_grade` tinyint(4) NOT NULL,
  `trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmt_create` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '开题报告记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of open_report_record
-- ----------------------------

-- ----------------------------
-- Table structure for oral_exam_score
-- ----------------------------
DROP TABLE IF EXISTS `oral_exam_score`;
CREATE TABLE `oral_exam_score`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sno` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生学号',
  `tno` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师工号',
  `score` tinyint(4) NOT NULL COMMENT '成绩',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '答辩每名老师成绩' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oral_exam_score
-- ----------------------------

-- ----------------------------
-- Table structure for project_apply
-- ----------------------------
DROP TABLE IF EXISTS `project_apply`;
CREATE TABLE `project_apply`  (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题唯一id',
  `pname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题名字',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题附件路径',
  `type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题类型',
  `tags` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题标签',
  `college` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院',
  `creator_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者编号',
  `is_pass` tinyint(4) NULL DEFAULT 2 COMMENT '课题是否通过',
  `is_select` tinyint(4) NULL DEFAULT 0,
  `blind_trial_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盲审者工号',
  `blind_trial_grade` tinyint(4) NULL DEFAULT NULL COMMENT '盲审者评分成绩',
  `blind_trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盲审者评语',
  `trial_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人id',
  `trial_grade` tinyint(4) NULL DEFAULT NULL,
  `trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人评语',
  `school_year` char(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学年',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  `is_delete` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课题申请' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_apply
-- ----------------------------

-- ----------------------------
-- Table structure for project_plan
-- ----------------------------
DROP TABLE IF EXISTS `project_plan`;
CREATE TABLE `project_plan`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_apply_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课题申请起止时间',
  `project_select_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '师生互选起止时间',
  `task_book_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务书起止时间',
  `open_report_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开题报告起止时间',
  `foreign_literature_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外文资料起止时间',
  `oral_examination_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答辩起止时间',
  `thesis_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '论文起止时间',
  `school_year` char(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学年',
  `score_composition` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guidance_record_time` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '毕设计划时间' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_plan
-- ----------------------------
INSERT INTO `project_plan` VALUES (1, '2020-06-04 00:00:00,2020-06-08 12:51:00', '2020-06-05 00:00:00,2020-06-12 23:59:59', '2020-06-05 00:00:00,2020-06-12 23:59:59', '2020-06-05 00:00:00,2020-06-06 14:11:00', '2020-06-05 00:00:00,2020-06-12 23:59:59', '2020-06-05 00:00:00,2020-06-12 23:59:59', '2020-06-05 00:00:00,2020-06-09 17:31:30', '2020-2021', '30%,30%,40%', NULL);

-- ----------------------------
-- Table structure for project_select
-- ----------------------------
DROP TABLE IF EXISTS `project_select`;
CREATE TABLE `project_select`  (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题号',
  `selector_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选题者学号/工号',
  `is_select` tinyint(4) NOT NULL DEFAULT 2 COMMENT '选题申请的状态  2待审核 1选题成功 0选题被拒',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '师生互选信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_select
-- ----------------------------

-- ----------------------------
-- Table structure for project_select_result
-- ----------------------------
DROP TABLE IF EXISTS `project_select_result`;
CREATE TABLE `project_select_result`  (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题唯一编号',
  `tno` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师工号',
  `sno` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生号',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `no_UNIQUE`(`pno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '选题结果' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_select_result
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `sno` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `sname` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `college` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院',
  `major` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业',
  `grade_class` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级',
  `school_year` char(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` tinyint(4) NULL DEFAULT NULL COMMENT '学生最终成绩',
  `group_num` int(11) NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `student_id_UNIQUE`(`sno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for task_book
-- ----------------------------
DROP TABLE IF EXISTS `task_book`;
CREATE TABLE `task_book`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题号',
  `file_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务书' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_book
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` bigint(1) NOT NULL AUTO_INCREMENT,
  `tno` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `tname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `college` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院',
  `role` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lead_student_num` tinyint(4) NOT NULL DEFAULT 0,
  `group_num` int(11) NULL DEFAULT NULL COMMENT '该老师最新答辩小组组号',
  `group_role` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该老师最新小组角色',
  `school_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该老师最新负责的学年',
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tno_UNIQUE`(`tno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '老师信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '8000117', 'xwz', '5b808a388d2ef80ba88c792856095d7f', '2000', 'admin', '教授', 0, NULL, NULL, '2020-2021,2019-2020,2018-2019', '2020-03-15 00:00:00', '2020-03-15 00:00:00');

-- ----------------------------
-- Table structure for thesis
-- ----------------------------
DROP TABLE IF EXISTS `thesis`;
CREATE TABLE `thesis`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题号',
  `file_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `is_pass` tinyint(4) NULL DEFAULT 2,
  `trial_grade` tinyint(4) NULL DEFAULT NULL,
  `trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blind_trial_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blind_trial_grade` tinyint(4) NULL DEFAULT NULL,
  `blind_trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  `modifiable` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '论文' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of thesis
-- ----------------------------

-- ----------------------------
-- Table structure for thesis_record
-- ----------------------------
DROP TABLE IF EXISTS `thesis_record`;
CREATE TABLE `thesis_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '论文编号',
  `file_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `is_pass` tinyint(4) NULL DEFAULT NULL,
  `trial_grade` tinyint(4) NOT NULL,
  `trial_comment` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmt_create` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '论文审核记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of thesis_record
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
