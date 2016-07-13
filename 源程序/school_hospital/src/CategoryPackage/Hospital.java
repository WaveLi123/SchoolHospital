package CategoryPackage;
import java.sql.*;
import java.util.ArrayList;

import MainPackage.Management;

public class Hospital 
{
//	--9.住院信息（病历号，病房号，住院时间，出院时间）
//	create table 住院信息
//	(
//		病人病历号 int,
//		病人病房号 int,
//		住院时间 datetime,
//		出院时间 datetime,
//	--主码定义
//		primary key(病历号,住院时间),
//	--外码定义
//		foreign key(病历号) references 病人(病历号),
//		foreign key(病房号) references 病房(病房号),
//	)

	private static final long serialVersionUID = 20101025L;	
	//data
	//住院信息（病历号，病房号，住院时间，出院时间）
	int 病人病历号;
	int 病人病房号;
	String 住院时间;
	String 出院时间;	
	
	//不带参数的构造函数
	public Hospital()
	{
		this.病人病历号 = 0;
		this.病人病房号 = 0;	
		this.住院时间 = null;
		this.出院时间 = null;
	}
	//带参数的构造函数
	public Hospital(int num_1,int num_2,String time_1,String time_2)
	{
		this.病人病历号 = num_1;
		this.病人病房号 = num_2;	
		this.住院时间 = time_1;
		this.出院时间 = time_2;		
	}
//	public String getreqSpace()		//return the requaired space
//	{
//		return new java.text.DecimalFormat("0.0MB").format(reqSpace);
//	}
	//获取新的产品信息
	public void addition(String[] value) throws Exception
	{
		int i=0;
		this.病人病历号 = Integer.valueOf(value[i++]);
		this.病人病房号 = Integer.valueOf(value[i++]);
		this.住院时间 = value[i++];
		this.出院时间 = value[i++];		
	}
	//将产品信息添加到数据库中
	public void storeToDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="insert into "+
					"住院信息(病人病历号,病人病房号,住院时间,出院时间)"+
					"values("+this.病人病历号+","+this.病人病房号+",'"+this.住院时间+"','"+this.出院时间+"'"+")";		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//更新数据库中的信息
	public void updateDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="update 住院信息 "+
					"set 病人病历号 = "+this.病人病历号+" ,"+
					" 病人病房号 = "+this.病人病房号+" ,"+
					" 住院时间= "+"'"+this.住院时间+"'"+" ,"+
					" 出院时间 = "+"'"+this.出院时间+" '"+
					"where 病人病历号 = "+this.病人病历号;		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//获取数据库中的信息
	public String[] getkeyinfo_patient() throws SQLException, ClassNotFoundException{
		Management operate = new Management();
		operate.display("病人");
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
	public String[] getkeyinfo_ward() throws SQLException, ClassNotFoundException{
		Management operate = new Management();
		operate.display("病房");
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