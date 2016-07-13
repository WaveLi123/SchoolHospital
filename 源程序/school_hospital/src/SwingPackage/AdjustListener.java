package SwingPackage;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.*;

import CategoryPackage.Department;
import CategoryPackage.Doctor;
import CategoryPackage.Drugstore;
import CategoryPackage.Hospital;
import CategoryPackage.Medicence;
import CategoryPackage.Patient;
import CategoryPackage.Prescription;
import CategoryPackage.Treatment;
import CategoryPackage.Ward;
import MainPackage.Management;
class AdjustListener implements ActionListener
{
	JPanel jplDisplay;
	JInternalFrame jifShow;//添加产品面板窗口
	JPanel displayUp,displayMid,displayDown;
	Management operate=null;
	
//	public AdjustListener(MainWindow window)
//	{
//		//添加显示面板		
//		jplDisplay=new JPanel();
//		jplDisplay.setLayout(new BorderLayout());
//		jplDisplay.setBackground(new Color(230,230,230));
//		window.add(jplDisplay,BorderLayout.CENTER);		
//	}
	public AdjustListener(JPanel jpl)
	{
		this.jplDisplay = jpl;
	}	
	public void actionPerformed(ActionEvent e)
	{
		jplDisplay.removeAll();
		
		jifShow=new JInternalFrame("添加信息面板",true,true,true);
		jifShow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		//顶层面板
		displayUp=new JPanel(new GridBagLayout());
		final String[] productName={"","医生","病人","病房","科室","药房","药品","住院信息","处方信息","就诊信息"};		
		final JComboBox productBox=new JComboBox(productName);		//设置下拉菜单
		productBox.setName("类型");		
		JLabel jCate=new JLabel("类型");
		
		//Doctors
		String[] doctor={"医师","专家"}; 
		final JComboBox doctorBox = new JComboBox(doctor);		//设置下拉菜单
		String[] department = {"门诊部","内科","外科"};
		final JComboBox departmentBox = new JComboBox(department);		//设置下拉菜单
		
		//Ward
		String [] wardLevel = {"普通病房","重症监护病房","隔离病房"};
		final JComboBox wardLevelBox = new JComboBox(wardLevel);		//设置下拉菜单
		String [] wardBed = {"1","2","4"};
		final JComboBox wardBedBox = new JComboBox(wardBed);		//设置下拉菜单
		
		//Patient
		String [] patientSex = {"男","女"};
		final JComboBox patientSexBox = new JComboBox(patientSex);		//设置下拉菜单
		
		//Prescription		
		String [] prescription = null;
		try {
			prescription = (new Prescription()).getkeyinfo_drug();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox prescriptionBox = new JComboBox(prescription);		//设置下拉菜单

		//Drugstore		
		String [] drugstore = null;
		try {
			drugstore = (new Drugstore()).getkeyinfo_drug();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox drugstoreBox = new JComboBox(drugstore);		//设置下拉菜单
		
		//Treatment
		String [] treatment_doctor = null;
		try {
			treatment_doctor = (new Treatment()).getkeyinfo_doctor();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox treatment_doctorBox = new JComboBox(treatment_doctor);		//设置下拉菜单
		String [] treatment_patient = null;
		try {
			treatment_patient = (new Treatment()).getkeyinfo_patient();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox treatment_patientBox = new JComboBox(treatment_patient);		//设置下拉菜单
		String [] treatment_prescription = null;
		try {
			treatment_prescription = (new Treatment()).getkeyinfo_prescription();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox treatment_prescriptionBox = new JComboBox(treatment_prescription);		//设置下拉菜单
		
		//Hospital
		String [] hospital_patient = null;
		try {
			hospital_patient = (new Hospital()).getkeyinfo_patient();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox hospital_patientBox = new JComboBox(hospital_patient);		//设置下拉菜单
		String [] hospital_ward = null;
		try {
			hospital_ward = (new Hospital()).getkeyinfo_ward();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox hospital_wardBox = new JComboBox(hospital_ward);		//设置下拉菜单


		//设置组件的布局
		GridBagConstraints gridBagCon01 = new GridBagConstraints();
		
		gridBagCon01.gridx = 5;
		gridBagCon01.gridy = 0;
		gridBagCon01.insets = new Insets(5,5,5,5); 
		displayUp.add(jCate, gridBagCon01);
		
		gridBagCon01.gridx = 6;
		gridBagCon01.fill = GridBagConstraints.BOTH;;
		displayUp.add(productBox, gridBagCon01);
		displayUp.setBackground(Color.GREEN);
		
		//中间面板
		displayMid=new JPanel(new GridBagLayout());
		final int colNum=15;
		final String[] name=new String[colNum];	
		final JTextField[] columnValue=new JTextField[colNum];	//存放对应的值
		for(int i=0;i<colNum;i++){
			name[i]=null;
			columnValue[i]=null;
		}
		String[] sTF={"","True","False"};
		final JComboBox removableJCB=new JComboBox(sTF);
		
		JOptionPane.showMessageDialog(jifShow,"请选择相应的类型","提示", 2);
		/*为productBox添加监听器*/
		productBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				//得到产品类型名
				final String productName=productBox.getSelectedItem().toString();
				
				if(e.getStateChange()==ItemEvent.DESELECTED)	//判断下拉菜单的事件ItemEvent.DESELECTED
					return;
				displayMid.removeAll();
				if(productName.length()==0)		//判断菜单项是否为空值
				{
					jplDisplay.add(jifShow);
					return;
				}
				for(int i=0;i<colNum;i++)
				{
					name[i]=null;
					columnValue[i]=null;
				}
				try{
					//根据数据库中内容，为中间面板布局
					operate = new Management();
					operate.display(productName);	//调用函数并连接数据库
					ResultSetMetaData rsmd=operate.rs.getMetaData();	//创建结果集对象
					int colCount=rsmd.getColumnCount();		//得到列数
					for(int i=1;i<=colCount;i++)	//得到列名
					{
						name[i-1]=rsmd.getColumnName(i);
					}
					operate.rs.close();
					
					GridBagConstraints gridBagCon02 = new GridBagConstraints();//布局管理对象
					gridBagCon02.insets = new Insets(10,5,5,5);//设置上下左右填充值
					gridBagCon02.fill = GridBagConstraints.BOTH;//设置对象是否填满区域
					for(int i=0;i<colCount;i++)
					{
						gridBagCon02.gridx = 5;
						gridBagCon02.gridy =(i);
						displayMid.add(new JLabel(name[i]), gridBagCon02);
						gridBagCon02.gridx = 6;
						if(name[i].equals("Removable"))
						{						
							displayMid.add(removableJCB, gridBagCon02);
						}
						else
						{
							
							//Doctors
							if(name[i].equalsIgnoreCase("医生职称")){																
								displayMid.add(doctorBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("所属科室")){
								displayMid.add(departmentBox, gridBagCon02);
							}
							//Wards
							else if(name[i].equalsIgnoreCase("病房级别")){
								displayMid.add(wardLevelBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("病房床位数")){
								displayMid.add(wardBedBox, gridBagCon02);
							}
							//Patients
							else if(name[i].equalsIgnoreCase("病人性别")){
								displayMid.add(patientSexBox, gridBagCon02);
							}
							//Prescription
							else if(name[i].equalsIgnoreCase("处方药品编号")){
								displayMid.add(prescriptionBox, gridBagCon02);
							}
							//Drugstore
							else if(name[i].equalsIgnoreCase("药房药品编号")){
								displayMid.add(drugstoreBox, gridBagCon02);
							}
							//Treatment							
							else if(name[i].equalsIgnoreCase("就诊医生编号")){
								displayMid.add(treatment_doctorBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("就诊病历号")){
								displayMid.add(treatment_patientBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("就诊处方单号")){
								displayMid.add(treatment_prescriptionBox, gridBagCon02);
							}
							//hospital
							else if(name[i].equalsIgnoreCase("病人病历号")){
								displayMid.add(hospital_patientBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("病人病房号")){
								displayMid.add(hospital_wardBox, gridBagCon02);
							}
							else{
								columnValue[i]=new JTextField(16);
								displayMid.add(columnValue[i], gridBagCon02);
							}
						}
					}
					displayMid.setVisible(true);	//设置可见性
					jifShow.add(displayMid,BorderLayout.CENTER);
					jplDisplay.add(jifShow);		//添加对象
				}catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(jplDisplay, "数据库连接异常","错误",0);
					//added by WaveLi,Nov 12 1st in 2014
					e1.printStackTrace();
				}catch(SQLException wrong){
					JOptionPane.showMessageDialog(jifShow, "数据库访问异常","错误",0);
					//added by WaveLi,Nov 12 1st in 2014
					wrong.printStackTrace();
				}
			//获取用户要修改的信息
			int pNum_t = Integer.parseInt(JOptionPane.showInputDialog("请输入该类型的编号：",""));
			while(pNum_t < 0 && pNum_t > 23232323){
				pNum_t = Integer.parseInt(JOptionPane.showInputDialog("请输入该类型的编号：",""));
			}
			String pNum = String.valueOf(pNum_t);
			try {
				operate = new Management();												
				operate.display(productName);
				ResultSetMetaData rsmd_t=operate.rs.getMetaData();	//创建结果集对象
				int colCount=rsmd_t.getColumnCount();		//得到列数
				String[] name=new String[colCount];

				for(int i=1;i<=colCount;i++)	//得到列名
				{
					name[i-1]=rsmd_t.getColumnName(i);					
				}
				if(operate.rs.next())
				{
					operate.rs.beforeFirst();
					System.out.println("select * from "+
							productName+" where "+name[0]+" = "+Integer.parseInt(pNum));				
					
					operate.rs=operate.stmt.executeQuery("select * from "+
							productName+" where "+name[0]+" = "+Integer.parseInt(pNum));
					ResultSetMetaData rsmd=operate.rs.getMetaData();	//创建结果集对象					
					while(operate.rs.next())		//得到各行的属性值
					{
						String[] value=new String[colCount];
						for(int i=1;i<=colCount;i++){
							value[i-1] = operate.rs.getString(i);
							//Doctors
							if(name[i-1].equalsIgnoreCase("医生职称")){													
								doctorBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("所属科室")){
								departmentBox.setSelectedItem(value[i-1]);
							}
							//Wards
							else if(name[i-1].equalsIgnoreCase("病房级别")){								
								wardLevelBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("病房床位数")){								
								wardBedBox.setSelectedItem(value[i-1]);
							}
							//Patients
							else if(name[i-1].equalsIgnoreCase("病人性别")){								
								patientSexBox.setSelectedItem(value[i-1]);
							}
							//Prescription
							else if(name[i-1].equalsIgnoreCase("处方药品编号")){								
								prescriptionBox.setSelectedItem(value[i-1]);
							}
							//Drugstore
							else if(name[i-1].equalsIgnoreCase("药房药品编号")){								
								drugstoreBox.setSelectedItem(value[i-1]);
							}
							//Treatment
							else if(name[i-1].equalsIgnoreCase("就诊医生编号")){								
								treatment_doctorBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("就诊病历号")){								
								treatment_patientBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("就诊处方单号")){								
								treatment_prescriptionBox.setSelectedItem(value[i-1]);
							}
							//Hospital
							else if(name[i-1].equalsIgnoreCase("病人病历号")){								
								hospital_patientBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("病人病房号")){								
								hospital_wardBox.setSelectedItem(value[i-1]);
							}						
							else{
								columnValue[i-1].setText(value[i-1]);
							}							
						}																						
					}
					operate.rs.close();
				}
			} catch (ClassNotFoundException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			} catch (SQLException e5) {
				// TODO Auto-generated catch block					
				e5.printStackTrace();
			}
		}
		});
		
		//底层面板
		JButton jbStore=new JButton("保存");			//设置按钮
		JButton jbReset=new JButton("清除");
		JButton jbQuit=new JButton("退出");
		displayDown=new JPanel(new FlowLayout());
		displayDown.add(jbStore);
		displayDown.add(jbReset);
		displayDown.add(jbQuit);
		displayDown.setBackground(Color.BLACK);
		
		jifShow.add(displayUp,BorderLayout.NORTH);
		jifShow.add(displayDown,BorderLayout.SOUTH);
		jifShow.setVisible(true);
		jplDisplay.add(jifShow);
		//为jbQuit按钮添加监听器
		jbQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jifShow.dispose();
			}
		});
		//为jbReset按钮添加监听器
		jbReset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				productBox.setSelectedIndex(0);				
			}
		});
		
		//为jbStore按钮添加监听器
		jbStore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tableName=productBox.getSelectedItem().toString();
				
				if(tableName.length()==0)//产品类型提示框，1代表消息类型
				{
					JOptionPane.showMessageDialog(jifShow, "请选择产品类型","提示", 1);
					return;
				}																		
				int max=0;
				for(;name[max]!=null;){max++;}
				String[] tableValue=new String[max];
				int i = 0;
				for(; i < max; i++){
					//Doctors
					if(name[i].equalsIgnoreCase("医生职称")){
						tableValue[i] = new String((String) doctorBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("所属科室")){
						tableValue[i] = String.valueOf(departmentBox.getSelectedItem());
					}
					//Wards
					else if(name[i].equalsIgnoreCase("病房级别")){
						tableValue[i] = String.valueOf((String) wardLevelBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("病房床位数")){
						tableValue[i] = String.valueOf(wardBedBox.getSelectedItem());
					}
					//Patients
					else if(name[i].equalsIgnoreCase("病人性别")){
						tableValue[i] = String.valueOf((String) patientSexBox.getSelectedItem());
					}
					//Prescription
					else if(name[i].equalsIgnoreCase("处方药品编号")){
						tableValue[i] = String.valueOf(prescriptionBox.getSelectedItem());
					}
					//Drugstore
					else if(name[i].equalsIgnoreCase("药房药品编号")){
						tableValue[i] = String.valueOf(drugstoreBox.getSelectedItem());
					}
					//Treatment
					else if(name[i].equalsIgnoreCase("就诊医生编号")){
						tableValue[i] = String.valueOf(treatment_doctorBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("就诊病历号")){
						tableValue[i] = String.valueOf(treatment_patientBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("就诊处方单号")){
						tableValue[i] = String.valueOf(treatment_prescriptionBox.getSelectedItem());
					}
					//Hospital
					else if(name[i].equalsIgnoreCase("病人病历号")){
						tableValue[i] = String.valueOf(hospital_patientBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("病人病房号")){
						tableValue[i] = String.valueOf(hospital_wardBox.getSelectedItem());
					}				
					else{
						tableValue[i]=new String(columnValue[i].getText());
					}					
				}				
				if(name[i+1]!=null && name[i+1].equals("Removable")){
					tableValue[i]=new String(removableJCB.getSelectedItem().toString());
				}
				String dbTableName=new String(productBox.getSelectedItem().toString());
				try {		
					/*调用函数，向数据库添加数据*/
					if(dbTableName.equalsIgnoreCase("医生")){
						Doctor doctor = new Doctor();
						doctor.addition(tableValue);
						doctor.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("住院信息")){
						Hospital hospital = new Hospital();
						hospital.addition(tableValue);
						hospital.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("药品")){
						Medicence medicence = new Medicence();
						medicence.addition(tableValue);
						medicence.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("药房")){
						Drugstore drugupdate = new Drugstore();
						drugupdate.addition(tableValue);
						drugupdate.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("科室")){
						Department department = new Department();
						department.addition(tableValue);
						department.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("就诊信息")){
						Treatment treatment = new Treatment();
						treatment.addition(tableValue);
						treatment.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("处方信息")){
						Prescription prescription = new Prescription();
						prescription.addition(tableValue);
						prescription.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("病人")){
						Patient patient = new Patient();
						patient.addition(tableValue);
						patient.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("病房")){
						Ward ward = new Ward();
						ward.addition(tableValue);
						ward.updateDB();
					}
					JOptionPane.showMessageDialog(jifShow, "更改信息成功","Successful", 1);
				}catch(ClassNotFoundException e2){
					JOptionPane.showMessageDialog(jifShow, "数据库连接异常","警告", 2);
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(jifShow, "数据库插入异常","警告", 2);
					//added by WaveLi,Dec 1st in 2014
					e1.printStackTrace();
				}catch(Exception e3){
					JOptionPane.showMessageDialog(jifShow, "请输入合法的数据","警告", 2);
				}finally{
					Management.maxId--;			//???
				}
			}
		});
	}
}