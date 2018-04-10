SET SESSION FOREIGN_KEY_CHECKS=0;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/* Drop Tables */

DROP TABLE IF EXISTS sys_role_office;
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_office;
DROP TABLE IF EXISTS sys_area;
DROP TABLE IF EXISTS sys_dict;
DROP TABLE IF EXISTS sys_log;
DROP TABLE IF EXISTS sys_mdict;
DROP TABLE IF EXISTS sys_role_menu;
DROP TABLE IF EXISTS sys_menu;
DROP TABLE IF EXISTS sys_role;




/* Create Tables */

CREATE TABLE sys_area
(
	id varchar(64) NOT NULL COMMENT '编号',
	parent_id varchar(64) NOT NULL COMMENT '父级编号',
	parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
	name varchar(100) NOT NULL COMMENT '名称',
	sort decimal(10,0) NOT NULL COMMENT '排序',
	code varchar(100) COMMENT '区域编码',
	type char(1) COMMENT '区域类型',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '区域表';


CREATE TABLE sys_dict
(
	id varchar(64) NOT NULL COMMENT '编号',
	value varchar(100) NOT NULL COMMENT '数据值',
	label varchar(100) NOT NULL COMMENT '标签名',
	type varchar(100) NOT NULL COMMENT '类型',
	description varchar(100) NOT NULL COMMENT '描述',
	sort decimal(10,0) NOT NULL COMMENT '排序（升序）',
	parent_id varchar(64) DEFAULT '0' COMMENT '父级编号',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '字典表';


CREATE TABLE sys_log
(
	id varchar(64) NOT NULL COMMENT '编号',
	type char(1) DEFAULT '1' COMMENT '日志类型',
	title varchar(255) DEFAULT '' COMMENT '日志标题',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	remote_addr varchar(255) COMMENT '操作IP地址',
	user_agent varchar(255) COMMENT '用户代理',
	request_uri varchar(255) COMMENT '请求URI',
	method varchar(5) COMMENT '操作方式',
	params text COMMENT '操作提交的数据',
	exception text COMMENT '异常信息',
	PRIMARY KEY (id)
) COMMENT = '日志表';


CREATE TABLE sys_mdict
(
	id varchar(64) NOT NULL COMMENT '编号',
	parent_id varchar(64) NOT NULL COMMENT '父级编号',
	parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
	name varchar(100) NOT NULL COMMENT '名称',
	sort decimal(10,0) NOT NULL COMMENT '排序',
	description varchar(100) COMMENT '描述',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '多级字典表';


CREATE TABLE sys_menu
(
	id varchar(64) NOT NULL COMMENT '编号',
	parent_id varchar(64) NOT NULL COMMENT '父级编号',
	parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
	name varchar(100) NOT NULL COMMENT '名称',
	sort decimal(10,0) NOT NULL COMMENT '排序',
	href varchar(2000) COMMENT '链接',
	target varchar(20) COMMENT '目标',
	icon varchar(100) COMMENT '图标',
	is_show char(1) NOT NULL COMMENT '是否在菜单中显示',
	permission varchar(200) COMMENT '权限标识',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '菜单表';


CREATE TABLE sys_office
(
	id varchar(64) NOT NULL COMMENT '编号',
	parent_id varchar(64) NOT NULL COMMENT '父级编号',
	parent_ids varchar(2000) NOT NULL COMMENT '所有父级编号',
	name varchar(100) NOT NULL COMMENT '名称',
	sort decimal(10,0) NOT NULL COMMENT '排序',
	area_id varchar(64) NOT NULL COMMENT '归属区域',
	code varchar(100) COMMENT '区域编码',
	type char(1) NOT NULL COMMENT '机构类型',
	grade char(1) NOT NULL COMMENT '机构等级',
	address varchar(255) COMMENT '联系地址',
	zip_code varchar(100) COMMENT '邮政编码',
	master varchar(100) COMMENT '负责人',
	phone varchar(200) COMMENT '电话',
	fax varchar(200) COMMENT '传真',
	email varchar(200) COMMENT '邮箱',
	USEABLE varchar(64) COMMENT '是否启用',
	PRIMARY_PERSON varchar(64) COMMENT '主负责人',
	DEPUTY_PERSON varchar(64) COMMENT '副负责人',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '机构表';


CREATE TABLE sys_role
(
	id varchar(64) NOT NULL COMMENT '编号',
	office_id varchar(64) COMMENT '归属机构',
	name varchar(100) NOT NULL COMMENT '角色名称',
	enname varchar(255) COMMENT '英文名称',
	role_type varchar(255) COMMENT '角色类型',
	data_scope char(1) COMMENT '数据范围',
	is_sys varchar(64) COMMENT '是否系统数据',
	useable varchar(64) COMMENT '是否可用',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '角色表';


CREATE TABLE sys_role_menu
(
	role_id varchar(64) NOT NULL COMMENT '角色编号',
	menu_id varchar(64) NOT NULL COMMENT '菜单编号',
	PRIMARY KEY (role_id, menu_id)
) COMMENT = '角色-菜单';


CREATE TABLE sys_role_office
(
	role_id varchar(64) NOT NULL COMMENT '角色编号',
	office_id varchar(64) NOT NULL COMMENT '机构编号',
	PRIMARY KEY (role_id, office_id)
) COMMENT = '角色-机构';


CREATE TABLE sys_user
(
	id varchar(64) NOT NULL COMMENT '编号',
	company_id varchar(64) NOT NULL COMMENT '归属公司',
	office_id varchar(64) NOT NULL COMMENT '归属部门',
	login_name varchar(100) NOT NULL COMMENT '登录名',
	password varchar(100) NOT NULL COMMENT '密码',
	no varchar(100) COMMENT '工号',
	name varchar(100) NOT NULL COMMENT '姓名',
	email varchar(200) COMMENT '邮箱',
	phone varchar(200) COMMENT '电话',
	mobile varchar(200) COMMENT '手机',
	user_type char(1) COMMENT '用户类型',
	photo varchar(1000) COMMENT '用户头像',
	login_ip varchar(100) COMMENT '最后登陆IP',
	login_date datetime COMMENT '最后登陆时间',
	login_flag varchar(64) COMMENT '是否可登录',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '用户表';


CREATE TABLE sys_user_role
(
	user_id varchar(64) NOT NULL COMMENT '用户编号',
	role_id varchar(64) NOT NULL COMMENT '角色编号',
	PRIMARY KEY (user_id, role_id)
) COMMENT = '用户-角色';

CREATE TABLE `e_user` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '之前用的学生表的id，由于老师也可以点赞，改为e_user表的id',
  `user_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_password` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_group` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '1.学生组 2.老师组',
  `user_phone` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `nick_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `point` int(11) DEFAULT NULL COMMENT '积分',
  `openid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'openid',
  `photo` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `e_user_unique_index_user_name` (`user_name`),
  UNIQUE KEY `e_user_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Table structure for table `t_event` */

DROP TABLE IF EXISTS `t_event`;

CREATE TABLE `t_event` (
  `id` varchar(32) NOT NULL,
  `event_code` varchar(32) DEFAULT NULL COMMENT '事件编码',
  `event_name` varchar(100) DEFAULT NULL COMMENT '事件名称',
  `event_scope` varchar(2) DEFAULT NULL COMMENT '适用范围',
  `event_score` int(11) DEFAULT NULL COMMENT '得分',
  `day_limit` int(11) DEFAULT NULL COMMENT '每日上限',
  `enable` varchar(2) DEFAULT NULL COMMENT '是否有效',
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_event_log` */

DROP TABLE IF EXISTS `t_event_log`;

CREATE TABLE `t_event_log` (
  `id` varchar(32) NOT NULL,
  `event_id` varchar(32) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_feedback` */

DROP TABLE IF EXISTS `t_feedback`;

CREATE TABLE `t_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID，为空表示未登录用户',
  `user_name` varchar(60) DEFAULT NULL COMMENT '姓名',
  `email` varchar(80) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(13) DEFAULT NULL COMMENT '电话',
  `content` varchar(500) NOT NULL COMMENT '反馈内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '反馈时间',
  `comefrom` smallint(6) DEFAULT NULL COMMENT '来源(参见biz_metadata, meta_type=platform)',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '处理状态 0-未处理 1-已处理',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `handle_user_id` varchar(32) DEFAULT NULL,
  `qq` varchar(10) DEFAULT NULL,
  `content_type` varchar(50) DEFAULT NULL,
  `handle_result` varchar(1000) DEFAULT NULL,
  `hanle_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Table structure for table `t_level` */

DROP TABLE IF EXISTS `t_level`;

CREATE TABLE `t_level` (
  `id` varchar(32) NOT NULL,
  `level_name` varchar(32) DEFAULT NULL COMMENT '名称',
  `level_scope` varchar(2) DEFAULT NULL COMMENT '适用范围',
  `from_point` int(11) DEFAULT NULL COMMENT '分值下限',
  `to_point` int(11) DEFAULT NULL COMMENT '分值上限',
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `upate_date` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_level_level_name_level_scope` (`level_name`,`level_scope`),
  UNIQUE KEY `t_level_level_name_level_scope_from_point` (`level_name`,`level_scope`,`from_point`),
  UNIQUE KEY `t_level_level_name_level_scope_to_point` (`level_name`,`level_scope`,`to_point`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_sign` */

DROP TABLE IF EXISTS `t_sign`;

CREATE TABLE `t_sign` (
  `object_id` varchar(32) DEFAULT NULL COMMENT '可以是用户ID',
  `sign_id` varchar(32) DEFAULT NULL COMMENT '可以是消息ID',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `t_sign_unique` (`object_id`,`sign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_vote_item` */

DROP TABLE IF EXISTS `t_vote_item`;

CREATE TABLE `t_vote_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` varchar(30) DEFAULT NULL COMMENT '点赞的对象id',
  `vote_count` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数量统计',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Table structure for table `t_vote_record` */

DROP TABLE IF EXISTS `t_vote_record`;

CREATE TABLE `t_vote_record` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `topic_id` varchar(32) DEFAULT NULL COMMENT '投票的对象id',
  `vote_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '投票时间,不用传',
  `create_by` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`topic_id`),
  KEY `fk_vote_record_user` (`user_id`),
  KEY `fk_vote_record_topic` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


/* Create Indexes */

CREATE INDEX sys_area_parent_id ON sys_area (parent_id ASC);
/*CREATE INDEX sys_area_parent_ids ON sys_area (parent_ids ASC);*/
CREATE INDEX sys_area_del_flag ON sys_area (del_flag ASC);
CREATE INDEX sys_dict_value ON sys_dict (value ASC);
CREATE INDEX sys_dict_label ON sys_dict (label ASC);
CREATE INDEX sys_dict_del_flag ON sys_dict (del_flag ASC);
CREATE INDEX sys_log_create_by ON sys_log (create_by ASC);
CREATE INDEX sys_log_request_uri ON sys_log (request_uri ASC);
CREATE INDEX sys_log_type ON sys_log (type ASC);
CREATE INDEX sys_log_create_date ON sys_log (create_date ASC);
CREATE INDEX sys_mdict_parent_id ON sys_mdict (parent_id ASC);
/*CREATE INDEX sys_mdict_parent_ids ON sys_mdict (parent_ids ASC);*/
CREATE INDEX sys_mdict_del_flag ON sys_mdict (del_flag ASC);
CREATE INDEX sys_menu_parent_id ON sys_menu (parent_id ASC);
/*CREATE INDEX sys_menu_parent_ids ON sys_menu (parent_ids ASC);*/
CREATE INDEX sys_menu_del_flag ON sys_menu (del_flag ASC);
CREATE INDEX sys_office_parent_id ON sys_office (parent_id ASC);
/*CREATE INDEX sys_office_parent_ids ON sys_office (parent_ids ASC);*/
CREATE INDEX sys_office_del_flag ON sys_office (del_flag ASC);
CREATE INDEX sys_office_type ON sys_office (type ASC);
CREATE INDEX sys_role_del_flag ON sys_role (del_flag ASC);
CREATE INDEX sys_role_enname ON sys_role (enname ASC);
CREATE INDEX sys_user_office_id ON sys_user (office_id ASC);
CREATE INDEX sys_user_login_name ON sys_user (login_name ASC);
CREATE INDEX sys_user_company_id ON sys_user (company_id ASC);
CREATE INDEX sys_user_update_date ON sys_user (update_date ASC);
CREATE INDEX sys_user_del_flag ON sys_user (del_flag ASC);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;