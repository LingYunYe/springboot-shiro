/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.19 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `user` (
	`id` int (20),
	`name` varchar (60),
	`pwd` varchar (60),
	`perms` varchar (150),
	`roles` varchar (150)
); 
insert into `user` (`id`, `name`, `pwd`, `perms`, `roles`) values('1','guest','123456','user:delete','vip3');
insert into `user` (`id`, `name`, `pwd`, `perms`, `roles`) values('3','test','123456','user:update','vip2');
insert into `user` (`id`, `name`, `pwd`, `perms`, `roles`) values('4','admin','123456','user:delete,user:update','vip');
