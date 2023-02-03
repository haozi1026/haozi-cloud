create table Resouces
(
    resources_id  bigint unsigned not null
        primary key,
    resource_name varchar(255)    null comment '资源名',
    parent_id     bigint unsigned null comment '父级id',
    type          varchar(255)    null comment '类型 类型0:按钮 1：菜单',
    is_del        varchar(255)    null comment '删除标识',
    url           varchar(255)    null comment '请求地址',
    create_time   datetime        null comment '创建时间',
    update_time   datetime        null comment '更新时间',
    resource_flag varchar(100)    null comment '资源标识'
);

create table Role
(
    role_id     bigint unsigned not null
        primary key,
    role_name   varchar(255)    null comment '角色名',
    role_desc   varchar(255)    null comment '角色描述',
    create_time datetime        null,
    update_time datetime        null
);

create table role_resources
(
    role_id     bigint   not null,
    resource_id bigint   not null,
    create_time datetime null,
    update_time datetime null,
    primary key (role_id, resource_id)
);

create table token_config
(
    id                int          not null
        primary key,
    access_token_key  varchar(255) null,
    refresh_token_key varchar(255) null,
    access_token_exp  varchar(255) null,
    create_time       datetime     null,
    update_time       datetime     null
);

create table upms_user
(
    id          bigint unsigned not null
        primary key,
    user_name   varchar(30)     null comment '用户名',
    password    int             null comment '密码',
    create_time datetime        null,
    update_time datetime        null
)
    comment '用户';

create table user_role
(
    username varchar(255) null,
    role_id  bigint       null
);

create table users
(
    username     varchar(100) not null comment '用户名'
        primary key,
    pwd          varchar(255) null,
    enabled      tinyint(255) null comment '是否可用',
    email        varchar(255) null,
    phone_number int          null comment '手机号',
    create_time  datetime     null,
    update_time  datetime     null
);

