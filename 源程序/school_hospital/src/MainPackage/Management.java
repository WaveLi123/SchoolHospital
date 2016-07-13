//Management.java 包含对个各种产品进行管理的方法
package MainPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Management
{
	//data
	Connection con;					//声明特定数据库的连接实例
	public Statement stmt;			//声明向数据库发送SQL语句的statement对象
	public ResultSet rs;			//声明结果集，接受查询结果返回的对象
	public static int maxId;		//存放当前产品编号的最大值
	
	//method
	//不带参数的构造函数
	public Management() throws ClassNotFoundException, SQLException	
	{
		con=null;
		stmt=null;
		rs=null;
		maxId=0;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=校医院数据库系统","WaveLi_23","00100100");		
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);	
	}
	protected void finalize()throws Throwable		//析构函数
	{
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
	}
	//显示出所有物品的信息
	//**********************************************************
	public void display(String name)
	{
		try{
			rs = stmt.executeQuery("select * from "+name);	//Executes the given SQL statement
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}