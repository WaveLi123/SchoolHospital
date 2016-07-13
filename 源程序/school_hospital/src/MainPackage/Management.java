//Management.java �����Ը����ֲ�Ʒ���й���ķ���
package MainPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Management
{
	//data
	Connection con;					//�����ض����ݿ������ʵ��
	public Statement stmt;			//���������ݿⷢ��SQL����statement����
	public ResultSet rs;			//��������������ܲ�ѯ������صĶ���
	public static int maxId;		//��ŵ�ǰ��Ʒ��ŵ����ֵ
	
	//method
	//���������Ĺ��캯��
	public Management() throws ClassNotFoundException, SQLException	
	{
		con=null;
		stmt=null;
		rs=null;
		maxId=0;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=УҽԺ���ݿ�ϵͳ","WaveLi_23","00100100");		
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);	
	}
	protected void finalize()throws Throwable		//��������
	{
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(con!=null)
			con.close();
	}
	//��ʾ��������Ʒ����Ϣ
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