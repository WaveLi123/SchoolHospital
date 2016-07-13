create table Product(
	number_ID int primary key,
	name varchar,		--产品的名字
	category varchar,	--产品的种类
	price float,		--产品的价格
	description varchar	--产品的描述
);

create table Software(
	number_ID int primary key,
	name varchar,		--产品的名字
	category varchar,	--产品的种类
	price float,		--产品的价格
	description varchar,--产品的描述
	reqSpace int,		--required space
	os varchar,			--required operating system 
	softType varchar 	--software type(e.g. program, utilities)
);

create table Computer(
	number_ID int primary key,
	name varchar,		  --产品的名字
	category varchar,	  --产品的种类
	price float,		  --产品的价格
	description varchar,  --产品的描述
	processorName varchar,--处理器的名字
	processorSpeed float ,--处理器的速度
	memory int 			  --内存大小
);

create table Desktop(
	number_ID int primary key,
	name varchar,		  --产品的名字
	category varchar,	  --产品的种类
	price float,		  --产品的价格
	description varchar,  --产品的描述
	processorName varchar,--处理器的名字
	processorSpeed float ,--处理器的速度
	memory int, 		  --内存大小
	monitorSize int 	  --显示器的大小	
);

create table Laptop(
	number_ID int primary key,
	name varchar,		  --产品的名字
	category varchar,	  --产品的种类
	price float,		  --产品的价格
	description varchar,  --产品的描述
	processorName varchar,--处理器的名字
	processorSpeed float ,--处理器的速度
	memory int, 		  --内存大小
	thickness float,	  --笔记本的厚度
	weight float		  --笔记本的重量
);

create table Hometheater(
	number_ID int primary key,
	name varchar,		--产品的名字
	category varchar,	--产品的种类
	price float,		--产品的价格
	description varchar,--产品的描述
	speaker int,		--number of speakers(e.g. 2)扬声器
	watts int,			--watts (e.g. 1000)功率
	channel int 		--频道
);

create table Cartheater(
	number_ID int primary key,
	name varchar,		--产品的名字
	category varchar,	--产品的种类
	price float,		--产品的价格
	description varchar,--产品的描述
	speaker int,		--number of speakers(e.g. 2)扬声器
	watts int,			--watts (e.g. 1000)功率
	removable int
);