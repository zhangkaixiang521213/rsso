#单表实例
drop table if exists test_user;
create table test_user (
	id varchar(64),
	login_name varchar(64)  null,
	name varchar(64)  null,
	password varchar(255)  null,
	salt varchar(64)  null,
	roles varchar(255)  null,
	register_date timestamp  null default 0,
	primary key (id)
) engine=InnoDB DEFAULT CHARSET=utf8;
 
#一对多表实例
drop table if exists tbl_order;
CREATE TABLE `tbl_order` (
  `order_id` varchar(40) NOT NULL COMMENT '订单号',
  `oname` varchar(45) DEFAULT NULL COMMENT '用户',
  `oaddress` varchar(100) DEFAULT NULL COMMENT '地址',
  `phone` varchar(45) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

drop table if exists tbl_order_item;
CREATE TABLE `tbl_order_item` (
  `item_id` varchar(40) NOT NULL COMMENT '商品号',
  `order_id` varchar(45) DEFAULT NULL,
  `item_name` varchar(45) DEFAULT NULL COMMENT '商品名称',
  `price` varchar(45) DEFAULT NULL COMMENT '价格',
  `acount` varchar(45) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`item_id`),
  KEY `fk_order_id_idx` (`order_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `tbl_order` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';
