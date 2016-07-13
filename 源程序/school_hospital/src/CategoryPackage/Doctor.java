package CategoryPackage;
import java.sql.*;

import MainPackage.Management;

public class Doctor 
{
	private static final long serialVersionUID = 20101025L;
//	int reqSpace;		//required space
//	String os;			//required operating system
//	String softType;	//software type(e.g. program, utilities)
	//data
	//医生（编号，身份证号，职称，姓名，所属科室）
	int 医生编号;
	String 医生身份证号;
	String 医生职称;
	String 医生姓名;
	String 所属科室;
	
	//不带参数的构造函数
	public Doctor()
	{
		this(0,"","","","");
	}
	//带参数的构造函数
	public Doctor(int num,String id,String level,String name,String ks)
	{
		this.医生编号 = num;
		this.医生身份证号 = id;	
		this.医生职称 = level;
		this.医生姓名 = name;
		this.所属科室 = ks;
	}
//	public String getreqSpace()		//return the requaired space
//	{
//		return new java.text.DecimalFormat("0.0MB").format(reqSpace);
//	}
	//获取新的产品信息
	public void addition(String[] value) throws Exception
	{
		int i=0;
		this.医生编号 = Integer.valueOf(value[i++]);
		this.医生身份证号 = value[i++];
		this.医生职称 = value[i++];
		this.医生姓名 = value[i++];
		this.所属科室 = value[i++];
	}
	//将产品信息添加到数据库中
	public void storeToDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="insert into "+
					"医生(医生编号,医生身份证号,医生职称,医生姓名,所属科室)"+
					"values("+this.医生编号+",'"+this.医生身份证号+"','"+this.医生职称+"','"+this.医生姓名+"','"+
					this.所属科室+"')";				
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//更新数据库中的信息
	public void updateDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="update 医生 "+
					"set 医生编号 = "+this.医生编号+" ,"+
					" 医生身份证号 = "+"'"+this.医生身份证号+"'"+" ,"+
					" 医生职称 = "+"'"+this.医生职称+"'"+" ,"+
					" 医生姓名 = "+"'"+this.医生姓名+"'"+" ,"+
					" 所属科室 = "+"'"+this.所属科室+" '"+
					"where 医生编号 = "+this.医生编号;				
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
}