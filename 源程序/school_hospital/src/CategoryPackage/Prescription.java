package CategoryPackage;
import java.sql.*;
import java.util.ArrayList;

import MainPackage.Management;

public class Prescription 
{
	private static final long serialVersionUID = 20101025L;
//	--10.处方信息（处方单号，药品编号，药品数量，药品单价）
//	create table 处方信息(
//		处方单号 int primary key,
//		处方药品编号 int,
//		药品数量 int,
//		药品单价 money,
//	--外码定义
//		foreign key (药品编号) references 药品(药品编号),
//	)
	//medicence
	//处方信息（处方单号，药品编号，药品数量，药品单价）
	int 处方单号;
	int 处方药品编号;
	int 药品数量;
	String 药品单价;	
	
	//不带参数的构造函数
	public Prescription(){
		this.处方单号 = 0;
		this.处方药品编号 = 0;
		this.药品数量 = 0;
		this.药品单价 = null;
	}
	//带参数的构造函数
	public Prescription(int num_1,int num_2,int length,String price){
		this.处方单号 = num_1;
		this.处方药品编号 = num_2;
		this.药品数量 = length;
		this.药品单价 = price;
	}
	//获取新的产品信息
	public void addition(String[] value) throws Exception
	{
		int i=0;
		this.处方单号 = Integer.valueOf(value[i++]);
		this.处方药品编号 = Integer.valueOf(value[i++]);
		this.药品数量 = Integer.valueOf(value[i++]);
		this.药品单价 = value[i++];
	}
	//将产品信息添加到数据库中
	public void storeToDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="insert into "+
					"处方信息(处方单号,处方药品编号,药品数量,药品单价)"+
					"values("+this.处方单号+","+this.处方药品编号+","+this.药品数量+",'"+this.药品单价+"')";		
		System.out.println(sqlS);
		Management operate=new Management();
		operate.stmt.executeUpdate(sqlS);
	}
	//更新数据库中的信息
	public void updateDB() throws SQLException, ClassNotFoundException
	{
		String sqlS="update 处方信息 "+					
					"set 处方单号= "+this.处方单号+" ,"+
					" 处方药品编号= "+this.处方药品编号+" ,"+
					" 药品数量= "+this.药品数量+" ,"+
					" 药品单价 = "+"'"+this.药品单价+" '"+
					"where 处方单号 = "+this.处方单号;		
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

