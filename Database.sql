/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.11-MariaDB : Database - onlinemobileshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`onlinemobileshop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `onlinemobileshop`;

/*Table structure for table `accessories` */

DROP TABLE IF EXISTS `accessories`;

CREATE TABLE `accessories` (
  `productID` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `typeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`productID`),
  KEY `accessories_ibfk_1` (`typeID`),
  CONSTRAINT `accessories_ibfk_1` FOREIGN KEY (`typeID`) REFERENCES `type` (`typeID`) ON UPDATE CASCADE,
  CONSTRAINT `accessories_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `accessories` */

insert  into `accessories`(`productID`,`description`,`typeID`) values 
(6,'fdgdsg',1);

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `postcode` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`postcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `city` */

insert  into `city`(`postcode`,`name`) values 
(11000,'Beograd'),
(18000,'Nis'),
(21000,'Novi Sad'),
(24000,'Subotica');

/*Table structure for table `client` */

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `clientID` int(11) NOT NULL AUTO_INCREMENT,
  `telephone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adress` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `postcode` int(11) DEFAULT NULL,
  PRIMARY KEY (`clientID`),
  KEY `client_ibfk_1` (`postcode`),
  CONSTRAINT `client_ibfk_1` FOREIGN KEY (`postcode`) REFERENCES `city` (`postcode`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `client` */

insert  into `client`(`clientID`,`telephone`,`email`,`adress`,`postcode`) values 
(1,'06444444','marko@gmail.ocom','Slanacki put 14a',11000),
(2,'06333333','stefan@gmail.com','Visnjicki venac 17',11000),
(3,'06528162','marija@gmail.com','Laze Teleckog 6',21000),
(4,'06246821','milan@yahoo.com','Prvomajska 66',18000);

/*Table structure for table `individual` */

DROP TABLE IF EXISTS `individual`;

CREATE TABLE `individual` (
  `clientID` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`clientID`),
  CONSTRAINT `individual_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `individual` */

insert  into `individual`(`clientID`,`name`,`surname`,`idNumber`) values 
(1,'Marko','Zaric',12324444),
(2,'Stefan','Djordjevic',33244324);

/*Table structure for table `legalentity` */

DROP TABLE IF EXISTS `legalentity`;

CREATE TABLE `legalentity` (
  `ClientID` int(11) NOT NULL,
  `companyRegistrationNumber` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ClientID`),
  CONSTRAINT `legalentity_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`clientID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `legalentity` */

insert  into `legalentity`(`ClientID`,`companyRegistrationNumber`,`companyName`) values 
(3,'31421dfa3124','Saga'),
(4,'dsa43242sda5','Telekom');

/*Table structure for table `manufacturer` */

DROP TABLE IF EXISTS `manufacturer`;

CREATE TABLE `manufacturer` (
  `manufacturerID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`manufacturerID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `manufacturer` */

insert  into `manufacturer`(`manufacturerID`,`name`) values 
(1,'Samsung'),
(2,'Apple'),
(3,'Xiomi'),
(4,'Sony'),
(5,'Motorola'),
(6,'Nokia'),
(7,'LG'),
(8,'Alcatel');

/*Table structure for table `mobile` */

DROP TABLE IF EXISTS `mobile`;

CREATE TABLE `mobile` (
  `productID` int(11) NOT NULL,
  `memory` int(11) DEFAULT NULL,
  `specification` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `color` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`productID`),
  CONSTRAINT `mobile_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `mobile` */

insert  into `mobile`(`productID`,`memory`,`specification`,`color`) values 
(2,256,'adsfafa','blue'),
(3,124,'fhj','red');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `clientID` int(11) DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `sellerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `order_ibfk_1` (`clientID`),
  KEY `order_ibfk_2` (`sellerID`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`) ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`sellerID`) REFERENCES `seller` (`sellerID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `order` */

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `serialNumber` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `orderitem_ibfk_1` (`productID`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON UPDATE CASCADE,
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `orderitem` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `productID` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `manufacturerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`productID`),
  KEY `product_ibfk_1` (`manufacturerID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`manufacturerID`) REFERENCES `manufacturer` (`manufacturerID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product` */

insert  into `product`(`productID`,`model`,`price`,`manufacturerID`) values 
(1,'g3',150,7),
(2,'s10e',600,1),
(3,'x',1000,2),
(4,'mi9',400,3),
(6,'galaxy bubs',140,1);

/*Table structure for table `seller` */

DROP TABLE IF EXISTS `seller`;

CREATE TABLE `seller` (
  `sellerID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sellerID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `seller` */

insert  into `seller`(`sellerID`,`name`,`surname`,`username`,`password`) values 
(1,'Rados','Samardzic','raki','raki'),
(2,'FIlip','Djordjevic','fica',NULL);

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `typeID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `type` */

insert  into `type`(`typeID`,`name`) values 
(1,'Slusalice'),
(2,'Eksterne baterije'),
(3,'Maske'),
(4,'Futrole'),
(5,'Punjaci'),
(6,'Folije'),
(7,'Zastitna stakla');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
