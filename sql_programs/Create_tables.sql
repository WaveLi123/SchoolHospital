create table Product(
	number_ID int primary key,
	name varchar,		--��Ʒ������
	category varchar,	--��Ʒ������
	price float,		--��Ʒ�ļ۸�
	description varchar	--��Ʒ������
);

create table Software(
	number_ID int primary key,
	name varchar,		--��Ʒ������
	category varchar,	--��Ʒ������
	price float,		--��Ʒ�ļ۸�
	description varchar,--��Ʒ������
	reqSpace int,		--required space
	os varchar,			--required operating system 
	softType varchar 	--software type(e.g. program, utilities)
);

create table Computer(
	number_ID int primary key,
	name varchar,		  --��Ʒ������
	category varchar,	  --��Ʒ������
	price float,		  --��Ʒ�ļ۸�
	description varchar,  --��Ʒ������
	processorName varchar,--������������
	processorSpeed float ,--���������ٶ�
	memory int 			  --�ڴ��С
);

create table Desktop(
	number_ID int primary key,
	name varchar,		  --��Ʒ������
	category varchar,	  --��Ʒ������
	price float,		  --��Ʒ�ļ۸�
	description varchar,  --��Ʒ������
	processorName varchar,--������������
	processorSpeed float ,--���������ٶ�
	memory int, 		  --�ڴ��С
	monitorSize int 	  --��ʾ���Ĵ�С	
);

create table Laptop(
	number_ID int primary key,
	name varchar,		  --��Ʒ������
	category varchar,	  --��Ʒ������
	price float,		  --��Ʒ�ļ۸�
	description varchar,  --��Ʒ������
	processorName varchar,--������������
	processorSpeed float ,--���������ٶ�
	memory int, 		  --�ڴ��С
	thickness float,	  --�ʼǱ��ĺ��
	weight float		  --�ʼǱ�������
);

create table Hometheater(
	number_ID int primary key,
	name varchar,		--��Ʒ������
	category varchar,	--��Ʒ������
	price float,		--��Ʒ�ļ۸�
	description varchar,--��Ʒ������
	speaker int,		--number of speakers(e.g. 2)������
	watts int,			--watts (e.g. 1000)����
	channel int 		--Ƶ��
);

create table Cartheater(
	number_ID int primary key,
	name varchar,		--��Ʒ������
	category varchar,	--��Ʒ������
	price float,		--��Ʒ�ļ۸�
	description varchar,--��Ʒ������
	speaker int,		--number of speakers(e.g. 2)������
	watts int,			--watts (e.g. 1000)����
	removable int
);