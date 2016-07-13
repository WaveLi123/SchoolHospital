package SwingPackage;
import MainPackage.Management;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
class DisplayListener implements ActionListener
{
	JPanel jplDisplay;
	JInternalFrame jifShow;//显示结果的面板
	JPanel jplButton;//选择产品类型的面板
	
	public DisplayListener(JPanel jpl)
	{		
//		//添加显示面板		
////		jplDisplay = new JPanel();
//		jplDisplay.setLayout(new BorderLayout());
//		jplDisplay.setBackground(new Color(230,230,230));
//		window.add(jplDisplay,BorderLayout.CENTER);	
		this.jplDisplay = jpl;
	}
	public void actionPerformed(ActionEvent e)
	{
		jplDisplay.removeAll();		
		//用于选择产品类型的面板
		jplButton=new JPanel();
		jplButton.setBackground(new Color(250,250,240));
		String[] productName={"","医生","病人","病房","科室","药房","药品","住院信息","处方信息","就诊信息"};
		
		final JComboBox productBox=new JComboBox();		//设置下拉菜单
		productBox.setModel(new DefaultComboBoxModel(productName));
		
		JButton jbSure=new JButton("确定");			//设置按钮
		JButton jbQuit=new JButton("退出");
		JLabel advise=new JLabel("请选择");
		advise.setFont(new Font("华文行楷", 1, 25));
		advise.setForeground(Color.BLACK);
		jplButton.setLayout(new GridLayout(12,1));
		for(int i=0;i<12;i++)
		{
			switch(i)
			{
			case 0:jplButton.add(advise,BorderLayout.CENTER);break;
			case 1:jplButton.add(productBox,BorderLayout.CENTER);break;
			case 10:jplButton.add(jbSure,BorderLayout.CENTER);break;
			case 11:jplButton.add(jbQuit,BorderLayout.CENTER);break;
			default:jplButton.add(new JLabel());
			}
		}
		jplButton.setVisible(true);
		
		//用于显示结果的面板
		jifShow=new JInternalFrame("显示面板",true,true,true);
		final JScrollPane jspDisplay=new JScrollPane();	//滚动面板
		jspDisplay.setBackground(Color.WHITE);
		jspDisplay.setVisible(true);
		
		final DefaultTableModel JTableModel = new DefaultTableModel(10,20);	//定义表格模板
		JTable myFirstTable=new JTable(JTableModel);		//定义表格
		myFirstTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		myFirstTable.setFillsViewportHeight(true);
		myFirstTable.setRowHeight(18);		
		jspDisplay.add(myFirstTable);		//添加到显示面板上
		jspDisplay.setViewportView(myFirstTable);
		
		jifShow.add(jplButton,BorderLayout.EAST);
		jifShow.add(jspDisplay,BorderLayout.CENTER);
		jifShow.setVisible(true);
		jplDisplay.add(jifShow);

		JOptionPane.showMessageDialog(jplDisplay, "请在右侧选择相应类型进行查看","提示", 1);
		//添加监听器
		jbQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jifShow.dispose();				
			}
		});			
		jbSure.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){								
				Management operate = null;
				JTable myJTable;
				String productName=productBox.getSelectedItem().toString();
				
				try{
					operate = new Management();
					operate.display(productName);
					ResultSetMetaData rsmd=operate.rs.getMetaData();	//创建结果集对象
					int colCount=rsmd.getColumnCount();		//得到列数
					String[] name=new String[colCount];
					
					//表格模板的行数和列数清零
					JTableModel.setRowCount(0);	
					JTableModel.setColumnCount(0);
					for(int i=1;i<=colCount;i++)	//得到列名
					{
						name[i-1]=rsmd.getColumnName(i);
						JTableModel.addColumn(name[i-1]);
					}
					operate.rs.beforeFirst();					
					while(operate.rs.next())		//得到各行的属性值
					{
						String[] value=new String[colCount];
						for(int i=1;i<=colCount;i++)
							value[i-1]=operate.rs.getString(i);
						JTableModel.addRow(value);
					}
					operate.rs.close();
					JTableModel.setRowCount(JTableModel.getRowCount()+10);
					JTableModel.setColumnCount(JTableModel.getColumnCount()+10);
					myJTable=new JTable(JTableModel);	//用表格模板初始化表格myJTable
					myJTable.setEnabled(true);		//设置表格能被编辑
					myJTable.setRowHeight(18);		//设置行高
					myJTable.setFillsViewportHeight(true);
					myJTable.setFont(new Font("宋体",0,15));
					myJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//关闭自动调节列宽
					myJTable.setCellSelectionEnabled(true);			//允许选取单元格
					myJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	//允许多选
					
					jspDisplay.add(myJTable);		//添加到显示面板上
					jspDisplay.setViewportView(myJTable);
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(jspDisplay, "数据库连接异常","错误",0);
					e1.printStackTrace();
				}catch(SQLException wrong){
					JOptionPane.showMessageDialog(jspDisplay, "数据库访问异常","错误",0);
				}
			}
		});
	}
}