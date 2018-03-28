use tdt;

DROP TABLE IF EXISTS `tdt_ct_testcase`;
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
  `featureUri` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createdTime` bigint(20) NOT NULL,
  `updatedTime` bigint(20) NOT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE_INDEX` (`uri`,`parentUri`,`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tdt_ct_testitem`;
CREATE TABLE `tdt_ct_testitem` (
  `uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `parentUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parentPath` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `number` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isFeature` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `createdTime` bigint(20) NOT NULL,
  `updatedTime` bigint(20) NOT NULL,
  `versionUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE_INDEX` (`uri`,`versionUri`,`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tdt_ct_testresult`;
CREATE TABLE `tdt_ct_testresult` (
  `testCaseUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `iterOrTaskUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `lastResult` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `branchUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '迭代或任务名称',
  `createdTime` bigint(20) NOT NULL,
  `updatedTime` bigint(20) NOT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE_KEY` (`testCaseUri`,`iterOrTaskUri`,`type`,`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tdt_ct_testtask`;
CREATE TABLE `tdt_ct_testtask` (
  `uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `stage` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `taskType` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `iteratorUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `branchUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `createdTime` bigint(20) NOT NULL,
  `updatedTime` bigint(20) NOT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE` (`uri`,`branchUri`,`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tdt_ct_testtask_r_testcase`;
CREATE TABLE `tdt_ct_testtask_r_testcase` (
  `testtask_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `testcase_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`testtask_uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tdt_ct_testversion`;
CREATE TABLE `tdt_ct_testversion` (
  `uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `parentUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parentPath` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `pbiId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isIterator` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `serviceName` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `isMaster` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `createdTime` bigint(20) NOT NULL,
  `updatedTime` bigint(20) NOT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE_INDEX` (`uri`,`parentUri`,`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_milestone`;
CREATE TABLE `tdt_milestone` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` bigint(64) DEFAULT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_pbiversion`;
CREATE TABLE `tdt_pbiversion` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cn` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `en` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parentId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_privilege`;
CREATE TABLE `tdt_privilege` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parentId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `tdt_privilege` WRITE;
INSERT INTO `tdt_privilege` VALUES ('TDT_PRIVILEGE_MEM_CONFIG','人员配置',NULL,NULL),('TDT_PRIVILEGE_PRJ_PLAN_MGR','项目计划管理',NULL,NULL),('TDT_PRIVILEGE_RES_CREATE','资源创建',NULL,NULL),('TDT_PRIVILEGE_RES_DELETE','资源删除',NULL,NULL),('TDT_PRIVILEGE_RES_MODIFY','资源修改',NULL,NULL),('TDT_PRIVILEGE_RES_VIEW','资源查看',NULL,NULL);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tdt_project`;
CREATE TABLE `tdt_project` (
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `projectName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `projectDesc` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `serviceMode` tinyint(1) NOT NULL,
  `creatorId` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `creatorName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pbiId` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `testVersionUri` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isArchived` tinyint(1) DEFAULT '0',
  `createdTime` bigint(20) DEFAULT NULL,
  `updatedTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `tdt_project` WRITE;
INSERT INTO `tdt_project` VALUES ('14d9e2ed-8dde-4280-ba70-51376a50d7d1','project_7','',0,'xwx539899','肖曾成','',NULL,0,201803151441,20180315172737195),('2f78cace-f555-44dc-9170-c675ebd44fb2','project_3','',0,'xwx539899','肖曾成','',NULL,0,201803141652,201803141652),('34816c56-23b7-4bfc-ab79-8f722bca1f91','project_6','',0,'xwx539899','肖曾成','',NULL,0,201803160957,201803160957),('39b3e3ec-8d4d-441c-bef1-6a032f86df30','project_5','',0,'xwx539899','肖曾成','',NULL,0,201803151301,201803151301),('49654f20-bfb1-4a51-a238-a5d45840b4fb','project_6','',0,'xwx539899','肖曾成','',NULL,0,201803160958,201803160958),('791de133-d85d-48d9-b14f-cf646c03462f','project_1',NULL,0,'xwx539899','肖曾成',NULL,NULL,0,201803132058,201803132058),('bdef0781-4e8c-4ee5-924c-3d59060d8a5c','project_4','',0,'xwx539899','肖曾成','',NULL,0,201803151019,201803151019),('e5c9fd41-8f85-4fb1-8bea-de02f212aa9e','project_2','',0,'xwx539899','肖曾成','',NULL,0,201803141147,201803141147);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tdt_project_r_project`;
CREATE TABLE `tdt_project_r_project` (
  `project_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `sub_project_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE_INDEX` (`project_id`,`sub_project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_role`;
CREATE TABLE `tdt_role` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `type` char(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '1:测试经理、2:TSE、3:TE、4:游客 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `tdt_role` WRITE;
INSERT INTO `tdt_role` VALUES ('1','com.huawei.tdt.role.name.tse',NULL),('12','com.huawei.tdt.role.name.te',NULL),('2','com.huawei.tdt.role.name.tm',NULL),('9','com.huawei.tdt.role.name.visitor',NULL);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tdt_role_r_privilege`;
CREATE TABLE `tdt_role_r_privilege` (
  `role_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `privilege_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE_KEY` (`role_id`,`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `tdt_role_r_privilege` WRITE;
INSERT INTO `tdt_role_r_privilege` VALUES ('1','TDT_PRIVILEGE_MEM_CONFIG'),('1','TDT_PRIVILEGE_PRJ_PLAN_MGR'),('1','TDT_PRIVILEGE_RES_CREATE'),('1','TDT_PRIVILEGE_RES_DELETE'),('1','TDT_PRIVILEGE_RES_MODIFY'),('1','TDT_PRIVILEGE_RES_VIEW'),('12','TDT_PRIVILEGE_RES_CREATE'),('12','TDT_PRIVILEGE_RES_DELETE'),('12','TDT_PRIVILEGE_RES_MODIFY'),('12','TDT_PRIVILEGE_RES_VIEW'),('2','TDT_PRIVILEGE_MEM_CONFIG'),('2','TDT_PRIVILEGE_PRJ_PLAN_MGR'),('2','TDT_PRIVILEGE_RES_CREATE'),('2','TDT_PRIVILEGE_RES_DELETE'),('2','TDT_PRIVILEGE_RES_MODIFY'),('2','TDT_PRIVILEGE_RES_VIEW'),('9','TDT_PRIVILEGE_RES_VIEW');
UNLOCK TABLES;

DROP TABLE IF EXISTS `tdt_stage`;
CREATE TABLE `tdt_stage` (
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `startTime` bigint(64) DEFAULT NULL,
  `endTime` bigint(64) DEFAULT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_systemtask`;
CREATE TABLE `tdt_systemtask` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `params` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `progress` double DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL COMMENT '0:Failed,1:Successed,2:In progress',
  `remark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remarkParams` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `createdTime` bigint(64) NOT NULL,
  `endedTime` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tdt_team`;
CREATE TABLE `tdt_team` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `type` char(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_team_r_user`;
CREATE TABLE `tdt_team_r_user` (
  `team_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `project_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `pgName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pgLeader` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pgSEDE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pgTSETDE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pgLiaison` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pgDefectAnalysisOwner` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tdt_testplan`;
CREATE TABLE `tdt_testplan` (
  `planId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `planName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `parentId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `planDesc` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `testVersionUri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mainProjectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `createdTime` bigint(20) NOT NULL,
  `updatedTime` bigint(20) NOT NULL,
  `rootPlanId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`planId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_testplan_favorite`;
CREATE TABLE `tdt_testplan_favorite` (
  `userId` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `testPlanId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `createdTime` bigint(64) NOT NULL COMMENT '收藏时间',
  UNIQUE KEY `UNIQUE_INDEX_TP_FAVORITE` (`userId`,`testPlanId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `tdt_testplan_r_testcase`;
CREATE TABLE `tdt_testplan_r_testcase` (
  `testplan_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `testcase_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `project_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_testplan_r_user`;
CREATE TABLE `tdt_testplan_r_user` (
  `testplan_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_testplan_testcase_iterator_r_task`;
CREATE TABLE `tdt_testplan_testcase_iterator_r_task` (
  `testplan_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `testcase_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `project_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `iterator_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `task_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE_INDEX` (`testplan_id`,`testcase_uri`,`iterator_uri`,`task_uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用于记录计划中的用例在分解到迭代之后在测试融合桌面中被分配到任务的记录';

DROP TABLE IF EXISTS `tdt_testplan_testcase_r_iterator`;
CREATE TABLE `tdt_testplan_testcase_r_iterator` (
  `testplan_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `testcase_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `project_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `iterator_uri` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE_INDEX` (`testplan_id`,`testcase_uri`,`iterator_uri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用于记录计划中的用例分解到迭代的关系';


DROP TABLE IF EXISTS `tdt_user`;
CREATE TABLE `tdt_user` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `departmentName1` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `departmentName2` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `departmentName3` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `tdt_user` WRITE;
INSERT INTO `tdt_user` VALUES ('lwx537094','李溜','IT研发质量与运营部','IT研发管理部','IT产品线');
UNLOCK TABLES;

DROP TABLE IF EXISTS `tdt_user_r_role`;
CREATE TABLE `tdt_user_r_role` (
  `user_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `project_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `UNIQUE_KEY` (`user_id`,`role_id`,`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tdt_worktask`;
CREATE TABLE `tdt_worktask` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `projectId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `executor` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `testPlanId` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `finishedTime` bigint(64) DEFAULT NULL,
  `createdTime` bigint(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
