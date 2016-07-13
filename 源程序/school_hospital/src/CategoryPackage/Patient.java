package CategoryPackage;
import java.sql.*;

import MainPackage.Management;

public class Patient 
{
	private static final long serialVersionUID = 20101025L;
//	--7.病人（病历号，姓名，性别，身份证号）
//	create table 病人(
//		病历号 int primary key,
//		病人姓名 varchar(50),
//		病人性别 char ,
//		病人身份证号 varchar(50),
//	--添加check约束
//		check (病人性别 in('男','女')),
//	)
	//medicence
	//病人（病历号，姓名，性别，身份证号）
	int 病历号;
	String 病人姓名;
	char 病人性别;
	String 病人身份证号;	
	
	//不带参数的构造函数
	public Patient(){
		this(0,"",' ',"");
	}
	//带参数的构造函数
	public Patient(int num,String name,char sex,String id){
		this.病历号 = num;
		this.病人姓名 = name;
		this.病人性别 = sex;
		this.病人身份证号 = id;
	}
	//获取新的产品信息
	public void addition(String[] value) throws Exception
	{
		int i=0;
		this.病历号 = Integer.valueOf(value[i++]);
		this.病人姓名 = value[i++];
		this.病人性别 = value[i++].charAt(0);
		this.病人身份证号 = value[i++];
	}
	//将产品信息添加到数据库中
	public void storeToDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="insert into "+
					"病人(病历号,病人姓名,病人性别,病人身份证号)"+
					"values("+this.病历号+",'"+this.病人姓名+"','"+this.病人性别+"','"+this.病人身份证号+"')";		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//更新数据库中的信息
	public void updateDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="update 病人 "+
					"set 病历号  = "+this.病历号+" ,"+
					" 病人姓名 = "+"'"+this.病人姓名+"'"+" ,"+
					" 病人性别 = "+"'"+this.病人性别+"'"+" ,"+
					" 病人身份证号 = "+"'"+this.病人身份证号+" '"+
					"where 病历号 = "+this.病历号;		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
}

