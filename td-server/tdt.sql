-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tdt
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tdt_ct_testcase`
--

DROP TABLE IF EXISTS `tdt_ct_testcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_ct_testcase` (
  `uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `parentUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parentPath` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `number` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `stage` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `autoType` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `testType` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `activity` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastResult` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_ct_testitem`
--

DROP TABLE IF EXISTS `tdt_ct_testitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_ct_testitem` (
  `uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `parentUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parentPath` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `number` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isFeature` char(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_ct_testitem_r_testcase`
--

DROP TABLE IF EXISTS `tdt_ct_testitem_r_testcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_ct_testitem_r_testcase` (
  `testitem_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `testcase_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_ct_testresult`
--

DROP TABLE IF EXISTS `tdt_ct_testresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_ct_testresult` (
  `testCaseUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `iterOrTaskUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `lastResult` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `updateTime` bigint(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_ct_testtask`
--

DROP TABLE IF EXISTS `tdt_ct_testtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_ct_testtask` (
  `uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `stage` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `taskType` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tdt_ct_testtaskcol` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_ct_testtask_r_testcase`
--

DROP TABLE IF EXISTS `tdt_ct_testtask_r_testcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_ct_testtask_r_testcase` (
  `testtask_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `testcase_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`testtask_uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_ct_testversion`
--

DROP TABLE IF EXISTS `tdt_ct_testversion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_ct_testversion` (
  `uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `parentUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parentPath` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `pbiId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isIterator` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `serviceName` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `isMaster` char(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_ct_testversion_r_testcase`
--

DROP TABLE IF EXISTS `tdt_ct_testversion_r_testcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_ct_testversion_r_testcase` (
  `testversion_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `testcase_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_milestone`
--

DROP TABLE IF EXISTS `tdt_milestone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_milestone` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` bigint(64) DEFAULT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_pbiversion`
--

DROP TABLE IF EXISTS `tdt_pbiversion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_pbiversion` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cn` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `en` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parentId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_project`
--

DROP TABLE IF EXISTS `tdt_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_project` (
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `parentProjectId` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `desc` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mode` int(11) DEFAULT NULL,
  `creatorId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creatorName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pbiId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `testVersionUri` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_project_r_user`
--

DROP TABLE IF EXISTS `tdt_project_r_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_project_r_user` (
  `project_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_role_type` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT '0:创建者、1:测试经理、2:TSE、3:TE、4:游客 '
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_role`
--

DROP TABLE IF EXISTS `tdt_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_role` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` char(1) COLLATE utf8_unicode_ci NOT NULL COMMENT '1:测试经理、2:TSE、3:TE、4:游客 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_stage`
--

DROP TABLE IF EXISTS `tdt_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_stage` (
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` bigint(64) DEFAULT NULL,
  `endTime` bigint(64) DEFAULT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_team`
--

DROP TABLE IF EXISTS `tdt_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_team` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_team_r_user`
--

DROP TABLE IF EXISTS `tdt_team_r_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_team_r_user` (
  `team_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_testplan`
--

DROP TABLE IF EXISTS `tdt_testplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_testplan` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parentId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `startTime` bigint(64) DEFAULT NULL,
  `endTime` bigint(64) DEFAULT NULL,
  `realEndTime` bigint(64) DEFAULT NULL,
  `estimatedWorkload` double DEFAULT NULL,
  `realWorkload` double DEFAULT NULL,
  `progress` double DEFAULT NULL,
  `isExpired` tinyint(4) DEFAULT NULL,
  `creatorId` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `creatorName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `customField1` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `totalCasesNum` int(11) DEFAULT NULL,
  `totalAssignedCasesNum` int(11) DEFAULT NULL,
  `totalExecutedCasesNum` int(11) DEFAULT NULL,
  `testVersionUri` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_testplan_r_testcase`
--

DROP TABLE IF EXISTS `tdt_testplan_r_testcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_testplan_r_testcase` (
  `testplan_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `testcase_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_testplan_r_user`
--

DROP TABLE IF EXISTS `tdt_testplan_r_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_testplan_r_user` (
  `testplan_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tdt_user`
--

DROP TABLE IF EXISTS `tdt_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdt_user` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-02 17:45:43
