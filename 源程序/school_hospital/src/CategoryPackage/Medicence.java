package CategoryPackage;
import java.sql.*;

import MainPackage.Management;

public class Medicence 
{
	private static final long serialVersionUID = 20101025L;
//	--2.药品（编号，名称，零售价，生产厂家）
//	create table 药品(
//		药品编号 int primary key,--主码
//		药品名称 varchar(50),
//		药品零售价 money,
//		生产厂家 varchar(50)
//	)
	//medicence
	//药品（编号，名称，零售价，生产厂家）
	int 药品编号;
	String 药品名称;
	float 药品零售价;
	String 生产厂家;	
	
	//不带参数的构造函数
	public Medicence(){
		this.药品编号 = 0;
		this.药品名称 = null;
		this.药品零售价 = 0;
		this.生产厂家 = null;		
	}
	//带参数的构造函数
	public Medicence(int num,String name,float price,String producer){
		this.药品编号 = num;
		this.药品名称 = name;
		this.药品零售价 = price;
		this.生产厂家 = producer;
	}
	//获取新的产品信息
	public void addition(String[] value) throws Exception
	{
		int i=0;
		this.药品编号 = Integer.parseInt(value[i++]);		
		this.药品名称 = value[i++];
		this.药品零售价 = Float.parseFloat(value[i++]);
		this.生产厂家 = value[i++];		
	}
	//将产品信息添加到数据库中
	public void storeToDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="insert into "+
					"药品(药品编号,药品名称,药品零售价,生产厂家)"+
					"values("+this.药品编号+",'"+this.药品名称+"','"+this.药品零售价+"','"+this.生产厂家+"')";		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//更新数据库中的信息
	public void updateDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="update 药品 "+
					"set 药品编号 = "+this.药品编号+" ,"+
					" 药品名称 = "+"'"+this.药品名称+"'"+" ,"+
					" 药品零售价 = "+"'"+this.药品零售价+"'"+" ,"+
					" 生产厂家 = "+"'"+this.生产厂家+" '"+
					"where 药品编号 = "+this.药品编号;		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
}

