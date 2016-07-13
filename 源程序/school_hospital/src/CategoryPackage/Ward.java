package CategoryPackage;
import java.sql.*;

import MainPackage.Management;

public class Ward 
{
//	--5.病房（病房号，级别，床位数）
//	create table 病房(
//		病房号 int primary key,--病房号
//		病房级别 varchar(50) default '普通病房',
//		病房床位数 int default 4,
//	--添加check约束
//		check (病房级别 in('普通病房','重症监护病房','隔离病房')),
//		check (病房床位数 =1 or bf_bednum=2 or bf_bednum=4)
//	)
	private static final long serialVersionUID = 20101025L;	
	//data
	//病房（病房号，级别，床位数）
	int 病房号;
	String 病房级别;
	int 病房床位数;
	
	//不带参数的构造函数
	public Ward()
	{
		this.病房号 = 0;
		this.病房级别 = null;	
		this.病房床位数 = 4;
	}
	//带参数的构造函数
	public Ward(int num,String level,int bed)
	{
		this.病房号 = num;
		this.病房级别 = level;	
		this.病房床位数 = bed;
	}
//	public String getreqSpace()		//return the requaired space
//	{
//		return new java.text.DecimalFormat("0.0MB").format(reqSpace);
//	}
	//获取新的产品信息
	public void addition(String[] value) throws Exception
	{
		int i=0;
		this.病房号 = Integer.valueOf(value[i++]);
		this.病房级别 = value[i++];
		this.病房床位数 = Integer.valueOf(value[i++]);
	}
	//将产品信息添加到数据库中
	public void storeToDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="insert into "+
					"病房(病房号,病房级别,病房床位数)"+
					"values("+this.病房号+",'"+this.病房级别+"',"+this.病房床位数+")";		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//更新数据库中的信息
	public void updateDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="update 病房 "+
					"set 病房号 = "+this.病房号+" ,"+
					" 病房级别 = "+"'"+this.病房级别+"'"+" ,"+
					" 病房床位数 = "+this.病房床位数+" ,"+					
					"where 病房号 = "+this.病房号;		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
}