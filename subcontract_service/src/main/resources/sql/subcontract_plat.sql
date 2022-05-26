/*==============================================================*/
/* Table: 项目发布                                           */
/*==============================================================*/
drop table if exists project_issue;
create table project_issue
(
    id               integer   NOT NULL AUTO_INCREMENT,
    project_name     varchar(60)  DEFAULT NULL COMMENT '项目名称',
    is_approval      integer      DEFAULT NULL COMMENT '是否立项',
    project_cycle    integer      DEFAULT NULL COMMENT '项目周期',
    delivery_method  varchar(20)  DEFAULT NULL COMMENT '交付方式',
    payment_method   integer      DEFAULT NULL COMMENT '付款方式',
    other_required   varchar(60)  DEFAULT NULL COMMENT '其他要求',
    project_managerr varchar(20)  DEFAULT NULL COMMENT '项目责任人',
    payment_product  varchar(60)  DEFAULT NULL COMMENT '交付产物',
    validity         timestamp NULL COMMENT '有效期',
    contact_way      varchar(20)  DEFAULT NULL COMMENT '联系方式',
    project_budget   integer      DEFAULT NULL COMMENT '项目预算',
    start_time       timestamp NULL COMMENT '项目计划开始时间',
    project_type     integer      DEFAULT NULL COMMENT '项目类别',
    project_related  integer      DEFAULT NULL COMMENT '关联项目',
    funds_type       integer      DEFAULT NULL COMMENT '资金类型',
    demand_introduce mediumtext   DEFAULT NULL COMMENT '需求介绍',
    remark           varchar(200) DEFAULT NULL COMMENT '备注',
    attachment_addr  mediumtext   DEFAULT NULL COMMENT '附件',
    company_id       integer      DEFAULT NULL COMMENT '企业id',
    create_time      timestamp    DEFAULT NOW() COMMENT '创建时间',
    score            integer      DEFAULT NULL COMMENT '评分',
    audit_result     integer      DEFAULT 0 COMMENT '审核结果 0未审核 1通过 2不通过',
    no_reason        varchar(200) DEFAULT NULL COMMENT '不通过理由',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='项目发布';


/*==============================================================*/
/* Table: 金融服务商                       						*/
/*==============================================================*/
drop table if exists financial_provider;
create table financial_provider
(
    id              integer NOT NULL AUTO_INCREMENT,
    provider_name   varchar(60)    DEFAULT NULL COMMENT '服务商名称',
    contract_amount decimal(10, 3) DEFAULT 0 COMMENT '合同金额',
    recycling_cycle integer        DEFAULT NULL COMMENT '资金回收周期',
    contact         varchar(20)    DEFAULT NULL COMMENT '联系人',
    contact_way     varchar(20)    DEFAULT NULL COMMENT '联系方式',
    project_id      integer        DEFAULT NULL COMMENT '项目id',
    contract_pic    text           DEFAULT NULL COMMENT '合同信息',
    create_time     timestamp      DEFAULT NOW() COMMENT '创建时间',
    company_id       integer      DEFAULT NULL COMMENT '服务商id',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='金融服务商';



/*==============================================================*/
/* Table: 咨询服务商                                            */
/*==============================================================*/
drop table if exists consulting_provider;
create table consulting_provider
(
    id              integer        NOT NULL AUTO_INCREMENT,
    company_name    varchar(60)    DEFAULT NULL COMMENT '企业名称',
    contract_amount decimal(10, 3) DEFAULT 0 COMMENT '合同金额',
    payment_method  integer        DEFAULT NULL COMMENT '支付方式',
    linkman         varchar(20)    DEFAULT NULL COMMENT '联系人',
    contact_way     varchar(20)    DEFAULT NULL COMMENT '联系方式',
    contract_pic     text     DEFAULT NULL COMMENT '合同信息',
    project_id      integer        DEFAULT NULL COMMENT '项目id',
    create_time     timestamp      DEFAULT NOW() COMMENT '创建时间',
    company_id       integer      DEFAULT NULL COMMENT '企业id',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='咨询服务商';



/*==============================================================*/
/* Table: 交付申请                         					    */
/*==============================================================*/
drop table if exists delivery_apply_for;
create table delivery_apply_for
(
    id               integer NOT NULL AUTO_INCREMENT,
    delivery_project varchar(60)  DEFAULT NULL COMMENT '交付项目',
    remark           varchar(200) DEFAULT NULL COMMENT '备注',
    delivery_product mediumtext   DEFAULT NULL COMMENT '交付产物',
    create_time      timestamp    DEFAULT NOW() COMMENT '时间',
    status           integer      DEFAULT NULL COMMENT '确认状态 0拒绝 1确认',
    project_id       integer      DEFAULT NULL COMMENT '项目id',
    company_id       integer      DEFAULT NULL COMMENT '企业id',
    type             integer      DEFAULT NULL COMMENT '类型 0开发生产 1安装施工',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='交付申请';



/*==============================================================*/
/* Table: 开发生产                           				    */
/*==============================================================*/
drop table if exists dev_produce;
create table dev_produce
(
    id             integer NOT NULL AUTO_INCREMENT,
    progress       varchar(20) DEFAULT NULL COMMENT '进度',
    weekly_reports mediumtext  DEFAULT NULL COMMENT '周报',
    create_time    timestamp   DEFAULT NOW() COMMENT '日期',
    project_id     integer     DEFAULT NULL COMMENT '项目id',
    company_id     integer     DEFAULT NULL COMMENT '企业id',
    type           integer     DEFAULT NULL COMMENT '类型 0开发生产 1安装施工',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='开发生产';


/*==============================================================*/
/* Table: 收付款信息                              				*/
/*==============================================================*/
drop table if exists receiving_info;
create table receiving_info
(
    id           integer NOT NULL AUTO_INCREMENT,
    payee        integer        DEFAULT NULL COMMENT '收款方',
    payee_num    integer        DEFAULT NULL COMMENT '收款方账号',
    open_bank    varchar(50)    DEFAULT NULL COMMENT '开户行',
    account_name varchar(50)    DEFAULT NULL COMMENT '收款账户名称',
    money        decimal(10, 3) DEFAULT 0 COMMENT '金额',
    payer        integer        DEFAULT NULL COMMENT '付款方',
    time         timestamp      DEFAULT NOW() COMMENT '时间',
    remark       varchar(200)   DEFAULT NULL COMMENT '备注',
    order_no     varchar(20)    DEFAULT NULL COMMENT '订单号',
    card_no      integer        DEFAULT NULL COMMENT '银行卡号',
    evidence     varchar(60)    DEFAULT NULL COMMENT '凭据',
    project_id   integer        DEFAULT NULL COMMENT '项目id',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='收付款信息';



/*==============================================================*/
/* Table:关闭项目                            					*/
/*==============================================================*/
drop table if exists project_close;
create table project_close
(
    id               integer      NOT NULL AUTO_INCREMENT,
    type             integer      DEFAULT NULL COMMENT '类型 0终止项目 1项目完成',
    reason           varchar(200) DEFAULT NULL COMMENT '原由',
    approval_suggest varchar(200) DEFAULT NULL COMMENT '审批建议',
    apply_time       timestamp    DEFAULT NOW() COMMENT '申请时间',
    approval_time    timestamp    NULL COMMENT '审批时间',
    project_id       integer      DEFAULT NULL COMMENT '项目id',
    audit_result     integer      DEFAULT NULL COMMENT '审批结果 0未审核 1不通过 2通过',
    company_id       integer      DEFAULT NULL COMMENT '企业id',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='关闭项目';


/*==================================================*/
/*Table:广告*/
/*==================================================*/
drop table if exists advert;
create table advert
(
    id           int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    address_no   varchar(50)   DEFAULT null COMMENT '位置编码',
    name         varchar(50)   DEFAULT null COMMENT '名称',
    data_id      varchar(200)  DEFAULT null COMMENT '图片',
    pixel_length decimal(7, 2) DEFAULT null COMMENT '长/像素',
    pixel_wide   decimal(7, 2) DEFAULT null COMMENT '宽/像素',
    remark       mediumtext    DEFAULT null COMMENT '备注',
    create_time  timestamp     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    is_delete    int(11)       DEFAULT null COMMENT '伪删除',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='广告';
/*==================================================*/
/*Table:企业管理*/
/*==================================================*/
drop table if exists company;
create table company
(
    id                    int(11)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    company_name          varchar(100) DEFAULT null COMMENT '企业名称',
    capital               varchar(50)  DEFAULT null COMMENT '注册资金',
    company_address       varchar(100) DEFAULT null COMMENT '公司详细地址',
    company_nature        varchar(20)  DEFAULT null COMMENT '企业性质',
    turnover_year         varchar(50)  DEFAULT null COMMENT '每年营业额',
    legal_person          varchar(20)  DEFAULT null COMMENT '法人',
    legal_num             varchar(20)  DEFAULT null COMMENT '法人证件号',
    company_scale         varchar(20)  DEFAULT null COMMENT '企业规模',
    business              varchar(20)  DEFAULT null COMMENT '行业',
    company_type          varchar(200) DEFAULT null COMMENT '企业类型',
    company_license       varchar(200) DEFAULT null COMMENT '工商营业执照(附件id,逗号隔开)',
    license_validity      timestamp    NUll COMMENT '工商执照有效期',
    credit_code           varchar(50)  DEFAULT null COMMENT '社会统一信用代码',
    identity_card         varchar(200) DEFAULT null COMMENT '法人身份证(附件id,逗号隔开)',
    company_authorization varchar(200) DEFAULT null COMMENT '公司授权书(附件id,逗号隔开)',
    audit_status          int(11)      DEFAULT 0    COMMENT '审核状态 0 待审核 1 审核通过 2 审核不通过',
    audit_reason          text         DEFAULT null COMMENT '审核理由',
    is_blacklist          int(11)      DEFAULT 0    COMMENT '是否拉入黑名单(0 正常 1 黑名单)',
    create_time           timestamp    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    audit_time            timestamp    NUll COMMENT '审核时间',
    blacklist_reason      text         DEFAULT NUll COMMENT '拉黑原因',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='企业管理';
/*==================================================*/
/*Table:企业主页*/
/*==================================================*/
drop table if exists company_pages;
create table company_pages
(
    id                int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    home_page         mediumtext    DEFAULT null COMMENT '广告',
    company_introduce mediumtext    DEFAULT null COMMENT '企业介绍',
    credit_value      decimal(5, 2) DEFAULT null COMMENT '企业信用值',
    certificate       mediumtext    DEFAULT null COMMENT '荣誉证书',
    company_case      mediumtext    DEFAULT null COMMENT '案例',
    solution          mediumtext    DEFAULT null COMMENT '解决方案',
    company_id        int(11)       DEFAULT null COMMENT '企业',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='企业主页';
/*==================================================*/
/*Table:优惠卷*/
/*==================================================*/
drop table if exists coupons;
create table coupons
(
    id          int(11)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    coupon_name varchar(50)      DEFAULT null COMMENT '优惠卷名称',
    start_time  timestamp      DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    end_time    timestamp NUll COMMENT '结束时间',
    face_value  decimal(10, 3) DEFAULT null COMMENT '面额/折扣',
    data_id     varchar(100)   DEFAULT null COMMENT '图片',
    app_ids     varchar(200)   DEFAULT null COMMENT 'APP',
    create_time timestamp      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    count       int(11)        DEFAULT null COMMENT '数量',
    type        int(11)        DEFAULT '0' COMMENT '类型 (0 面额 1 折扣)',
    coupon_no   varchar(50)    DEFAULT null COMMENT '优惠卷编号',
    user_id     int(11)        DEFAULT null COMMENT '生成人',
    is_delete   int(11)        DEFAULT '0' COMMENT '伪删除 0 正常 1 删除',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='优惠卷';

/*==================================================*/
/*Table:发票管理*/
/*==================================================*/
drop table if exists invoice;
create table invoice
(
    id              int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    order_id        int(11)        DEFAULT null COMMENT '订单',
    submit_time     timestamp      DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
    amount          int(11)        DEFAULT null COMMENT '开票张数',
    total_money     decimal(10, 3) DEFAULT null COMMENT '总金额',
    invoice_info_id int(11)        DEFAULT null COMMENT '发票信息',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='发票管理';
/*==================================================*/
/*Table:发票地址信息*/
/*==================================================*/
drop table if exists invoice_info;
create table invoice_info
(
    id                int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    company_name      varchar(100) DEFAULT null COMMENT '企业名称',
    company_address   varchar(200) DEFAULT null COMMENT '企业地址',
    company_phone     varchar(20)  DEFAULT null COMMENT '企业电话',
    bank_account      varchar(50)  DEFAULT null COMMENT '银行账号',
    open_bank         varchar(100) DEFAULT null COMMENT '开户行',
    tax_no            varchar(50)  DEFAULT null COMMENT '税务登记号',
    consignee         varchar(20)  DEFAULT null COMMENT '收件人',
    phone             varchar(20)  DEFAULT null COMMENT '联系电话',
    consignee_address varchar(200) DEFAULT null COMMENT '收件地址',
    user_id           int(11)      DEFAULT null COMMENT '用户',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='发票地址信息';
/*==================================================*/
/*Table:系统菜单*/
/*==================================================*/
drop table if exists menu;
create table menu
(
    id        int(11) NOT NULL AUTO_INCREMENT COMMENT '',
    code      varchar(50)  DEFAULT null COMMENT '编码',
    name      varchar(50)  DEFAULT null COMMENT '名称',
    url       varchar(100) DEFAULT null COMMENT '请求连接',
    status    int(11)      DEFAULT null COMMENT '状态  0正常 1禁用 2删除',
    parent_id int(11)      DEFAULT null COMMENT '父节点',
    perms     varchar(100) DEFAULT null COMMENT '多个用逗号分隔，如：user:list,user:create',
    type      int(11)      DEFAULT null COMMENT '类型(0 菜单 1 按钮)',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='系统菜单';
/*==================================================*/
/*Table:订单详情*/
/*==================================================*/
drop table if exists order_details;
create table order_details
(
    id              int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    app_id          int(11) DEFAULT null COMMENT 'APP',
    order_id        int(11) DEFAULT null COMMENT '订单',
    user_coupons_id int(11) DEFAULT null COMMENT '优惠券',
    handle          int(11) DEFAULT null COMMENT '操作 (0 购买)',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='订单详情';
/*==================================================*/
/*Table:角色管理*/
/*==================================================*/
drop table if exists role;
create table role
(
    id        int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    role_name varchar(50) DEFAULT null COMMENT '角色名称',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色管理';
/*==================================================*/
/*Table:角色菜单关系*/
/*==================================================*/
drop table if exists role_menu;
create table role_menu
(
    id      int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    role_id int(11) DEFAULT null COMMENT '角色',
    menu_id int(11) DEFAULT null COMMENT '菜单',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='角色菜单关系';
/*==================================================*/
/*Table:消息*/
/*==================================================*/
drop table if exists subpackage_message;
create table subpackage_message
(
    id          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    content     mediumtext DEFAULT null COMMENT '消息内容',
    create_time timestamp  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    type        int(11)    DEFAULT null COMMENT '消息类型 (0 咨询 1 金融 2 工程 3 企业 4 项目)',
    user_id     int(11)    DEFAULT null COMMENT '用户',
    title       char(10)   DEFAULT null COMMENT '标题',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='消息';
/*==================================================*/
/*Table:订单*/
/*==================================================*/
drop table if exists subpackage_order;
create table subpackage_order
(
    id          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    order_no    varchar(50) DEFAULT null COMMENT '订单号',
    create_time timestamp   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    status      int(11)     DEFAULT null COMMENT '状态',
    user_id     int(11)     DEFAULT null COMMENT '用户',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='订单';
/*==================================================*/
/*Table:用户表*/
/*==================================================*/
drop table if exists subpackage_user;
create table subpackage_user
(
    id            int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    login_name    varchar(50) DEFAULT null COMMENT '用户名',
    password      varchar(50) DEFAULT null COMMENT '密码',
    company_id    int(11)     DEFAULT null COMMENT '企业',
    company_email varchar(50) DEFAULT null COMMENT '企业邮箱',
    status        int(11)     DEFAULT 0    COMMENT '状态0 正常 1 禁用 2 删除',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户表';
/*==================================================*/
/*Table:用户优惠券*/
/*==================================================*/
drop table if exists user_coupons;
create table user_coupons
(
    id          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id     int(11)   DEFAULT null COMMENT '用户',
    coupons_id  int(11)   DEFAULT null COMMENT '优惠券',
    status      int(11)   DEFAULT null COMMENT '状态(0 未使用 1 已使用 2 已过期)',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '发放时间',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户优惠券';
/*==================================================*/
/*Table:用户角色关系*/
/*==================================================*/
drop table if exists user_role;
create table user_role
(
    id      int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id int(11) DEFAULT null COMMENT '用户',
    role_id int(11) DEFAULT null COMMENT '角色',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户角色关系';

/*==============================================================*/
/* Table: 解决方案                                              */
/*==============================================================*/
drop table if exists solution;
create table solution
(
    id               integer NOT NULL AUTO_INCREMENT,
    project_theme    varchar(100) DEFAULT NULL COMMENT '方案主题',
    project_detail   text         DEFAULT NULL COMMENT '方案详情',
    company_id       integer      DEFAULT NULL COMMENT '企业id',
    create_time      timestamp      DEFAULT NOW() COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='解决方案';

/*==============================================================*/
/* Table: 服务端邮箱                                              */
/*==============================================================*/
drop table if exists server_email;
create table server_email
(
    id             integer NOT NULL AUTO_INCREMENT,
    email_address  varchar(50) DEFAULT NULL COMMENT '服务端邮箱地址',
    email_password varchar(50) DEFAULT NULL COMMENT '服务端邮箱密码',
    smtp_address   varchar(50) DEFAULT NULL COMMENT 'SMYP服务端地址',
    content        text        DEFAULT NULL COMMENT '邮件内容',
    create_time    timestamp   DEFAULT NOW() COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='服务端邮箱';

/*==============================================================*/
/* Table: 公共附件表                          */
/*==============================================================*/
drop table if exists dhlk_basic_attachment;
create table dhlk_basic_attachment
(
    id          INTEGER NOT NULL AUTO_INCREMENT,
    name        varchar(100),
    save_name   varchar(50),
    path        varchar(100),
    suffix      varchar(10),
    create_time timestamp,
    data_id     varchar(50),
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1 COMMENT '公共附件表';


/*==============================================================*/
/* Table: 金币表                          */
/*==============================================================*/
drop table if exists gold;
create table gold
(
    id                 INTEGER          NOT NULL AUTO_INCREMENT,
    source_type        varchar(100)     DEFAULT null COMMENT '来源类型',
    gold_num           decimal(10, 3)   DEFAULT null COMMENT '金币数量',
    balance            decimal(10, 3)   DEFAULT null COMMENT '余额',
    user_id            integer          DEFAULT null COMMENT '企业id',
    create_time        timestamp        DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 1 COMMENT '金币表';


