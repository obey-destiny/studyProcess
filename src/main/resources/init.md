## 项目准备
1. 需求分析 模块 功能
2. 库表设计 数据库
3. 详细设计 流程图伪代码方式
4. 编码环节
- 搭建环境
- 正式进入编码环节
5. 测试
6. 部署上线

## 技术选型：
- 前端：vue + axios
- 后端：springboot + mybatis + tomcat + Redis



============================================================================================================================

## 需求分析
- 用户模块：
  - 用户登录
  - 用户注册
  - 验证码的实现
  - 欢迎xxx用户展示
  - 安全退出
  - 员工列表展示
- 员工模块
  - 员工添加
  - 员工删除
  - 员工修改
  - 员工列表加入redis缓存

## 库表设计
- 用户表
  - id
  - username
  - realname
  - password
  - sex
  - status
  - registerTime
- 员工表
  - id
  - name
  - path
  - salary
  - age

```sql
create table t_user(
    id int(6) primary key auto_increment,
    username varchar (60),
    realname varchar (60),
    password varchar (50),
    sex varchar (4),
    status varchar (4),
    registerTime timestamp 
);

create table t_emp(
    id int (6) primary key auto_increment,
    name varchar (50),
    path varchar (255),
    salary double (10, 2),
    age int (4)
);

```















