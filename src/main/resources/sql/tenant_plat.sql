/*==============================================================*/
/* Table: 租户管理                                       */
/*==============================================================*/
drop table if exists dhlk_basic_tenant;
create table  dhlk_basic_tenant (
  id integer NOT NULL AUTO_INCREMENT,
  code varchar(100) DEFAULT NULL COMMENT '租户编码',
  name varchar(50) DEFAULT NULL COMMENT '租户名称',
  telephone  varchar(20)  DEFAULT NULL COMMENT '企业联系电话',
  adress  varchar(100)    DEFAULT NULL COMMENT '联系地址',
  company_email  varchar(50)  DEFAULT NULL COMMENT '企业邮箱',
  linkman   varchar(20)       DEFAULT NULL COMMENT '联系人',
  phone    varchar(15)        DEFAULT NULL COMMENT '联系电话',
  email    varchar(50)        DEFAULT NULL COMMENT '邮箱',
  start_time  timestamp       DEFAULT NOW() COMMENT '有效期起',
  end_time   timestamp        DEFAULT NOW() COMMENT '有效期止',
  create_time   timestamp     DEFAULT NOW() COMMENT '创建时间',
  devices        integer      DEFAULT NULL COMMENT '最大设备数',
  tb_loginname    varchar(50)        DEFAULT NULL COMMENT 'tb登录账号',
  tb_password     varchar(50)        DEFAULT NULL COMMENT 'tb登录密码',
  tb_id     varchar(100)        DEFAULT NULL COMMENT '租户id',
  is_system   integer DEFAULT '1' COMMENT '是否系统默认租户  0是 1否',
  file_id    varchar(100)        DEFAULT NULL COMMENT '附件id',
  status integer DEFAULT '0' COMMENT '状态 0正常 2 删除',
  public_key    varchar(220)        DEFAULT NULL COMMENT '公钥',
  sign    varchar(200)        DEFAULT NULL COMMENT '数字签名',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户管理';


drop table if exists dhlk_basic_tenant_menu;
create table dhlk_basic_tenant_menu
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   menu_id              integer                        DEFAULT NULL COMMENT '菜单id',
   tenant_id              integer                      DEFAULT NULL COMMENT '租户id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户权限维护';
/*==============================================================*/
/* Table: 工厂机构                                       */
/*==============================================================*/
drop table if exists dhlk_basic_org;
create table  dhlk_basic_org (
  id integer NOT NULL AUTO_INCREMENT,
  code varchar(10) DEFAULT NULL COMMENT '机构编码',
  name varchar(20) DEFAULT NULL COMMENT '机构名称',
  parent_id integer DEFAULT '0' COMMENT '父机构 0最顶级机构',
  status integer DEFAULT '0' COMMENT '状态 0正常 2 删除',
  tenant_id integer DEFAULT NULL  COMMENT '租户',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工厂管理';
-- ----------------------------
-- Records of dhlk_basic_org
-- ----------------------------
INSERT INTO `dhlk_basic_org` VALUES (1, 'dhlk', '大航联科', 0, 2,null);
/*==============================================================*/
/* Table: 访问秘钥                                   */
/*==============================================================*/
drop table if exists dhlk_basic_org_auth;
create table dhlk_basic_org_auth
(
    id                   integer                        NOT NULL AUTO_INCREMENT,
    auth                 varchar(100)                   DEFAULT NULL COMMENT '秘钥',
    tenant_id            integer                        DEFAULT NULL COMMENT '租户',
    primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问秘钥';
/*==============================================================*/
/* Table: 网络设备                            */
/*==============================================================*/
drop table if exists dhlk_basic_net_devices;
create table dhlk_basic_net_devices
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   name                 varchar(50)                    DEFAULT NULL COMMENT '名称',
   ip                   varchar(50)                    DEFAULT NULL COMMENT 'ip地址',
   gateway              varchar(50)                    DEFAULT NULL COMMENT '默认网关',
   mask                 varchar(50)                    DEFAULT NULL COMMENT '子网掩码',
   create_time          timestamp                      DEFAULT NOW() COMMENT '创建时间',
   status               integer                        DEFAULT '0' COMMENT '状态 0正常 1停用',
   license              varchar(100)                   DEFAULT NULL COMMENT '许可',
   type_id              integer                        DEFAULT '1' COMMENT '设备类型  1大数据一体机 2 BI控制器',
   note                 varchar(200)                   DEFAULT NULL COMMENT '设备描述',
   tenant_id            integer                        DEFAULT NULL COMMENT '租户',
   tb_id                varchar(100)                   DEFAULT NULL COMMENT 'tb id',
   credentials          varchar(100)                   DEFAULT NULL COMMENT '网络设备访问令牌token',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网络设备';


/*==============================================================*/
/* Table: 网络设备故障                                          */
/*==============================================================*/
drop table if exists dhlk_basic_net_fault;
create table dhlk_basic_net_fault
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   tb_id                varchar(100)                   DEFAULT NULL COMMENT 'td网络设备Id',
   type                 varchar(100)                   DEFAULT NULL COMMENT '故障类型',
   content              text                           DEFAULT NULL COMMENT '故障信息',
   status               integer                        DEFAULT '0' COMMENT '状态 0待处理 1已处理',
   create_time          timestamp                      DEFAULT NOW() COMMENT '创建时间',
   deal_user            integer                        DEFAULT NULL COMMENT '处理人',
   deal_time            timestamp                      DEFAULT NOW() COMMENT '处理时间',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网络设备故障';

/*==============================================================*/
/* Table: 生产设备                          */
/*==============================================================*/
drop table if exists dhlk_basic_product_devices;
create table dhlk_basic_product_devices
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   code                 varchar(100)                    DEFAULT NULL COMMENT '设备编码',
   name                 varchar(100)                    DEFAULT NULL COMMENT '设备名称',
   org_id               integer                        DEFAULT NULL COMMENT '所属车间',
   note                 varchar(200)                   DEFAULT NULL COMMENT '描述',
   tenant_id            integer                        DEFAULT NULL COMMENT '租户',
   classify_id          varchar(100)                   DEFAULT NULL COMMENT '设备分类',
   create_time          timestamp                      DEFAULT NOW() COMMENT '添加时间',
   status               integer                        DEFAULT '0' COMMENT '状态  0正常 1禁用 2 删除',
   tb_id                varchar(100)                   DEFAULT NULL COMMENT 'tdId',
   credentials          varchar(100)                   DEFAULT NULL COMMENT '生产设备访问令牌token',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生产设备';

/*==============================================================*/
/* Table: 生产设备和网络设备关系                             */
/*==============================================================*/
drop table if exists dhlk_basic_product_net;
create table dhlk_basic_product_net
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   net_id               integer                        DEFAULT NULL COMMENT '设备分类',
   product_id           integer                        DEFAULT NULL COMMENT '设备分类',
   type                 integer                        DEFAULT NULL COMMENT '设备类型',
   tenant_id            integer                        DEFAULT NULL COMMENT '租户',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生产设备和网络设备关系配置';

/*==============================================================*/
/* Table: 软件网络设备关系表                        */
/*==============================================================*/
drop table if exists dhlk_basic_net_devices_soft;
create table dhlk_basic_net_devices_soft
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   name                 varchar(50)                    DEFAULT NULL COMMENT '软件名称',
   version              varchar(50)                    DEFAULT NULL COMMENT '当前版本号',
   url                  varchar(200)                   DEFAULT NULL COMMENT '云端地址url',
   port                 varchar(10)                    DEFAULT NULL COMMENT '端口号',
   net_device_id        integer                        DEFAULT NULL COMMENT '网络设备',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='软件网络设备关系表';
/*==============================================================*/
/* Table: 用户                                     */
/*==============================================================*/
drop table if exists dhlk_basic_user;
create table dhlk_basic_user
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   name                 varchar(50)                    DEFAULT NULL COMMENT '姓名',
   login_name           varchar(50)                    DEFAULT NULL COMMENT '用户名',
   password             varchar(100)                   DEFAULT NULL COMMENT '密码',
   phone                varchar(50)                    DEFAULT NULL COMMENT '联系电话',
   email                varchar(50)                    DEFAULT NULL COMMENT '邮箱',
   status               integer                        DEFAULT '0' COMMENT '状态  0正常 1禁用',
   org_id               varchar(100)                   DEFAULT NULL COMMENT '所属机构集合 用逗号分割',
   create_time          timestamp                      DEFAULT NOW() COMMENT '创建时间',
   is_admin             integer                        DEFAULT '1' COMMENT '是否超级用户 0超级管理员，2租户管理员，1普通用户',
   tenant_id            integer                        DEFAULT NULL COMMENT '租户',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of dhlk_basic_user
-- ----------------------------
INSERT INTO `dhlk_basic_user` VALUES (1, 'admin', 'admin', '1f6f4f71c8802b45d5c8ffac64374c02', '18293080304', '123@qq.com', 0, '1', '2020-04-09 18:05:10', 0,null);
/*==============================================================*/
/* Table: mosquito                                */
/*==============================================================*/
drop table if exists dhlk_basic_data_broker;
create table dhlk_basic_data_broker
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   name        			varchar(50)                    DEFAULT NULL COMMENT '名称',
   ip                   varchar(50)                    DEFAULT NULL COMMENT 'ip地址',
   topic_list           varchar(1000)                  DEFAULT NULL COMMENT '主题',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据订阅';

/*==============================================================*/
/* Table: 用户登录日志                                 */
/*==============================================================*/
drop table if exists dhlk_basic_login_log;
create table dhlk_basic_login_log
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   user_id              integer                        DEFAULT NULL COMMENT '登录用户',
   ip                   varchar(50)                    DEFAULT NULL COMMENT '登录地址',
   login_time           timestamp                      DEFAULT NOW() COMMENT '登录时间',
   tenant_id            integer                        DEFAULT NULL COMMENT '租户',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录日志';

/*==============================================================*/
/* Table: 用户角色关系                                 */
/*==============================================================*/
drop table if exists dhlk_basic_user_role;
create table dhlk_basic_user_role
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   user_id              integer                        DEFAULT NULL COMMENT '用户id',
   role_id              integer                        DEFAULT NULL COMMENT '角色id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系';
-- ----------------------------
-- Records of dhlk_basic_user_role
-- ----------------------------
INSERT INTO `dhlk_basic_user_role` VALUES (1, 1, 1);
/*==============================================================*/
/* Table: 权限                               */
/*==============================================================*/
drop table if exists dhlk_basic_permissions;
create table dhlk_basic_permissions
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   menu_id              integer                        DEFAULT NULL COMMENT '菜单id',
   role_id              integer                        DEFAULT NULL COMMENT '角色id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (1, 1, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (2, 2, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (3, 3, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (4, 4, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (5, 5, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (6, 6, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (7, 7, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (8, 8, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (9, 9, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (10, 10, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (11, 11, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (12, 12, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (13, 13, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (14, 14, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (15, 15, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (16, 16, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (17, 17, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (18, 19, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (19, 20, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (20, 23, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (21, 25, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (22, 32, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (23, 38, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (24, 39, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (25, 40, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (26, 41, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (27, 42, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (28, 43, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (29, 44, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (30, 45, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (31, 46, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (32, 47, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (33, 48, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (34, 49, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (35, 50, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (36, 51, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (37, 52, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (38, 53, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (39, 54, 1);

INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (41, 56, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (42, 57, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (43, 66, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (44, 67, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (45, 69, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (46, 70, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (47, 72, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (48, 73, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (49, 74, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (50, 75, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (51, 76, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (52, 77, 1);



/*==============================================================*/
/* Table: API分类表                                   */
/*==============================================================*/
drop table if exists dhlk_api_classify;
create table dhlk_api_classify
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   class_name           varchar(50)                    DEFAULT NULL UNIQUE COMMENT '分类名称',
   parent_id            integer                        DEFAULT NULL COMMENT '父节点',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='API分类表';

/*==============================================================*/
/* Table: 元数据表                                */
/*==============================================================*/
drop table if exists dhlk_basic_meta_table;
create table dhlk_basic_meta_table
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   table_enname         varchar(50)                    DEFAULT NULL COMMENT '元数据表编码',
   table_cnname         varchar(50)                    DEFAULT NULL COMMENT '元数据表名称',
   create_time          timestamp                      DEFAULT NOW() COMMENT '创建时间',
   status               integer                        DEFAULT '0' COMMENT '状态 0正常 2删除',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据表';

/*==============================================================*/
/* Table: 角色                                      */
/*==============================================================*/
drop table if exists dhlk_basic_role;
create table dhlk_basic_role
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   name                 varchar(20)                    DEFAULT NULL COMMENT '名称',
   note                 varchar(200)                   DEFAULT NULL COMMENT '备注',
   is_system            integer                        DEFAULT '1' COMMENT '是否系统默认角色  0是 1否',
   tenant_id            integer                        DEFAULT NULL COMMENT '租户',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
 /*默认超级管理员的角色id为1*/
INSERT INTO `dhlk_basic_role` VALUES ('1', '超级管理员', '超级管理员',0,1);
INSERT INTO `dhlk_basic_role` VALUES ('2', '租户管理员', '租户管理员',0,1);
/*==============================================================*/
/* Table: 系统菜单                                      */
/*==============================================================*/
drop table if exists dhlk_basic_menu;
create table dhlk_basic_menu
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   code                 varchar(50)                    DEFAULT NULL COMMENT '编码',
   name                 varchar(50)                    DEFAULT NULL COMMENT '名称',
   url                  varchar(100)                   DEFAULT NULL COMMENT '请求连接',
   status               integer                        DEFAULT '0'  COMMENT '状态  0正常 1禁用 2删除',
	 parent_id             int(11)                        DEFAULT NULL COMMENT '父节点',
	 perms                varchar(500)                   DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
	 type                 integer                        DEFAULT '0'  COMMENT '类型   0菜单   1按钮',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';
-- ----------------------------
-- Records of dhlk_basic_menu
-- ----------------------------
INSERT INTO `dhlk_basic_menu` VALUES (1, 'userCenter', '用户中心', '/system', 0, 0, 'user', 0);
INSERT INTO `dhlk_basic_menu` VALUES (2, 'deviceCenter', '设备中心', '/monitor', 0, 0, 'device', 0);
INSERT INTO `dhlk_basic_menu` VALUES (3, 'user/view', '用户管理', '/system/user', 0, 1, 'dhlk:view', 1);
INSERT INTO `dhlk_basic_menu` VALUES (4, 'role/view', '角色管理', '/system/role', 0, 1, 'dhlk:view', 1);
INSERT INTO `dhlk_basic_menu` VALUES (5, 'menu/view', '菜单管理', '/system/menu', 0, 1, 'dhlk:view', 1);
INSERT INTO `dhlk_basic_menu` VALUES (6, 'org/view', '组织架构', '/system/dept', 0, 1, 'dhlk:view', 1);
INSERT INTO `dhlk_basic_menu` VALUES (7, 'sysLog/view', '活动日志', 'sysLog/searchLogFile', 0, 1, 'dhlk:view', 1);
INSERT INTO `dhlk_basic_menu` VALUES (8, 'sysLog/downZipFile', '日志下载', 'sysLog/downZipFile', 0, 7, 'sysLog:downZipFile', 2);
INSERT INTO `dhlk_basic_menu` VALUES (9, 'user/insert', '新增用户', 'user/save', 0, 3, 'user:save', 2);
INSERT INTO `dhlk_basic_menu` VALUES (10, 'user/update', '修改用户', 'user/update', 0, 3, 'user:save', 2);
INSERT INTO `dhlk_basic_menu` VALUES (11, 'user/delete', '删除用户', 'user/delete', 0, 3, 'user:delete', 2);
INSERT INTO `dhlk_basic_menu` VALUES (12, 'role/insert', '新增角色', 'role/save', 0, 4, 'role:save', 2);
INSERT INTO `dhlk_basic_menu` VALUES (13, 'role/update', '修改角色', 'role/update', 0, 4, 'role:save', 2);
INSERT INTO `dhlk_basic_menu` VALUES (14, 'role/delete', '删除角色', 'role/delete', 0, 4, 'role:delete', 2);
INSERT INTO `dhlk_basic_menu` VALUES (15, 'menu/insert', '新增菜单', 'menu/insert', 0, 5, 'menu:save', 2);
INSERT INTO `dhlk_basic_menu` VALUES (16, 'menu/update', '修改菜单', 'menu/update', 0, 5, 'menu:save', 2);
INSERT INTO `dhlk_basic_menu` VALUES (17, 'menu/delete', '删除菜单', 'menu/delete', 0, 5, 'menu:delete', 2);
INSERT INTO `dhlk_basic_menu` VALUES (19, 'org/update', '修改', 'org/update', 0, 6, 'org:save', 2);
INSERT INTO `dhlk_basic_menu` VALUES (20, 'org/delete', '删除', 'org/delete', 0, 6, 'org:delete', 2);
INSERT INTO `dhlk_basic_menu` VALUES (23, 'permissions/insert', '分配权限', 'permissions/save', 0, 4, 'permissions:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (25, 'user/updatePassword', '修改密码', 'user/updatePassword', 0, 3, 'user:updatePassword', 1);
INSERT INTO `dhlk_basic_menu` VALUES (32, 'user/disable', '禁用用户', 'user/isEnable', 0, 3, 'user:isEnable', 2);

INSERT INTO `dhlk_basic_menu` VALUES (38, 'productDevices/view', '工业设备', 'productDevices/findList', 0, 2, 'dhlk:view', 0);
INSERT INTO `dhlk_basic_menu` VALUES (39, 'productDevices/insert', '新增设备', 'productDevices/save', 0, 38, 'productDevices:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (40, 'productDevices/update', '编辑设备', 'productDevices/save', 0, 38, 'productDevices:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (41, 'productDevices/delete', '删除设备', 'productDevices/delete', 0, 38, 'productDevices:delete', 1);

INSERT INTO `dhlk_basic_menu` VALUES (42, 'netDevices/view', '网络设备', 'netDevices/findList', 0, 2, 'dhlk:view', 0);
INSERT INTO `dhlk_basic_menu` VALUES (43, 'netDevices/insert', '新增设备', 'netDevices/save', 0, 42, 'netDevices:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (44, 'netDevices/update', '编辑设备', 'netDevices/save', 0, 42, 'netDevices:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (45, 'netDevices/delete', '删除设备', 'netDevices/delete', 0, 42, 'netDevices:delete', 1);
INSERT INTO `dhlk_basic_menu` VALUES (57, 'netDevices/agent', 'bi控制器代理', 'netDevices/agent', 0, 42, 'netDevices:agent', 1);


INSERT INTO `dhlk_basic_menu` VALUES (46, 'devicesAttrSet/view', '参数管理', 'devicesAttrSet/findList', 0, 2, 'dhlk:view', 0);
INSERT INTO `dhlk_basic_menu` VALUES (47, 'devicesAttrSet/insert', '新增参数', 'devicesAttrSet/save', 0, 46, 'devicesAttrSet:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (48, 'devicesAttrSet/update', '编辑参数', 'devicesAttrSet/save', 0, 46, 'devicesAttrSet:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (49, 'devicesAttrSet/delete', '删除参数', 'devicesAttrSet/delete', 0, 46, 'devicesAttrSet:delete', 1);
INSERT INTO `dhlk_basic_menu` VALUES (50, 'devicesClassify/view', '类型管理', 'devicesClassify/findList', 0, 2, 'dhlk:view', 0);
INSERT INTO `dhlk_basic_menu` VALUES (51, 'devicesClassify/insert', '新增类型', 'devicesClassify/save', 0, 50, 'devicesClassify:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (52, 'devicesClassify/update', '编辑类型', 'devicesClassify/save', 0, 50, 'devicesClassify:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (53, 'devicesClassify/delete', '删除类型', 'devicesClassify/delete', 0, 50, 'devicesClassify:delete', 1);
INSERT INTO `dhlk_basic_menu` VALUES (54, 'productNet/view', '配置管理', 'productNet/findList', 0, 2, 'dhlk:view', 0);
INSERT INTO `dhlk_basic_menu` VALUES (56, 'productNet/update', '编辑配置', 'productNet/save', 0, 54, 'productNet:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (66, 'user/distributionOrg', '分配归属机构', 'user/save', 0, 3, 'user:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (67, 'user/distributionRole', '分配角色', 'user/save', 0, 3, 'user:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (69, 'org/insertDept', '添加下级', 'org/save', 0, 6, 'org:save', 1);
INSERT INTO `dhlk_basic_menu` VALUES (70, 'user/startuse', '启用用户', 'user/isEnable', 0, 3, 'user:isEnable', 2);
INSERT INTO `dhlk_basic_menu` VALUES (72, 'netFault/dealFault', '故障处理', 'netFault/dealFault', 0, 42, 'netFault:dealFault', 1);
INSERT INTO `dhlk_basic_menu` VALUES (73, 'dataControl', '数据中台', NULL, 0, 0, 'dhlk:view', 0);
INSERT INTO `dhlk_basic_menu` VALUES (74, 'analysisControl', '商业智能', NULL, 0, 0, 'dhlk:view', 0);
INSERT INTO `dhlk_basic_menu` VALUES (75, 'monitorCenter', '监测中心', NULL, 0, 0, 'dhlk:view', 0);
INSERT INTO `dhlk_basic_menu` VALUES (76, 'event/delete', '删除事件', 'event/delete', 0, 75, 'event:delete', 0);
INSERT INTO `dhlk_basic_menu` VALUES (77, 'monitor/list', '预警监控', 'monitor/list', 0, 1, 'monitor:index', 1);




/*==============================================================*/
/* Table: API接口                                         */
/*==============================================================*/
drop table if exists dhlk_api_list;
create table dhlk_api_list
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   version              varchar(10)                    DEFAULT NULL COMMENT '版本',
   title                varchar(200)                   DEFAULT NULL COMMENT '接口名称',
   content              varchar(500)                   DEFAULT NULL COMMENT '接口说明',
   create_time          timestamp                      DEFAULT NOW() COMMENT '创建时间',
   status               integer                        DEFAULT '0' COMMENT '状态  0正常 1禁用',
   classify_id          integer                        DEFAULT NULL COMMENT '接口分类',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='API接口';


/*==============================================================*/
/* Table: 元数据表列                           */
/*==============================================================*/
drop table if exists dhlk_basic_meta_table_column;
create table dhlk_basic_meta_table_column
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   column_enname        varchar(20)                    DEFAULT NULL COMMENT '列编码',
   column_cnname        varchar(20)                    DEFAULT NULL COMMENT '列名',
   data_type            varchar(10)                    DEFAULT NULL COMMENT '数据类型',
   length               integer                        DEFAULT NULL COMMENT '数据长度',
   table_id             integer                        DEFAULT NULL COMMENT '元数据表',
   status               integer                        DEFAULT '0' COMMENT '状态  0正常 2删除',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据表列';

/*==============================================================*/
/* Table: 设备属性集合                           */
/*==============================================================*/
DROP TABLE IF EXISTS dhlk_basic_devices_attr_set;
CREATE TABLE dhlk_basic_devices_attr_set
(
   id                   INTEGER                    NOT NULL AUTO_INCREMENT,
   `name`               VARCHAR(100)               DEFAULT NULL COMMENT '名称',
   `describe`           VARCHAR(200)               DEFAULT NULL COMMENT '描述',
   tenant_id            integer                        DEFAULT NULL COMMENT '租户',
   attr_set_id          VARCHAR(100)               DEFAULT NULL COMMENT '属性ID',
   PRIMARY KEY  (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='设备属性集合';

/*==============================================================*/
/* Table: 设备属性明细                           */
/*==============================================================*/
DROP TABLE IF EXISTS dhlk_basic_devices_attr_detail;
CREATE TABLE dhlk_basic_devices_attr_detail
(
   id                    INTEGER                    NOT NULL AUTO_INCREMENT,
   attr                  VARCHAR(100)               DEFAULT NULL COMMENT '属性名称',
   `code`                  VARCHAR(100)               DEFAULT NULL COMMENT '编码',
   data_type             VARCHAR(20)                DEFAULT NULL COMMENT '数据类型',
   data_length           INTEGER                    DEFAULT NULL COMMENT '数据长度',
   unit                  VARCHAR(100)               DEFAULT NULL COMMENT '单位',
   attr_set_id           INTEGER                    DEFAULT NULL COMMENT '属性集合',
   PRIMARY KEY  (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='设备属性明细';



/*==============================================================*/
/* Table: 设备类型属性明细                           */
/*==============================================================*/
DROP TABLE IF EXISTS dhlk_basic_devices_classify_detail;
CREATE TABLE dhlk_basic_devices_classify_detail
(
   id                   INTEGER                       NOT NULL AUTO_INCREMENT,
   attr_set_id          INTEGER                       DEFAULT NULL COMMENT '属性集合',
   attr_sub_name        VARCHAR(100)                  DEFAULT NULL COMMENT '属性简称',
   attr_detail_id       INTEGER                       DEFAULT NULL COMMENT '属性明细',
   devices_classify_id  VARCHAR(100)                  DEFAULT NULL COMMENT '设备分类',
   PRIMARY KEY  (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='设备类型属性明细';
/*==============================================================*/
/* Table: 公共附件表                          */
/*==============================================================*/
drop table if exists dhlk_basic_attachment;
create table dhlk_basic_attachment
(
    id          INTEGER       NOT NULL AUTO_INCREMENT,
    name        varchar(100),
    save_name   varchar(50),
    path        varchar(100),
    suffix      varchar(10),
    create_time timestamp,
    data_id     varchar(50),
    PRIMARY KEY  (id)
) ENGINE = INNODB DEFAULT CHARSET = utf8 AUTO_INCREMENT = 1 COMMENT '公共附件表';

/*==============================================================*/
/* Table: 网络设备类型                                     */
/*==============================================================*/
drop table if exists dhlk_dm_net_type;
create table dhlk_dm_net_type
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   name                 varchar(100)                   DEFAULT NULL COMMENT '类型名称',
   status               integer                        DEFAULT '0' COMMENT '状态  0正常 2删除',
   PRIMARY KEY  (id)
) ENGINE = INNODB DEFAULT CHARSET = utf8 AUTO_INCREMENT = 1 COMMENT '网络设备类型';
-- ----------------------------
-- Records of dhlk_dm_net_type
-- ----------------------------
INSERT INTO `dhlk_dm_net_type` VALUES (1, '大数据一体机', 0);
INSERT INTO `dhlk_dm_net_type` VALUES (2, 'node-BI', 0);
INSERT INTO `dhlk_dm_net_type` VALUES (3, 'sensor-BI', 0);
INSERT INTO `dhlk_dm_net_type` VALUES (4, 'HCI-BI', 0);
/*==============================================================*/
/* Table: 设备类型分类                                     */
/*==============================================================*/
drop table if exists dhlk_dm_classify_type;
create table dhlk_dm_classify_type
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   name                 varchar(100)                   DEFAULT NULL COMMENT '分类名称',
   status               integer                        DEFAULT '0' COMMENT '状态  0正常 2删除',
   PRIMARY KEY  (id)
) ENGINE = INNODB DEFAULT CHARSET = utf8 AUTO_INCREMENT = 1 COMMENT '设备类型分类';
-- ----------------------------
-- Records of dhlk_dm_classify_type
-- ----------------------------
INSERT INTO `dhlk_dm_classify_type` VALUES (1, '基础类型', 0);
INSERT INTO `dhlk_dm_classify_type` VALUES (2, '其他类型', 0);


/*==============================================================*/
/* Table: 设备分类                           */
/*==============================================================*/
DROP TABLE IF EXISTS `dhlk_basic_devices_classify`;
CREATE TABLE `dhlk_basic_devices_classify`
(
   `id`                   VARCHAR(100)                  NOT NULL,
   `classify_name`        VARCHAR(100)                  DEFAULT NULL COMMENT '分类名称',
   `describe`             VARCHAR(200)                  DEFAULT NULL COMMENT '描述',
   `classify_id`          VARCHAR(200)                  DEFAULT NULL COMMENT '类型',
   `tenant_id`            integer                        DEFAULT NULL COMMENT '租户',
   `type_id`              INTEGER                       DEFAULT NULL COMMENT '设备类型分类id',
   PRIMARY KEY  (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='设备类型管理';
/*==============================================================*/
/* Table: 事件管理                          */
/*==============================================================*/
DROP TABLE IF EXISTS `dhlk_basic_event`;
CREATE TABLE `dhlk_basic_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alarmId` varchar(64) DEFAULT NULL COMMENT '事件id',
  `tenantId` varchar(100) DEFAULT NULL COMMENT '租户id',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `originator` varchar(100) DEFAULT NULL COMMENT '来源',
  `severity` varchar(100) DEFAULT NULL COMMENT '安全级别',
  `status` varchar(100) DEFAULT NULL COMMENT '状态',
  `startTs` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `endTs` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `ackTs` bigint(20) DEFAULT NULL COMMENT '确认时间',
  `clearTs` bigint(20) DEFAULT NULL COMMENT '清除时间',
  `details` text COMMENT '详情',
  `propagate` tinyint(4) DEFAULT NULL COMMENT '传播',
  `tbId` varchar(255) DEFAULT NULL COMMENT 'tb id',
  `createdTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `dhlk_memory_size`;
CREATE TABLE `dhlk_memory_size` (
  `shift_date` varchar(32) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `tenant` varchar(32) DEFAULT NULL,
  `totalSize` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



/*==============================================================*/
/* Function: 创建函数查出以逗号分开的字符串的第一个数                */
/*==============================================================*/
DELIMITER $$
DROP FUNCTION IF EXISTS `func_splitString`$$
CREATE FUNCTION `func_splitString`
( f_string VARCHAR(1000),f_delimiter VARCHAR(5),f_order INT)
RETURNS VARCHAR(255) CHARSET utf8
BEGIN
DECLARE str VARCHAR(255) DEFAULT '';
SET str = REVERSE(SUBSTRING_INDEX(REVERSE(SUBSTRING_INDEX(f_string,f_delimiter,f_order)),f_delimiter,1));
RETURN str;
END$$
DELIMITER ;

/*==============================================================*/
/* 登录名区分大小写      */
/*==============================================================*/
ALTER TABLE dhlk_basic_user MODIFY COLUMN `name` VARCHAR(50) BINARY;









/*============E2CUI升级增加数据表   2020-10-15===================*/

/*==============================================================*/
/* Table: 桌面菜单  */
/*==============================================================*/
drop table if exists dhlk_basic_desktop;
create table dhlk_basic_desktop
(
   id                   integer                      NOT NULL AUTO_INCREMENT,
   menu_id 			    integer					     DEFAULT NULL COMMENT '菜单id',
   user_id              integer                      DEFAULT NULL COMMENT '用户Id',
   parent_id            integer                      DEFAULT NULL COMMENT '菜单父Id',
   primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='桌面菜单';

/*==============================================================*/
/* Table: 用户点击一个模块,记录点击次数  */
/*==============================================================*/
drop table if exists dhlk_basic_module_click ;
create table dhlk_basic_module_click
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   module_name          varchar(50)                    DEFAULT NULL COMMENT '点击模块名称',
   login_name           varchar(50)                    DEFAULT NULL COMMENT '用户名',
   click_time           timestamp                      DEFAULT NOW() COMMENT '点击时间',
   click_num			integer						   NOT NULL  COMMENT '点击次数',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块点击';

/*==============================================================*/
/* Table: 系统消息  */
/*==============================================================*/
drop table if exists dhlk_basic_sys_message;
create table dhlk_basic_sys_message
(
   id                   integer                      NOT NULL AUTO_INCREMENT,
   title                varchar(100)                 DEFAULT NULL COMMENT '标题',
   describes            varchar(200)                 DEFAULT NULL COMMENT '描述',
   content              long varchar                 DEFAULT NULL COMMENT '内容',
   create_time          timestamp                    DEFAULT NOW() COMMENT '创建时间',
   tenant_id            integer                      DEFAULT NULL COMMENT '租户id',
   is_read              long varchar                 DEFAULT NULL COMMENT '记录租户读取id用逗号隔开',
   primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息';

/*==============================================================*/
/* Table: app菜单                                      */
/*==============================================================*/
drop table if exists dhlk_app_menu;
create table dhlk_app_menu
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   code                 varchar(50)                    DEFAULT NULL COMMENT '编码',
   name                 varchar(50)                    DEFAULT NULL COMMENT '名称',
   url                  varchar(100)                   DEFAULT NULL COMMENT '请求连接',
   status               integer                        DEFAULT '0'  COMMENT '状态  0正常 1禁用 2删除',
   parent_id            int(11)                        DEFAULT NULL COMMENT '父节点',
   perms                varchar(500)                   DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
   type                 integer                        DEFAULT '0'  COMMENT '类型   0菜单   1按钮',
   app_code             varchar(100)                   DEFAULT NULL COMMENT 'app应用标识',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app菜单';

/*==============================================================*/
/* Table: app权限                               */
/*==============================================================*/
drop table if exists dhlk_app_permissions;
create table dhlk_app_permissions
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   menu_id              integer                        DEFAULT NULL COMMENT '菜单id',
   role_id              integer                        DEFAULT NULL COMMENT '角色id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app权限';

/*==============================================================*/
/* Table: 应用租户                               */
/*==============================================================*/
drop table if exists dhlk_app_tenant;
create table dhlk_app_tenant
(
   id                   integer                        NOT NULL AUTO_INCREMENT,
   app_id               integer                        DEFAULT NULL COMMENT 'app应用id',
   app_code             varchar(100)                   DEFAULT NULL COMMENT 'app应用标识',
   tenant_id            integer                        DEFAULT NULL COMMENT '租户id',
   primary key  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用租户';



/*============应用市场增加数据表   2020-10-15=====================*/


/*==============================================================*/
/* Table: 本地应用  */
/*==============================================================*/
drop table if exists dhlk_local_app_info;
CREATE TABLE `dhlk_local_app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(50) DEFAULT NULL COMMENT 'app名称',
  `app_address` varchar(500) DEFAULT NULL COMMENT 'app地址',
  `app_logo` mediumtext COMMENT 'app logo',
  `other` text COMMENT '其他信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户对象',
  `store_app_id` int(10) DEFAULT NULL COMMENT '下载次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='app本地应用信息';

/*2020-10-19 增加字段*/
alter table dhlk_local_app_info add token_parameters int(1) COMMENT '是否token传参 0传 1不传';
alter table dhlk_local_app_info add put_advertising int(1) COMMENT '是否投屏广告 0传 1不传';

/*==============================================================*/
/* Table: 市场应用  */
/*==============================================================*/
drop table if exists dhlk_store_app_info;
CREATE TABLE `dhlk_store_app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(50) DEFAULT NULL COMMENT 'app名称',
  `app_address` varchar(500) DEFAULT NULL COMMENT 'app地址',
  `app_logo` mediumtext COMMENT 'app logo',
  `group_id` int(11) DEFAULT NULL COMMENT '分组信息',
  `other` text COMMENT '其他信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `system_support` varchar(255) DEFAULT NULL COMMENT '系统支持',
  `soft_features` mediumtext COMMENT '软件特色',
  `soft_function` mediumtext COMMENT '软件功能',
  `system_screenshot` mediumtext COMMENT '系统截图',
  `download_count` int(10) DEFAULT NULL COMMENT '下载次数',
  `app_type` varchar(255) DEFAULT NULL COMMENT '应用类型',
  `app_industry` varchar(255) DEFAULT NULL COMMENT '应用行业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COMMENT='app store应用信息';

/*2020-10-19 增加字段*/
alter table dhlk_store_app_info add token_parameters int(1) COMMENT '是否token传参 0传 1不传';
alter table dhlk_store_app_info add put_advertising int(1) COMMENT '是否投屏广告 0传 1不传';
alter table dhlk_store_app_info add app_code varchar(20) COMMENT 'app编码';
alter table dhlk_store_app_info add year_fee decimal(10,2) DEFAULT NULL COMMENT '年费';
/*==============================================================*/
/* Table: 市场应用分类  */
/*==============================================================*/
drop table if exists dhlk_store_group_info;
create table if not exists dhlk_store_group_info
(
	id int auto_increment
		primary key,
	group_name varchar(50) null comment '分组名称',
	other text null comment '其他信息',
	create_time timestamp default CURRENT_TIMESTAMP not null comment '添加时间'
)
comment 'app store分组信息';

INSERT INTO `dhlk_store_group_info`(`group_name`, `other`) VALUES ('资产性能管理', 'string' );
INSERT INTO `dhlk_store_group_info`(`group_name`, `other`) VALUES ('精益生产管理', 'string' );
INSERT INTO `dhlk_store_group_info`(`group_name`, `other`) VALUES ('厂务安全管理', 'string');
INSERT INTO `dhlk_store_group_info`(`group_name`, `other`) VALUES ('其他', 'string');

INSERT INTO `dhlk_basic_menu`(`id`, `code`, `name`, `url`, `status`, `parent_id`, `perms`, `type`) VALUES (125, 'localApp/view', '本地应用', '/application', 0, 0, 'app', 0);
INSERT INTO `dhlk_basic_menu`(`id`, `code`, `name`, `url`, `status`, `parent_id`, `perms`, `type`) VALUES (126, 'appShop/view', '应用市场', '/application', 0, 0, 'app', 0);
INSERT INTO `dhlk_basic_menu`(`id`, `code`, `name`, `url`, `status`, `parent_id`, `perms`, `type`) VALUES (128, 'localApp/delLocalApp', '删除应用', 'localApp/delLocalApp', 0, 125, 'localApp:delLocalApp', 1);
INSERT INTO `dhlk_basic_menu`(`id`, `code`, `name`, `url`, `status`, `parent_id`, `perms`, `type`) VALUES (130, 'localApp/addLocalApp', '下载应用', '/application/appShop', 0, 125, 'dhlk:view', 1);
INSERT INTO `dhlk_basic_menu`(`id`, `code`, `name`, `url`, `status`, `parent_id`, `perms`, `type`) VALUES (131, 'appShop/delStoreApp', '删除应用', 'appShop/delStoreApp', 0, 126, 'appShop:delStoreApp', 1);
INSERT INTO `dhlk_basic_menu`(`id`, `code`, `name`, `url`, `status`, `parent_id`, `perms`, `type`) VALUES (132, 'appShop/addStoreApp', '添加应用', 'appShop/addStoreApp', 0, 126, 'appShop:addStoreApp', 1);
INSERT INTO `dhlk_basic_menu`(`id`, `code`, `name`, `url`, `status`, `parent_id`, `perms`, `type`) VALUES (133, 'appShop/detailStoreApp', '查看应用', 'appShop/detailStoreApp', 0, 126, 'appShop:detailStoreApp', 1);
INSERT INTO `dhlk_basic_menu`(`id`, `code`, `name`, `url`, `status`, `parent_id`, `perms`, `type`) VALUES (134, 'appShop/editStoreApp', '编辑应用', 'appShop/editStoreApp', 0, 126, 'appShop:editStoreApp', 1);


INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (400, 125, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (401, 126, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (403, 128, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (405, 130, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (406, 131, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (407, 132, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (1199, 133, 1);
INSERT INTO `dhlk_basic_permissions`(`id`, `menu_id`, `role_id`) VALUES (1210, 134, 1);



/*==============================================================*/
/* Table: 数据库连接                                            */
/*==============================================================*/
drop table if exists dhlk_bi_database;
create table dhlk_bi_database
(
   id                integer                        NOT NULL AUTO_INCREMENT,
   name              varchar(50)                    DEFAULT NULL COMMENT '连接名称',
   url          	 varchar(50)                    DEFAULT NULL COMMENT '连接地址',
   port            	 integer                        DEFAULT NULL COMMENT '端口',
   username      	 varchar(20)                    DEFAULT NULL COMMENT '用户名',
   password       	 varchar(20)                    DEFAULT NULL COMMENT '密码',
   create_user   	 integer                        DEFAULT NULL COMMENT '添加人',
   create_time  	 timestamp                      DEFAULT NOW() COMMENT '添加时间',
    primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库连接';


/*==============================================================*/
/* Table: 报表面板                                              */
/*==============================================================*/
drop table if exists dhlk_bi_panel;
create table dhlk_bi_panel
(
   id                integer                        NOT NULL AUTO_INCREMENT,
   name              varchar(50)                    DEFAULT NULL COMMENT '名称',
   content           long varchar                   DEFAULT NULL COMMENT '内容',
   create_user   	 integer                        DEFAULT NULL COMMENT '添加人',
   create_time  	 timestamp                      DEFAULT NOW() COMMENT '添加时间',
   issue             integer                        DEFAULT 0 COMMENT '发布 0 未发布 1 已发布',
   primary key  (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表面板';