/*--------------------------------------------
Task：校医院数据库系统表格创建
Author:李伟康_1240069
Time:2014-05-14
----------------------------------------------*/
--1.药房（编号，名称）
create table 药房(
	药房编号 int primary key,--主码
	药房名称 varchar(50)
)

--2.药品（编号，名称，零售价，生产厂家）
create table 药品(
	药品编号 int primary key,--主码
	药品名称 varchar(50),
	药品零售价 money,
	生产厂家 varchar(50)
)

--3.存在_1（药房编号，药品编号，存量）
create table yf_yp(
	yf_num int,
	yp_num int,
	store int,
--主码定义
	primary key(yf_num,yp_num),
--外码定义
	foreign key(yf_num) references yf(yf_num),
	foreign key(yp_num) references yp(yp_num),
)

--4.科室（科号，科名，科地址，科电话）
create table 科室(
	科号 int primary key,--主码
	科名 varchar(50),
	科地址 varchar(50),
	科电话 varchar(50),
)

--5.病房（病房号，级别，床位数）
create table 病房(
	病房号 int primary key,--病房号
	病房级别 varchar(50) default '普通病房',
	病房床位数 int default 4,
--添加check约束
	check (病房级别 in('普通病房','重症监护病房','隔离病房')),
	check (病房床位数 =1 or 病房床位数=2 or 病房床位数=4)
)

--6.医生（编号，身份证号，职称，姓名，所属科室）
create table 医生(
	医生编号 int primary key,
	医生身份证号 varchar(50),
	医生职称 varchar(50) default '医师',
	医生姓名 varchar(50),
	所属科室 int foreign key references ks(ks_num),
--添加check约束
	check(医生职称='医师' or 医生职称='专家'),	
)

--7.病人（病历号，姓名，性别，身份证号）
create table 病人(
	病历号 int primary key,
	病人姓名 varchar(50),
	病人性别 varchar(50),
	病人身份证号 varchar(50),
--添加check约束
	check (病人性别 in('男','女')),
)

--8.存在_2（科室号，病房号）
create table ks_bf(
	ks_num int,
	bf_num int,
--主码定义
	primary key(ks_num,bf_num),
--外码定义
	foreign key(ks_num) references ks(ks_num),
	foreign key(bf_num) references bf(bf_num),
)

--9.住院（病历号，病房号，住院时间，出院时间）
create table 住院信息
(
	病人病历号 int,
	病人病房号 int,
	住院时间 datetime,
	出院时间 datetime,
--主码定义
	primary key(病人病历号,住院时间),
--外码定义
	foreign key(病人病历号) references 病人(病历号),
	foreign key(病人病房号) references 病房(病房号),
)

--10.处方信息（处方单号，药品编号，药品数量，药品单价）
create table 处方信息(
	处方单号 int primary key,
	处方药品编号 int,
	药品数量 int,
	药品单价 money,
--外码定义
	foreign key (处方药品编号) references 药品(药品编号),
)

--11.就诊信息（医生编号，病历号，就诊时间，处方单号）
create table 就诊信息
(
	就诊医生编号 int,
	就诊病历号 int,
	就诊时间 datetime,
	就诊处方单号 int,
--主码定义
	primary key(医生编号,病历号,就诊时间),
--外码定义
	foreign key(就诊医生编号) references 医生(医生编号),
	foreign key(就诊病历号) references 病人(病历号),
	foreign key(就诊处方单号) references 处方信息(处方单号),
)

