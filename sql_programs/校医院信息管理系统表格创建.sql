/*--------------------------------------------
Task��УҽԺ���ݿ�ϵͳ��񴴽�
Author:��ΰ��_1240069
Time:2014-05-14
----------------------------------------------*/
--1.ҩ������ţ����ƣ�
create table ҩ��(
	ҩ����� int primary key,--����
	ҩ������ varchar(50)
)

--2.ҩƷ����ţ����ƣ����ۼۣ��������ң�
create table ҩƷ(
	ҩƷ��� int primary key,--����
	ҩƷ���� varchar(50),
	ҩƷ���ۼ� money,
	�������� varchar(50)
)

--3.����_1��ҩ����ţ�ҩƷ��ţ�������
create table yf_yp(
	yf_num int,
	yp_num int,
	store int,
--���붨��
	primary key(yf_num,yp_num),
--���붨��
	foreign key(yf_num) references yf(yf_num),
	foreign key(yp_num) references yp(yp_num),
)

--4.���ң��ƺţ��������Ƶ�ַ���Ƶ绰��
create table ����(
	�ƺ� int primary key,--����
	���� varchar(50),
	�Ƶ�ַ varchar(50),
	�Ƶ绰 varchar(50),
)

--5.�����������ţ����𣬴�λ����
create table ����(
	������ int primary key,--������
	�������� varchar(50) default '��ͨ����',
	������λ�� int default 4,
--���checkԼ��
	check (�������� in('��ͨ����','��֢�໤����','���벡��')),
	check (������λ�� =1 or ������λ��=2 or ������λ��=4)
)

--6.ҽ������ţ����֤�ţ�ְ�ƣ��������������ң�
create table ҽ��(
	ҽ����� int primary key,
	ҽ�����֤�� varchar(50),
	ҽ��ְ�� varchar(50) default 'ҽʦ',
	ҽ������ varchar(50),
	�������� int foreign key references ks(ks_num),
--���checkԼ��
	check(ҽ��ְ��='ҽʦ' or ҽ��ְ��='ר��'),	
)

--7.���ˣ������ţ��������Ա����֤�ţ�
create table ����(
	������ int primary key,
	�������� varchar(50),
	�����Ա� varchar(50),
	�������֤�� varchar(50),
--���checkԼ��
	check (�����Ա� in('��','Ů')),
)

--8.����_2�����Һţ������ţ�
create table ks_bf(
	ks_num int,
	bf_num int,
--���붨��
	primary key(ks_num,bf_num),
--���붨��
	foreign key(ks_num) references ks(ks_num),
	foreign key(bf_num) references bf(bf_num),
)

--9.סԺ�������ţ������ţ�סԺʱ�䣬��Ժʱ�䣩
create table סԺ��Ϣ
(
	���˲����� int,
	���˲����� int,
	סԺʱ�� datetime,
	��Ժʱ�� datetime,
--���붨��
	primary key(���˲�����,סԺʱ��),
--���붨��
	foreign key(���˲�����) references ����(������),
	foreign key(���˲�����) references ����(������),
)

--10.������Ϣ���������ţ�ҩƷ��ţ�ҩƷ������ҩƷ���ۣ�
create table ������Ϣ(
	�������� int primary key,
	����ҩƷ��� int,
	ҩƷ���� int,
	ҩƷ���� money,
--���붨��
	foreign key (����ҩƷ���) references ҩƷ(ҩƷ���),
)

--11.������Ϣ��ҽ����ţ������ţ�����ʱ�䣬�������ţ�
create table ������Ϣ
(
	����ҽ����� int,
	���ﲡ���� int,
	����ʱ�� datetime,
	���ﴦ������ int,
--���붨��
	primary key(ҽ�����,������,����ʱ��),
--���붨��
	foreign key(����ҽ�����) references ҽ��(ҽ�����),
	foreign key(���ﲡ����) references ����(������),
	foreign key(���ﴦ������) references ������Ϣ(��������),
)

