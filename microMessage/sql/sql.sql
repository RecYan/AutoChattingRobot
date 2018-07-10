/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - micro_message
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`micro_message` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `micro_message`;

/*Table structure for table `command` */

DROP TABLE IF EXISTS `command`;

CREATE TABLE `command` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(16) DEFAULT NULL,
  `DESCRIPTION` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `command` */

insert  into `command`(`ID`,`NAME`,`DESCRIPTION`) values (1,'段子','精彩段子');

/*Table structure for table `command_content` */

DROP TABLE IF EXISTS `command_content`;

CREATE TABLE `command_content` (
  `ID` int(11) NOT NULL,
  `CONTENT` varchar(2048) DEFAULT NULL,
  `COMMAND_ID` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `command_content` */

insert  into `command_content`(`ID`,`CONTENT`,`COMMAND_ID`) values (1,'朋友们吃饭，一份作为同事的各种婚丧嫁娶的份子钱。剩下的2999块钱藏起来，不要告诉任何人','1'),(2,'段子2','1'),(3,'段子3','1'),(4,'段子4','1');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `COMMAND` varchar(16) DEFAULT NULL COMMENT '指令名称',
  `DESCRIPTION` varchar(32) DEFAULT NULL COMMENT '描述',
  `CONTENT` varchar(2048) DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`ID`,`COMMAND`,`DESCRIPTION`,`CONTENT`) values (1,'查看','精彩内容','精彩内容'),(2,'段子','精彩段子','如果你的月薪是3000块钱，请记得分成五份，一份用来买书，一份给家人，一份给女朋友买化妆品和衣服，一份请朋友们吃饭，一份作为同事的各种婚丧嫁娶的份子钱。剩下的2999块钱藏起来，不要告诉任何人'),(3,'新闻','今日头条','7月17日，马来西亚一架载有298人的777客机在乌克兰靠近俄罗斯边界坠毁。另据国际文传电讯社消息，坠毁机型为一架波音777客机，机载约280名乘客和15个机组人员。\r\n乌克兰空管部门随后证实马航MH17航班坠毁。乌克兰内政部幕僚表示，这一航班在顿涅茨克地区上空被击落。马来西亚航空公司确认，该公司从阿姆斯特丹飞往吉隆坡的MH17航班失联，并称最后与该客机取得联系的地点在乌克兰上空。图为马航客机坠毁现场。'),(4,'娱乐','娱乐新闻','昨日，邓超在微博分享了自己和孙俪的书法。夫妻同样写幸福，但差距很大。邓超自己都忍不住感慨字丑：左边媳妇写的。右边是我写的。看完我再也不幸福了。'),(5,'电影','近日上映大片','《忍者神龟》[2]真人电影由美国派拉蒙影业发行，《洛杉矶之战》导演乔纳森·里贝斯曼执导。 \r\n片中四只神龟和老鼠老师都基于漫画和卡通重新绘制，由动作捕捉技术实现。\r\n其中皮特·普劳泽克饰演达芬奇(武器：武士刀)，诺尔·费舍饰演米开朗基罗(武器：双节棍)，阿伦·瑞奇森饰演拉斐尔(武器：铁叉)，杰瑞米·霍华德饰演多拉泰罗(武器：武士棍)。\r\n该片计划于2014年8月8日在北美上映。'),(6,'彩票','中奖号码','查啥呀查，你不会中奖的！'),(7,'测试','测试','测试');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
