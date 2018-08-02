-- MySQL dump 10.13  Distrib 5.6.26, for osx10.6 (x86_64)
--
-- Host: localhost    Database: cemap
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `Uuid` varchar(255) DEFAULT NULL,
  `manageClass` varchar(255) DEFAULT NULL,
  `tempRoleId` int(11) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,0,'it@school.com','13223123123','admin','IT部2','admin','222',NULL,1,NULL),(2,0,NULL,NULL,'123456',NULL,'20134830909','333',NULL,4,1),(3,0,NULL,NULL,'123456',NULL,'201348309092','3333',NULL,4,2),(4,0,'22@22.com','22222','admin2','it23','admin2','38c89781-bd0c-4397-8eb1-f79adcab2f97',NULL,1,NULL),(5,0,'33@1.COM','33','admin3','33','admin3','667e2ac3-94c0-4f4c-ac24-6683c992ff25',NULL,1,NULL),(6,0,'13166031278@qq.com','13212123213','admin','徐老师','jiye1','b2c4c373-242d-410a-aa5a-4b456e780bdf',NULL,2,NULL),(12,0,'22@22.com','13213123123','12345','孙仁杰','F1','4b15aacd-7b9a-46c0-b08d-d3206b0d8d0f','1,2,3,',3,NULL),(13,0,'1232@12.com','123','123456','loveincode12i凡','20172017','555',NULL,4,NULL),(14,0,'22@22.com','2','123','123','admin','f65ab9e4-76cd-4ce6-a5b6-b7e15206a685',NULL,1,NULL),(16,0,'2@1.com','123123','123456','loveincode1','123123123','f699f0e1-b205-4d04-9227-ea5f96e53493',NULL,4,2),(17,0,'22@22.com','13232232131','123456','王涛','201348307282','6dcc3a0f-4fc3-4c40-b957-05d1b636fd32',NULL,4,1),(18,0,'22@22.com','15201871***','admin','2','admin22222','6cb3bbee-fc4f-49fa-8d56-610b1429b51d',NULL,1,NULL),(19,0,'163@163.com','15201871***','admin','三十岁','admin23','735cba6d-bf60-426f-ae05-737b3b1cf8a7',NULL,1,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_role`
--

DROP TABLE IF EXISTS `member_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_role` (
  `member_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`member_id`,`role_id`),
  KEY `FK_e8uutj5lhfcp6yjolosc8hee2` (`role_id`),
  KEY `FK_povku01iegi1ssbrexkmnk1bd` (`member_id`),
  CONSTRAINT `FK_e8uutj5lhfcp6yjolosc8hee2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_povku01iegi1ssbrexkmnk1bd` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_role`
--

LOCK TABLES `member_role` WRITE;
/*!40000 ALTER TABLE `member_role` DISABLE KEYS */;
INSERT INTO `member_role` VALUES (1,1),(4,1),(5,1),(14,1),(18,1),(19,1),(6,2),(12,3),(2,4),(3,4),(13,4),(16,4),(17,4);
/*!40000 ALTER TABLE `member_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `Uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN','管理员',NULL),(2,'ROLE_TEACHER_ADMIN','就业处老师',NULL),(3,'ROLE_TEACHER','辅导员',NULL),(4,'ROLE_STUDENT','学生',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_administrativeclass`
--

DROP TABLE IF EXISTS `t_administrativeclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_administrativeclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `collegeId` int(11) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) DEFAULT NULL,
  `professionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_administrativeclass`
--

LOCK TABLES `t_administrativeclass` WRITE;
/*!40000 ALTER TABLE `t_administrativeclass` DISABLE KEYS */;
INSERT INTO `t_administrativeclass` VALUES (1,'13fb6ea0-a7ab-4426-9cdb-344d4ee6875a','rg1',1,'\0','13软工A1',1),(2,'bd1c49e1-e3bd-434c-8d73-a7f143a9095a','13gra2',1,'\0','13软工A2',1),(3,'e67e519a-53a5-41b4-b23f-19eb95d43a6f','13hga1',4,'\0','13环工A1',4),(4,'27f919c9-1bab-4272-9a5d-dc70759fb736','13hga2',4,'\0','13环工A2',3),(5,'aa945649-489c-487d-852e-e6a31ae52563','13gra3',1,'\0','13软工A3',1),(6,'e272b4dd-668b-468d-a772-d124e4b8dbd5','123',2,'\0','13财务A1',5),(7,'eecdb4fc-37ca-4285-a96f-981af9087a36','2222222',5,'','23333',6);
/*!40000 ALTER TABLE `t_administrativeclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_college`
--

DROP TABLE IF EXISTS `t_college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_college`
--

LOCK TABLES `t_college` WRITE;
/*!40000 ALTER TABLE `t_college` DISABLE KEYS */;
INSERT INTO `t_college` VALUES (1,'da55249a-be20-437a-b478-e786d7391329','\0','计算机与信息工程学院','JSJXY1'),(2,'a5c1806d-62c5-4026-a256-236d8dba46bd','\0','经济管理学院','JGXY'),(3,'fcfa5a50-9015-4ae3-af02-b74f220dc757','\0','机械工程学院','JXGCXY'),(4,'3520c14b-9a88-4ab2-a112-ab15744459a1','\0','地理与环境科学学院','HJGCXY'),(5,'49425bfb-b149-48e3-a66e-30a9efd23da7','\0','12','232');
/*!40000 ALTER TABLE `t_college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_employmentinformation`
--

DROP TABLE IF EXISTS `t_employmentinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_employmentinformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `companyContactName` varchar(255) DEFAULT NULL,
  `companyContactPhone` varchar(255) DEFAULT NULL,
  `companyFullName` varchar(255) DEFAULT NULL,
  `companyPlace` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `email` varchar(255) DEFAULT NULL,
  `employmentMethod` varchar(255) DEFAULT NULL,
  `employmentPay` int(11) DEFAULT NULL,
  `industryType` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `occupationType` varchar(255) DEFAULT NULL,
  `organizationCode` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `studentId` varchar(255) DEFAULT NULL,
  `university` varchar(255) DEFAULT NULL,
  `companyProvince` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_employmentinformation`
--

LOCK TABLES `t_employmentinformation` WRITE;
/*!40000 ALTER TABLE `t_employmentinformation` DISABLE KEYS */;
INSERT INTO `t_employmentinformation` VALUES (2,'aad27225-4d7e-4bda-8cbf-bd8bee6c9350','12','12','12','1','\0','12','签合同',12,'水利、环境和公共设施管理业','','民营企业','12','12','12','20134830908','','四川'),(3,'151f4270-2ebd-4e20-9ae0-8d02824747b5','上海2','上海','上海2','22','\0','上海2','签合同',7500,'信息传输、软件和信息技术服务业','','民营企业','上海','2上海2','上海2','20134830909','','上海'),(4,'f46778b1-7446-4acb-8627-adb5c799089a','hr','15312312233','苏州科达','123','\0','123@163.com','签就业协议书',8000,'信息传输、软件和信息技术服务业','','民营企业','2','12312312F','231232','201348307282','','上海');
/*!40000 ALTER TABLE `t_employmentinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_graduateinformation`
--

DROP TABLE IF EXISTS `t_graduateinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_graduateinformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `accountLocation` varchar(255) DEFAULT NULL,
  `accountproperty` varchar(255) DEFAULT NULL,
  `administrativeClassId` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `collegeId` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `educationBackground` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `homeAddress` varchar(255) DEFAULT NULL,
  `homePhone` varchar(255) DEFAULT NULL,
  `identificationNumber` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `nativePlace` varchar(255) DEFAULT NULL,
  `originPlace` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `politicalStatus` varchar(255) DEFAULT NULL,
  `professionId` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `studentId` varchar(255) DEFAULT NULL,
  `graduationSession` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_graduateinformation`
--

LOCK TABLES `t_graduateinformation` WRITE;
/*!40000 ALTER TABLE `t_graduateinformation` DISABLE KEYS */;
INSERT INTO `t_graduateinformation` VALUES (3,'2f6e62d7-f3d1-42e1-aa4e-1d4d438181cd','四川成都','农村','1','1999-10-17','1','\0','本科','12345@12.com','12345','12345','510125','loveincode','汉族','四川成都新都','四川','12345','中共党员','1','男','20134830909','2017'),(4,'d9e1d63c-9b39-4b1b-b1d5-8d5ed3a6380b','','农村','2','1994-08-08','1','\0','硕士','hyfaihsx@163.com','','','','loveincode','汉族','','上海','','共青团员','1','女','201348309092','2017'),(5,'15758928-ef03-47cf-8315-64af9e94c1c4','123','农村','1','1994-08-08','1','\0','专科','1232@12.com','123','123','1231','loveincode12','汉族','四川','上海','123','共青团员','1','女','20172017','2017'),(7,'67cba27f-478e-46fd-9935-dbaa5b263ae2','成都','农村','2','1999-10-12','1','\0','本科','2@1.com','四川成都','123','12591280512','loveincode1','汉族','四川','四川','123123','共青团员','1','女','123123123','2017'),(8,'5f20d21a-9cd4-4565-9ca6-db27d7dbb921','浙江金华','农村','1','1994-08-29','1','\0','本科','22@22.com','浙江金华','15201871***','212322199408291232','王涛','汉族','浙江 金华','上海','13232232132','共青团员','1','男','201348307282','2017');
/*!40000 ALTER TABLE `t_graduateinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_graduationfile`
--

DROP TABLE IF EXISTS `t_graduationfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_graduationfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `description` varchar(255) DEFAULT NULL,
  `institutionName` varchar(255) DEFAULT NULL,
  `institutionPhone` varchar(255) DEFAULT NULL,
  `institutionPlace` varchar(255) DEFAULT NULL,
  `outDate` varchar(255) DEFAULT NULL,
  `outWay` varchar(255) DEFAULT NULL,
  `studentId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_graduationfile`
--

LOCK TABLES `t_graduationfile` WRITE;
/*!40000 ALTER TABLE `t_graduationfile` DISABLE KEYS */;
INSERT INTO `t_graduationfile` VALUES (4,'71686cab-fcf7-486f-afbf-eeee2515687f','\0','遂宁1','遂宁1','遂宁1','遂宁1','2017-03-25','遂宁1','20134830908'),(5,'b32e471e-7f59-4f8c-b459-328f1c277491','\0','4','4','4','4','2017-03-25','4','20134830909'),(6,'bef77149-2dcd-48ad-b979-32cbc29af6c8','\0','','3','13223123123','33','2017-03-31','2','201348307282'),(7,'e8a198be-6ed9-4d00-88ee-0a6ef880a9a7','\0','2','123','13612312322','123','2017-04-18','123','201348309092');
/*!40000 ALTER TABLE `t_graduationfile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_logchangeword`
--

DROP TABLE IF EXISTS `t_logchangeword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_logchangeword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `changeType` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `username_c` varchar(255) DEFAULT NULL,
  `username_x` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_logchangeword`
--

LOCK TABLES `t_logchangeword` WRITE;
/*!40000 ALTER TABLE `t_logchangeword` DISABLE KEYS */;
INSERT INTO `t_logchangeword` VALUES (1,NULL,'重置密码','2017-04-13 10:59:00','\0','201348307282','admin'),(2,NULL,'重置密码','2017-04-13 10:59:22','\0','F1','admin'),(3,NULL,'个人修改','2017-04-13 11:01:56','\0','201348307282','201348307282');
/*!40000 ALTER TABLE `t_logchangeword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_loginout`
--

DROP TABLE IF EXISTS `t_loginout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_loginout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `ipAdress` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_loginout`
--

LOCK TABLES `t_loginout` WRITE;
/*!40000 ALTER TABLE `t_loginout` DISABLE KEYS */;
INSERT INTO `t_loginout` VALUES (1,NULL,'登录','2017-03-29 11:05:11','\0','0:0:0:0:0:0:0:1','admin'),(2,NULL,'退出','2017-03-29 11:05:13','\0','0:0:0:0:0:0:0:1','admin'),(3,NULL,'登录','2017-03-29 11:05:15','\0','0:0:0:0:0:0:0:1','admin'),(4,NULL,'登录','2017-03-29 11:24:39','\0','0:0:0:0:0:0:0:1','admin'),(5,NULL,'退出','2017-03-29 11:26:19','\0','0:0:0:0:0:0:0:1','admin'),(6,NULL,'登录','2017-03-29 11:26:21','\0','0:0:0:0:0:0:0:1','admin'),(7,NULL,'登录','2017-03-29 13:04:47','\0','0:0:0:0:0:0:0:1','admin'),(8,NULL,'登录','2017-03-29 13:21:42','\0','0:0:0:0:0:0:0:1','20134830909'),(9,NULL,'退出','2017-03-29 13:28:21','\0','0:0:0:0:0:0:0:1','20134830909'),(10,NULL,'登录','2017-03-29 13:28:22','\0','0:0:0:0:0:0:0:1','admin'),(11,NULL,'登录','2017-03-29 13:56:33','\0','0:0:0:0:0:0:0:1','admin'),(12,NULL,'登录','2017-03-29 14:05:03','\0','0:0:0:0:0:0:0:1','admin'),(13,NULL,'退出','2017-03-29 14:23:05','\0','0:0:0:0:0:0:0:1','admin'),(14,NULL,'登录','2017-03-29 14:23:07','\0','0:0:0:0:0:0:0:1','admin'),(15,NULL,'登录','2017-03-29 14:29:03','\0','0:0:0:0:0:0:0:1','admin'),(16,NULL,'登录','2017-03-29 15:18:43','\0','0:0:0:0:0:0:0:1','admin'),(17,NULL,'退出','2017-03-29 15:41:26','\0','0:0:0:0:0:0:0:1','admin'),(18,NULL,'登录','2017-03-29 15:41:32','\0','0:0:0:0:0:0:0:1','123123123'),(19,NULL,'退出','2017-03-29 15:41:51','\0','0:0:0:0:0:0:0:1','123123123'),(20,NULL,'登录','2017-03-29 15:41:52','\0','0:0:0:0:0:0:0:1','admin'),(21,NULL,'退出','2017-03-29 15:42:06','\0','0:0:0:0:0:0:0:1','admin'),(22,NULL,'登录','2017-03-29 15:42:08','\0','0:0:0:0:0:0:0:1','admin'),(23,NULL,'退出','2017-03-29 15:42:11','\0','0:0:0:0:0:0:0:1','admin'),(24,NULL,'登录','2017-03-29 15:42:19','\0','0:0:0:0:0:0:0:1','123123123'),(25,NULL,'退出','2017-03-29 15:59:08','\0','0:0:0:0:0:0:0:1','123123123'),(26,NULL,'登录','2017-03-29 15:59:09','\0','0:0:0:0:0:0:0:1','admin'),(27,NULL,'登录','2017-03-30 10:58:00','\0','0:0:0:0:0:0:0:1','admin'),(28,NULL,'登录','2017-03-30 14:18:58','\0','0:0:0:0:0:0:0:1','admin'),(29,NULL,'退出','2017-03-30 14:18:59','\0','0:0:0:0:0:0:0:1','admin'),(30,NULL,'登录','2017-03-30 15:10:01','\0','0:0:0:0:0:0:0:1','admin'),(31,NULL,'登录','2017-03-30 18:28:15','\0','0:0:0:0:0:0:0:1','admin'),(32,NULL,'登录','2017-03-30 22:33:17','\0','0:0:0:0:0:0:0:1','admin'),(33,NULL,'登录','2017-03-31 09:09:50','\0','0:0:0:0:0:0:0:1','admin'),(34,NULL,'登录','2017-03-31 10:20:13','\0','0:0:0:0:0:0:0:1','admin'),(35,NULL,'退出','2017-03-31 10:20:15','\0','0:0:0:0:0:0:0:1','admin'),(36,NULL,'登录','2017-03-31 10:20:16','\0','0:0:0:0:0:0:0:1','admin'),(37,NULL,'退出','2017-03-31 10:20:17','\0','0:0:0:0:0:0:0:1','admin'),(38,NULL,'登录','2017-03-31 10:20:18','\0','0:0:0:0:0:0:0:1','admin'),(39,NULL,'退出','2017-03-31 10:56:33','\0','0:0:0:0:0:0:0:1','admin'),(40,NULL,'登录','2017-03-31 10:56:36','\0','0:0:0:0:0:0:0:1','admin'),(41,NULL,'退出','2017-03-31 10:56:47','\0','0:0:0:0:0:0:0:1','admin'),(42,NULL,'登录','2017-03-31 10:56:53','\0','0:0:0:0:0:0:0:1','201348307282'),(43,NULL,'登录','2017-03-31 13:13:05','\0','0:0:0:0:0:0:0:1','admin'),(44,NULL,'登录','2017-03-31 13:50:56','\0','0:0:0:0:0:0:0:1','admin'),(45,NULL,'登录','2017-03-31 14:17:57','\0','0:0:0:0:0:0:0:1','admin'),(46,NULL,'登录','2017-03-31 16:53:39','\0','0:0:0:0:0:0:0:1','admin'),(47,NULL,'登录','2017-04-01 11:57:42','\0','0:0:0:0:0:0:0:1','admin'),(48,NULL,'登录','2017-04-06 09:08:51','\0','0:0:0:0:0:0:0:1','admin'),(49,NULL,'登录','2017-04-06 10:59:49','\0','0:0:0:0:0:0:0:1','admin'),(50,NULL,'登录','2017-04-06 13:19:47','\0','0:0:0:0:0:0:0:1','admin'),(51,NULL,'登录','2017-04-06 13:59:00','\0','0:0:0:0:0:0:0:1','admin'),(52,NULL,'登录','2017-04-06 14:12:10','\0','0:0:0:0:0:0:0:1','admin'),(53,NULL,'登录','2017-04-06 15:06:28','\0','0:0:0:0:0:0:0:1','admin'),(54,NULL,'退出','2017-04-06 15:06:36','\0','0:0:0:0:0:0:0:1','admin'),(55,NULL,'登录','2017-04-06 15:06:37','\0','0:0:0:0:0:0:0:1','admin'),(56,NULL,'登录','2017-04-06 19:15:40','\0','0:0:0:0:0:0:0:1','admin'),(57,NULL,'登录','2017-04-07 15:17:31','\0','0:0:0:0:0:0:0:1','admin'),(58,NULL,'登录','2017-04-11 09:37:08','\0','0:0:0:0:0:0:0:1','admin'),(59,NULL,'登录','2017-04-11 09:57:57','\0','0:0:0:0:0:0:0:1','admin'),(60,NULL,'登录','2017-04-11 10:25:04','\0','0:0:0:0:0:0:0:1','admin'),(61,NULL,'登录','2017-04-11 11:06:33','\0','0:0:0:0:0:0:0:1','admin'),(62,NULL,'登录','2017-04-11 14:12:27','\0','0:0:0:0:0:0:0:1','admin'),(63,NULL,'登录','2017-04-12 11:13:36','\0','0:0:0:0:0:0:0:1','admin'),(64,NULL,'登录','2017-04-12 11:14:34','\0','0:0:0:0:0:0:0:1','admin'),(65,NULL,'登录','2017-04-12 13:52:07','\0','0:0:0:0:0:0:0:1','admin'),(66,NULL,'登录','2017-04-13 10:57:02','\0','0:0:0:0:0:0:0:1','admin'),(67,NULL,'退出','2017-04-13 11:01:24','\0','0:0:0:0:0:0:0:1','admin'),(68,NULL,'登录','2017-04-13 11:01:30','\0','0:0:0:0:0:0:0:1','201348307282'),(69,NULL,'退出','2017-04-13 11:16:54','\0','0:0:0:0:0:0:0:1','201348307282'),(70,NULL,'登录','2017-04-13 11:17:01','\0','0:0:0:0:0:0:0:1','admin'),(71,NULL,'退出','2017-04-13 11:18:22','\0','0:0:0:0:0:0:0:1','admin'),(72,NULL,'登录','2017-04-13 13:16:45','\0','0:0:0:0:0:0:0:1','admin'),(73,NULL,'退出','2017-04-13 13:16:51','\0','0:0:0:0:0:0:0:1','admin'),(74,NULL,'登录','2017-04-13 13:17:49','\0','0:0:0:0:0:0:0:1','admin'),(75,NULL,'退出','2017-04-13 13:17:50','\0','0:0:0:0:0:0:0:1','admin'),(76,NULL,'登录','2017-04-13 13:17:57','\0','0:0:0:0:0:0:0:1','admin'),(77,NULL,'退出','2017-04-13 13:17:59','\0','0:0:0:0:0:0:0:1','admin'),(78,NULL,'登录','2017-04-13 13:18:54','\0','0:0:0:0:0:0:0:1','admin'),(79,NULL,'退出','2017-04-13 13:19:10','\0','0:0:0:0:0:0:0:1','admin'),(80,NULL,'登录','2017-04-13 13:19:56','\0','0:0:0:0:0:0:0:1','admin'),(81,NULL,'登录','2017-04-13 13:21:34','\0','0:0:0:0:0:0:0:1','admin'),(82,NULL,'登录','2017-04-13 16:19:13','\0','0:0:0:0:0:0:0:1','admin'),(83,NULL,'登录','2017-04-13 17:32:41','\0','0:0:0:0:0:0:0:1','admin'),(84,NULL,'登录','2017-04-13 18:29:52','\0','0:0:0:0:0:0:0:1','admin'),(85,NULL,'登录','2017-04-13 19:50:01','\0','0:0:0:0:0:0:0:1','admin'),(86,NULL,'登录','2017-04-13 20:00:42','\0','0:0:0:0:0:0:0:1','admin'),(87,NULL,'登录','2017-04-14 09:29:53','\0','0:0:0:0:0:0:0:1','admin'),(88,NULL,'登录','2017-04-14 09:56:35','\0','0:0:0:0:0:0:0:1','admin'),(89,NULL,'登录','2017-04-14 10:53:34','\0','0:0:0:0:0:0:0:1','admin'),(90,NULL,'登录','2017-04-14 13:01:42','\0','0:0:0:0:0:0:0:1','admin'),(91,NULL,'登录','2017-04-14 13:49:24','\0','0:0:0:0:0:0:0:1','admin'),(92,NULL,'登录','2017-04-14 16:47:13','\0','0:0:0:0:0:0:0:1','admin'),(93,NULL,'登录','2017-04-15 17:00:01','\0','0:0:0:0:0:0:0:1','admin');
/*!40000 ALTER TABLE `t_loginout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_news`
--

DROP TABLE IF EXISTS `t_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `content` longtext,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `newsClick` int(11) DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `newsType_id` int(11) DEFAULT NULL,
  `attach` varchar(255) DEFAULT NULL,
  `attachName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nh6isi8wxcapp8or4bh62d1vk` (`newsType_id`),
  CONSTRAINT `FK_nh6isi8wxcapp8or4bh62d1vk` FOREIGN KEY (`newsType_id`) REFERENCES `t_newstype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_news`
--

LOCK TABLES `t_news` WRITE;
/*!40000 ALTER TABLE `t_news` DISABLE KEYS */;
INSERT INTO `t_news` VALUES (11,'029e9a41-606f-46cb-9ab8-3ed77ed82d8c','<p>123</p>','\0',22,'2017-03-19 16:22:07','高校就业分析',32,'',''),(12,'261b0ee7-7fd3-4ab5-ac3d-280fcf09335a','<p>12313</p>','\0',7,'2017-03-21 09:38:14','关于高校毕业生档案传递有关事项',33,'25dfe1a220764fce9e240572e7cb1c69','寸照.jpg'),(13,'9aed1dc7-96f6-4fae-8963-afc09f2336ec','<p>123</p>','\0',31,'2017-03-21 14:50:29','毕业生户口有关事项',32,'8891eb02b5c04562aef708880fcb8b84','工作照.jpeg'),(14,'c0fb2eb7-8043-490a-8d36-30787d10dc5c','<p>有些东西明明一文不值，却不舍得扔掉 有些故事其实很平常，我们却念念不忘 它们放在角落或存入记忆的盒子 随时能让我们重新看到一场大雨 一次分离，一杯咖啡，一个拥抱 那些细碎而美好的存在&nbsp;</p>','\0',3,'2017-03-24 10:56:15','毕业生签订就业协议流程',32,'',''),(15,'d471b98e-359c-4999-a23b-ea96eacdad21','<p>123123123</p>','\0',22,'2017-03-28 13:52:22','毕业生就业协议违约改派流程',34,'794d3f769628406fa7e70bff534ef937','4.pic.dftemp.jpg'),(16,'ed5e773e-8fc6-417f-accb-08467edd8049','<p><br></p>','\0',0,'2017-03-30 15:31:27','毕业生生源信息核对',32,'',''),(17,'667c64de-f085-484c-9c9e-c9d98192dfb4','<p>123</p>','\0',5,'2017-03-30 16:13:45',' 毕业生就业信息填写',32,'',''),(18,'b84ec740-9c23-4705-82e7-be72c3ca1d24','<p>2</p>','\0',0,'2017-04-01 11:57:51','123',32,'',''),(19,'ab0380a0-ffa1-4564-b47e-828f8c1463d0','<p>23</p>','\0',0,'2017-04-01 11:57:55','123',32,'',''),(20,'bbf114e6-a930-4e54-a40c-f4a4536d8d88','<p>123</p>','\0',0,'2017-04-01 11:58:00','123',32,'',''),(21,'0685ada0-558d-42f9-9978-1d49a9944333','<p>123</p>','\0',1,'2017-04-01 11:58:05','123',32,'',''),(22,'f5452962-770d-41f2-a600-2962b2b46182','<p>123</p>','\0',0,'2017-04-01 11:58:09','123',32,'',''),(23,'1f4c73e6-94fd-49a5-935e-ac572405e3e7','<p>2</p>','\0',1,'2017-04-01 11:58:24','123',32,'',''),(24,'21a149aa-8c8f-48df-bed2-e7975da1ed5d','<p>2</p>','\0',15,'2017-04-01 11:58:28','123',32,'','');
/*!40000 ALTER TABLE `t_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_newstype`
--

DROP TABLE IF EXISTS `t_newstype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_newstype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) DEFAULT NULL,
  `Uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_newstype`
--

LOCK TABLES `t_newstype` WRITE;
/*!40000 ALTER TABLE `t_newstype` DISABLE KEYS */;
INSERT INTO `t_newstype` VALUES (32,'Notice','\0','公告','00a2234e-e28d-4ca8-966a-f12ac33d7fb6'),(33,'Information','\0','资讯','5595421b-c99f-4ac4-aead-8031e28e7434'),(34,'File','\0','文件','244a0f3c-c356-4550-8b74-052319e2d612'),(35,'213','\0','123','b2cee2c9-4bea-4b21-853d-26ad886e0eb6'),(36,'11','\0','113','e2d1bf8a-0a22-4ac8-8513-746a877aede1');
/*!40000 ALTER TABLE `t_newstype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_preachingmeeting`
--

DROP TABLE IF EXISTS `t_preachingmeeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_preachingmeeting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `companyType` varchar(255) DEFAULT NULL,
  `dayDate` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `description` longtext,
  `period` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_preachingmeeting`
--

LOCK TABLES `t_preachingmeeting` WRITE;
/*!40000 ALTER TABLE `t_preachingmeeting` DISABLE KEYS */;
INSERT INTO `t_preachingmeeting` VALUES (1,'f823aa2c-e113-4869-8cbd-4e7defd6390a','阿里巴巴','民营企业','2017-08-08','\0','<p>瞬开</p>','16:30 - 17:30','教学楼6300'),(2,'e539a58b-4a4b-4581-939b-bbba96134bea','222','国有企业','2017-08-07','\0','<p>qwe</p>','13:30 - 17:20','212'),(3,'8243a7e0-55b6-4ba2-805d-bff18ed67397','2121','国有企业','2017-08-08','\0','<p>122</p>','12:22 - 22:22','211'),(4,'fdd94ed5-1274-4e33-83df-f3d0fd89c068','百度','国有企业','2017-08-08','\0','<p>123</p>','17:33 - 17:22','人文楼3200'),(5,'3ecdcc7c-2e69-461a-a7c8-8911cc0b7726','同瑞','民营企业','2017-08-08','\0','<p>系</p>','12:00 - 13:00','教学1110');
/*!40000 ALTER TABLE `t_preachingmeeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_profession`
--

DROP TABLE IF EXISTS `t_profession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_profession` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `collegeId` int(11) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_profession`
--

LOCK TABLES `t_profession` WRITE;
/*!40000 ALTER TABLE `t_profession` DISABLE KEYS */;
INSERT INTO `t_profession` VALUES (1,'a349c6aa-8814-4bc6-b9ce-4e4c82a57d83','rg',1,'\0','软件工程'),(2,'e95d333a-5e5c-4edd-a913-8e1b36ae96f9','jk',1,'\0','计算机科学与技术'),(3,'872b0c99-f70e-4324-8f51-819234182952','HG',4,'\0','环境工程'),(4,'272f3c8c-8f37-4506-a6c3-290209f63034','HK',4,'\0','环境科学'),(5,'8f04cd37-b134-427a-b6f7-c8911fc3f30b','jg',2,'\0','财务管理'),(6,'0771c5ce-9d69-4881-8265-a874040cc54b','222333',5,'\0','2222');
/*!40000 ALTER TABLE `t_profession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_recruitmentinformation`
--

DROP TABLE IF EXISTS `t_recruitmentinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_recruitmentinformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(255) DEFAULT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `companyType` varchar(255) DEFAULT NULL,
  `deadline` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `theme` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_recruitmentinformation`
--

LOCK TABLES `t_recruitmentinformation` WRITE;
/*!40000 ALTER TABLE `t_recruitmentinformation` DISABLE KEYS */;
INSERT INTO `t_recruitmentinformation` VALUES (1,'4722cdbc-86d9-4324-997d-f8b67f8bcf41','阿里巴巴','民营企业','2017-03-23','\0','<p>123</p>','2017校园招聘','2017校园招聘'),(2,'9a4269b8-f559-456e-826f-1763bef430b6','苏州科达','民营企业','2017-12-20','\0','<h2><b>苏州科达2017校园招聘苏州科达2017校园招聘<br></b><b>苏州科达2017校园招聘苏州科达2017校园招聘</b></h2><p><b><br></b><br></p>','22@22.com','苏州科达2017校园招聘'),(3,'189492a5-1198-4d8f-9175-8f11a802624a','百度','民营企业','2017-03-21','\0','<p>百度2017春季校园招聘</p><p>百度2017春季校园招聘</p><p>百度2017春季校园招聘</p><p>百度2017春季校园招聘</p><p>百度2017春季校园招聘<br></p>','百度2017春季校园招聘','百度2017春季校园招聘'),(4,'0e69e6fc-3781-4253-815e-d7ffa2b909e7','腾讯','民营企业','2017-05-09','\0','<p>腾讯2017春季校园招聘</p><p>腾讯2017春季校园招聘</p><p>腾讯2017春季校园招聘</p><p>腾讯2017春季校园招聘</p><p>腾讯2017春季校园招聘</p><hr><p><br></p>','hr@qq.com','腾讯2017春季校园招聘'),(5,'ebad78e2-5d81-4f91-856e-fbfc28633593','测试','国有企业','2017-03-30','\0','<p>22</p>','22@22.com','测试'),(6,'c31542c6-211e-4adf-b45c-53adbfb7a6fa','测','国有企业','2017-08-08','\0','<p>12</p>','hr@1.com','测');
/*!40000 ALTER TABLE `t_recruitmentinformation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-18 10:54:04
