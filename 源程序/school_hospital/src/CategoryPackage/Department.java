package CategoryPackage;
import java.sql.*;

import MainPackage.Management;

public class Department 
{
//	--4.科室（科号，科名，科地址，科电话）
//	create table 科室(
//		科号 int primary key,--主码
//		科名 varchar(50),
//		科地址 varchar(50),
//		科电话 varchar(50),
//	)
	private static final long serialVersionUID = 20101025L;	
	//data
	//科室（科号，科名，科地址，科电话）
	int 科号;
	String 科名;
	String 科地址;
	String 科电话;
	
	//不带参数的构造函数
	public Department()
	{
		this.科号 = 0;
		this.科名 = null;	
		this.科地址 = null;
		this.科电话 = null;
	}
	//带参数的构造函数
	public Department(int num,String name,String id,String tel)
	{
		this.科号 = num;
		this.科名 = name;	
		this.科地址 = id;
		this.科电话 = tel;
	}
//	public String getreqSpace()		//return the requaired space
//	{
//		return new java.text.DecimalFormat("0.0MB").format(reqSpace);
//	}
	//获取新的产品信息
	public void addition(String[] value) throws Exception
	{
		int i=0;
		this.科号 = Integer.valueOf(value[i++]);
		this.科名 = value[i++];
		this.科地址 = value[i++];
		this.科电话 = value[i++];
	}
	//将产品信息添加到数据库中
	public void storeToDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="insert into "+
					"科室(科号,科名,科地址,科电话)"+
					"values("+this.科号+",'"+this.科名+"','"+this.科地址+"','"+this.科电话+"')";				
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//更新数据库中的信息
	public void updateDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="update 科室 "+
					"set 科号 = "+this.科号+" ,"+
					" 科名 = "+"'"+this.科名+"'"+" ,"+
					" 科地址 = "+"'"+this.科地址+"'"+" ,"+
					" 科电话 = "+"'"+this.科电话+" '"+
					"where 科号 = "+this.科号;				
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
}