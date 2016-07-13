package CategoryPackage;
import java.sql.*;
import java.util.ArrayList;

import MainPackage.Management;

public class Drugstore 
{
//	--1.药房（编号，名称）
//	create table 药房(
//		药房编号 int primary key,--主码
//		药房名称 varchar(50)
//	)
	private static final long serialVersionUID = 20101025L;	
	//data
	//药房（编号，名称）
	int 药房编号;
	String 药房名称;

	
	//不带参数的构造函数
	public Drugstore()
	{
		this(0,"");
	}
	//带参数的构造函数
	public Drugstore(int num,String name)
	{
		this.药房编号 = num;
		this.药房名称 = name;		
	}
	//获取新的产品信息
	public void addition(String[] value) throws Exception
	{
		int i=0;
		this.药房编号 = Integer.valueOf(value[i++]);
		this.药房名称 = value[i++];
	}
	//将产品信息添加到数据库中
	public void storeToDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="insert into "+
					"药房(药房编号,药房名称)"+
					"values("+this.药房编号+",'"+this.药房名称+"'"+")";		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//更新数据库中的信息
	public void updateDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="update 药房 "+
					"set 药房编号 = "+this.药房编号+
					" 药房名称 = "+"'"+this.药房名称+" '"+
					"where 药房编号 = "+this.药房编号;		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//获取产品信息
	public String[] getkeyinfo_drug() throws SQLException, ClassNotFoundException{
		Management operate = new Management();
		operate.display("药品");
		ResultSetMetaData rsmd=operate.rs.getMetaData();	//创建结果集对象
		int colCount=rsmd.getColumnCount();		//得到列数
		String[] name=new String[colCount];
		operate.rs.beforeFirst();
		
		String[] value=new String[colCount];		

		ArrayList<String> ar = new ArrayList<String>();  		
		while(operate.rs.next())		//得到各行的属性值
		{			
			for(int i=1;i<=colCount;i++){
				value[i-1]=operate.rs.getString(i);			
			}							
			ar.add(value[0]);
		}
		operate.rs.close();
		String [] t_value = new String[ar.size()];
		int j = 0;
		for(int p = 0; p< ar.size(); p ++){
			t_value[p] = ar.get(p);
			System.out.println(t_value[p]);
		}
		return t_value;
	}
}