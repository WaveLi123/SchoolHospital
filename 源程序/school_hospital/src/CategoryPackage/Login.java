package CategoryPackage;
import java.sql.*;
import java.util.ArrayList;

import MainPackage.Management;

public class Login 
{
	private static final long serialVersionUID = 20101025L;
	//data
	//Login（name,secret）
	String name;
	String secret;	
	
	//不带参数的构造函数
	public Login()
	{
		this("","");
	}
	//带参数的构造函数
	public Login(String na,String sec)
	{
		this.name = na;
		this.secret = sec;
	}
	//获取用户名信息
	public String[] getkeyinfo() throws SQLException, ClassNotFoundException{
		Management operate = new Management();
		operate.display("Login");
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
	//用户密码信息
	public boolean Define(String name ,String secret) throws Exception, SQLException{
		Management operate = new Management();
		operate.rs=operate.stmt.executeQuery("select * from Login"
									+" where name='"+name+"'");
		String[] value = null;
		if(operate.rs.next())
		{
			operate.rs.beforeFirst();
			ResultSetMetaData rsmd=operate.rs.getMetaData();	//创建结果集对象

			int colCount=rsmd.getColumnCount();		//得到列数
			value=new String[colCount];
			while(operate.rs.next())		//得到各行的属性值
			{				
				for(int i=1;i<=colCount;i++)
					value[i-1]=operate.rs.getString(i);				
			}
			operate.rs.close();
		}
		//验证成功
		if(secret.equalsIgnoreCase(value[1])){
			return true;
		}
		//验证失败
		else{
			return false;
		}
		
	}
}