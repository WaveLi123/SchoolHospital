package CategoryPackage;
import java.sql.*;
import java.util.ArrayList;

import MainPackage.Management;

public class Treatment 
{
//	--11.就诊信息（医生编号，病历号，就诊时间，处方单号）
//	create table 就诊信息
//	(
//		就诊医生编号 int,
//		就诊病历号 int,
//		就诊时间 datetime,
//		就诊处方单号 int,
//	--主码定义
//		primary key(医生编号,病历号,就诊时间),
//	--外码定义
//		foreign key(医生编号) references 医生(医生编号),
//		foreign key(病历号) references 病人(病历号),
//		foreign key(处方单号) references 处方信息(处方单号),
//	)


	private static final long serialVersionUID = 20101025L;	
	//data
	//就诊信息（医生编号，病历号，就诊时间，处方单号）
	int 就诊医生编号;
	int 就诊病历号;
	String 就诊时间;
	int 就诊处方单号;
	
	//不带参数的构造函数
	public Treatment()
	{
		this.就诊医生编号 = 0;
		this.就诊病历号 = 0;	
		this.就诊时间 = null;
		this.就诊处方单号 = 0;
	}
	//带参数的构造函数
	public Treatment(int num_1,int num_2,String time,int num_3)
	{
		this.就诊医生编号 = num_1;
		this.就诊病历号 = num_2;	
		this.就诊时间 = time;
		this.就诊处方单号 = num_3;
	}
//	public String getreqSpace()		//return the requaired space
//	{
//		return new java.text.DecimalFormat("0.0MB").format(reqSpace);
//	}
	//获取新的产品信息
	public void addition(String[] value) throws Exception
	{
		int i=0;
		this.就诊医生编号 = Integer.valueOf(value[i++]);
		this.就诊病历号 = Integer.valueOf(value[i++]);
		this.就诊时间 = value[i++];
		this.就诊处方单号 = Integer.valueOf(value[i++]);
	}
	//将产品信息添加到数据库中
	public void storeToDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="insert into "+
					"就诊信息(就诊医生编号,就诊病历号,就诊时间,就诊处方单号)"+
					"values("+this.就诊医生编号+","+this.就诊病历号+",'"+this.就诊时间+"',"+this.就诊处方单号+")";		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//更新数据库中的信息
	public void updateDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="update 就诊信息 "+
					"set 就诊医生编号 = "+this.就诊医生编号+" ,"+
					" 就诊病历号 = "+this.就诊病历号+" ,"+
					" 就诊时间 = "+"'"+this.就诊时间+"'"+" ,"+
					" 就诊处方单号 = "+this.就诊处方单号+
					"where 就诊医生编号 = "+this.就诊医生编号;		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	
	//获取产品信息
	public String[] getkeyinfo_doctor() throws SQLException, ClassNotFoundException{
		Management operate = new Management();
		operate.display("医生");
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
	public String[] getkeyinfo_prescription() throws SQLException, ClassNotFoundException{
		Management operate = new Management();
		operate.display("处方信息");
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
