package SwingPackage;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import MainPackage.Management;
class DeleteListener implements ActionListener
{
	JPanel jplDisplay;
	JInternalFrame jifShow;//��ʾ��崰��
	JPanel displayUp,displayMid,displayDown;
	Management operate=null;
//	public DeleteListener(MainWindow window)
//	{
////		image.removeAll();
//		//�����ʾ���		
//		jplDisplay=new JPanel();
//		jplDisplay.setLayout(new BorderLayout());
//		jplDisplay.setBackground(new Color(230,230,230));
//		window.add(jplDisplay,BorderLayout.CENTER);				
//	}
	public DeleteListener(JPanel jpl)
	{
		this.jplDisplay = jpl;
	}
	public void actionPerformed(ActionEvent e)
	{
		jplDisplay.removeAll();
		jifShow=new JInternalFrame("ɾ����Ϣ���",true,true,true);
		//�������
		displayUp=new JPanel();
		JLabel jTitle=new JLabel("ɾ����Ϣ");
		jTitle.setFont(new Font("�����п�", 2, 28));
		jTitle.setForeground(Color.BLACK);
		displayUp.add(jTitle);
		displayUp.setBackground(Color.GREEN);
		//�м����
		displayMid=new JPanel(new BorderLayout());		//�м������ϲ�
		JLabel[] jName=new JLabel[2];		
		String[] sType={"","ҽ��","����","����","����","ҩ��","ҩƷ","סԺ��Ϣ","������Ϣ","������Ϣ"};
		jName[0]=new JLabel("����ѡ��");
		jName[1]=new JLabel("���");
		final JComboBox productBox=new JComboBox(sType);//
		final JTextField pName=new JTextField(12);		//�ı������
		JButton jbDelete=new JButton("ɾ��");			//���ð�ť
		jbDelete.setFont(new Font("�����п�",1,18));
		JPanel panelMidUp=new JPanel(new FlowLayout());
		panelMidUp.add(jName[0]);
		panelMidUp.add(productBox);
		panelMidUp.add(jName[1]);
		panelMidUp.add(pName);
		panelMidUp.add(jbDelete);
		displayMid.add(panelMidUp, BorderLayout.NORTH);
		
		final DefaultTableModel JTableModel = new DefaultTableModel(10,20);	//������ģ��
		final JTable myJTable=new JTable(JTableModel);	//�ñ��ģ���ʼ�����myJTable
//		myJTable.setEnabled(true);
		myJTable.setRowHeight(18);
		myJTable.setFillsViewportHeight(true);
		myJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		final JScrollPane mySrollPane=new JScrollPane();	//�������
		mySrollPane.add(myJTable);
		mySrollPane.setViewportView(myJTable);
		displayMid.add(mySrollPane, BorderLayout.CENTER);
		
		
		jifShow.add(displayUp,BorderLayout.NORTH);
		jifShow.add(displayMid,BorderLayout.CENTER);
//		jifShow.add(displayDown,BorderLayout.SOUTH);
		jifShow.setVisible(true);
		jplDisplay.add(jifShow);
		
		JOptionPane.showMessageDialog(jplDisplay, "��ѡ����Ӧ���ͽ��в鿴","��ʾ", 1);
		//ΪjbDelete��ť��Ӽ�����
		jbDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tableName=productBox.getSelectedItem().toString();
				if(tableName.length()==0)//��Ʒ������ʾ��1������Ϣ����
				{
					JOptionPane.showMessageDialog(jifShow, "��ѡ����Ӧ����","��ʾ",1);
					return;
				}
				String productName=pName.getText();
				Pattern pattern=Pattern.compile("^[\\040]*");//���С�\040�ǿո�İ˽���ת���ַ���
				if(pattern.matcher(productName).matches()==true)//��Ʒ������ʾ��
				{
					JOptionPane.showMessageDialog(jifShow, "��������Ӧ���͵ı��","��ʾ",1);
					return;
				}
				try{
					operate = new Management();
					operate.rs=operate.stmt.executeQuery("select * from "+
							tableName);
					ResultSetMetaData rsmd_t=operate.rs.getMetaData();	//�������������					
					int colCount=rsmd_t.getColumnCount();		//�õ�����
					String[] name=new String[colCount];
					for(int i=1;i<=colCount;i++)	//�õ�����
					{
						name[i-1]=rsmd_t.getColumnName(i);						
					}					
					operate.rs=operate.stmt.executeQuery("select * from "+
												tableName+" where "+name[0]+" ="+productName);
					if(operate.rs.next())
					{
						operate.rs.beforeFirst();
						ResultSetMetaData rsmd=operate.rs.getMetaData();	//�������������
						JTableModel.setRowCount(0);	//���ģ�����������������
						JTableModel.setColumnCount(0);						
						for(int i=1;i<=colCount;i++)	//�õ�����
						{
							name[i-1]=rsmd.getColumnName(i);
							JTableModel.addColumn(name[i-1]);
						}
						while(operate.rs.next())		//�õ����е�����ֵ
						{
							String[] value=new String[colCount];
							for(int i=1;i<=colCount;i++)
								value[i-1]=operate.rs.getString(i);
							JTableModel.addRow(value);
						}
						operate.rs.close();
						JTableModel.setRowCount(JTableModel.getRowCount()+15);
						JTableModel.setColumnCount(JTableModel.getColumnCount()+10);
						JTable resTable=new JTable(JTableModel);
//						resTable.setEnabled(true);		//���ñ���ܱ��༭
						resTable.setRowHeight(18);		//�����и�
						resTable.setFillsViewportHeight(true);
						resTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�ر��Զ������п�
						mySrollPane.add(resTable);
						mySrollPane.setViewportView(resTable);
						Object[] value={"��","��","ȡ��"};
						int result=JOptionPane.showOptionDialog(mySrollPane, "ȷ��ɾ������Ϣ", "����", 2, 2,null, value, value[0]);
						if(result==0)
						{
							operate.stmt.executeUpdate("delete from "+
												tableName+" where "+name[0]+"="+productName);
							pName.setText(null);
							resTable=new JTable(new DefaultTableModel(10,20));
							resTable.setRowHeight(18);		//�����и�
							resTable.setFillsViewportHeight(true);
							resTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�ر��Զ������п�
							mySrollPane.add(resTable);
							mySrollPane.setViewportView(resTable);
							JOptionPane.showOptionDialog(mySrollPane, "����ɾ���ѳɹ���", "��ʾ", 2, 2,null, value, value[0]);
						}						
					}
					else
					{
						JTable resTable=new JTable(new DefaultTableModel(10,20));
						resTable.setRowHeight(18);		//�����и�
						resTable.setFillsViewportHeight(true);
						resTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//�ر��Զ������п�
						mySrollPane.add(resTable);
						mySrollPane.setViewportView(resTable);
						JOptionPane.showMessageDialog(jifShow, "����Ϣ������","����",1);
					}
				}catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(jplDisplay, "���ݿ������쳣","����",0);
				}catch(SQLException fault){
					JOptionPane.showMessageDialog(jifShow, "���ݿ�����쳣","����",0);
					fault.printStackTrace();
				}				
			}
		});		
	}
}