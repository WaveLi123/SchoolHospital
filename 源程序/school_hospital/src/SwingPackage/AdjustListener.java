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
	JInternalFrame jifShow;//��Ӳ�Ʒ��崰��
	JPanel displayUp,displayMid,displayDown;
	Management operate=null;
	
//	public AdjustListener(MainWindow window)
//	{
//		//�����ʾ���		
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
		
		jifShow=new JInternalFrame("�����Ϣ���",true,true,true);
		jifShow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		//�������
		displayUp=new JPanel(new GridBagLayout());
		final String[] productName={"","ҽ��","����","����","����","ҩ��","ҩƷ","סԺ��Ϣ","������Ϣ","������Ϣ"};		
		final JComboBox productBox=new JComboBox(productName);		//���������˵�
		productBox.setName("����");		
		JLabel jCate=new JLabel("����");
		
		//Doctors
		String[] doctor={"ҽʦ","ר��"}; 
		final JComboBox doctorBox = new JComboBox(doctor);		//���������˵�
		String[] department = {"���ﲿ","�ڿ�","���"};
		final JComboBox departmentBox = new JComboBox(department);		//���������˵�
		
		//Ward
		String [] wardLevel = {"��ͨ����","��֢�໤����","���벡��"};
		final JComboBox wardLevelBox = new JComboBox(wardLevel);		//���������˵�
		String [] wardBed = {"1","2","4"};
		final JComboBox wardBedBox = new JComboBox(wardBed);		//���������˵�
		
		//Patient
		String [] patientSex = {"��","Ů"};
		final JComboBox patientSexBox = new JComboBox(patientSex);		//���������˵�
		
		//Prescription		
		String [] prescription = null;
		try {
			prescription = (new Prescription()).getkeyinfo_drug();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox prescriptionBox = new JComboBox(prescription);		//���������˵�

		//Drugstore		
		String [] drugstore = null;
		try {
			drugstore = (new Drugstore()).getkeyinfo_drug();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox drugstoreBox = new JComboBox(drugstore);		//���������˵�
		
		//Treatment
		String [] treatment_doctor = null;
		try {
			treatment_doctor = (new Treatment()).getkeyinfo_doctor();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox treatment_doctorBox = new JComboBox(treatment_doctor);		//���������˵�
		String [] treatment_patient = null;
		try {
			treatment_patient = (new Treatment()).getkeyinfo_patient();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox treatment_patientBox = new JComboBox(treatment_patient);		//���������˵�
		String [] treatment_prescription = null;
		try {
			treatment_prescription = (new Treatment()).getkeyinfo_prescription();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox treatment_prescriptionBox = new JComboBox(treatment_prescription);		//���������˵�
		
		//Hospital
		String [] hospital_patient = null;
		try {
			hospital_patient = (new Hospital()).getkeyinfo_patient();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox hospital_patientBox = new JComboBox(hospital_patient);		//���������˵�
		String [] hospital_ward = null;
		try {
			hospital_ward = (new Hospital()).getkeyinfo_ward();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}  
		final JComboBox hospital_wardBox = new JComboBox(hospital_ward);		//���������˵�


		//��������Ĳ���
		GridBagConstraints gridBagCon01 = new GridBagConstraints();
		
		gridBagCon01.gridx = 5;
		gridBagCon01.gridy = 0;
		gridBagCon01.insets = new Insets(5,5,5,5); 
		displayUp.add(jCate, gridBagCon01);
		
		gridBagCon01.gridx = 6;
		gridBagCon01.fill = GridBagConstraints.BOTH;;
		displayUp.add(productBox, gridBagCon01);
		displayUp.setBackground(Color.GREEN);
		
		//�м����
		displayMid=new JPanel(new GridBagLayout());
		final int colNum=15;
		final String[] name=new String[colNum];	
		final JTextField[] columnValue=new JTextField[colNum];	//��Ŷ�Ӧ��ֵ
		for(int i=0;i<colNum;i++){
			name[i]=null;
			columnValue[i]=null;
		}
		String[] sTF={"","True","False"};
		final JComboBox removableJCB=new JComboBox(sTF);
		
		JOptionPane.showMessageDialog(jifShow,"��ѡ����Ӧ������","��ʾ", 2);
		/*ΪproductBox��Ӽ�����*/
		productBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				//�õ���Ʒ������
				final String productName=productBox.getSelectedItem().toString();
				
				if(e.getStateChange()==ItemEvent.DESELECTED)	//�ж������˵����¼�ItemEvent.DESELECTED
					return;
				displayMid.removeAll();
				if(productName.length()==0)		//�жϲ˵����Ƿ�Ϊ��ֵ
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
					//�������ݿ������ݣ�Ϊ�м���岼��
					operate = new Management();
					operate.display(productName);	//���ú������������ݿ�
					ResultSetMetaData rsmd=operate.rs.getMetaData();	//�������������
					int colCount=rsmd.getColumnCount();		//�õ�����
					for(int i=1;i<=colCount;i++)	//�õ�����
					{
						name[i-1]=rsmd.getColumnName(i);
					}
					operate.rs.close();
					
					GridBagConstraints gridBagCon02 = new GridBagConstraints();//���ֹ������
					gridBagCon02.insets = new Insets(10,5,5,5);//���������������ֵ
					gridBagCon02.fill = GridBagConstraints.BOTH;//���ö����Ƿ���������
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
							if(name[i].equalsIgnoreCase("ҽ��ְ��")){																
								displayMid.add(doctorBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("��������")){
								displayMid.add(departmentBox, gridBagCon02);
							}
							//Wards
							else if(name[i].equalsIgnoreCase("��������")){
								displayMid.add(wardLevelBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("������λ��")){
								displayMid.add(wardBedBox, gridBagCon02);
							}
							//Patients
							else if(name[i].equalsIgnoreCase("�����Ա�")){
								displayMid.add(patientSexBox, gridBagCon02);
							}
							//Prescription
							else if(name[i].equalsIgnoreCase("����ҩƷ���")){
								displayMid.add(prescriptionBox, gridBagCon02);
							}
							//Drugstore
							else if(name[i].equalsIgnoreCase("ҩ��ҩƷ���")){
								displayMid.add(drugstoreBox, gridBagCon02);
							}
							//Treatment							
							else if(name[i].equalsIgnoreCase("����ҽ�����")){
								displayMid.add(treatment_doctorBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("���ﲡ����")){
								displayMid.add(treatment_patientBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("���ﴦ������")){
								displayMid.add(treatment_prescriptionBox, gridBagCon02);
							}
							//hospital
							else if(name[i].equalsIgnoreCase("���˲�����")){
								displayMid.add(hospital_patientBox, gridBagCon02);
							}
							else if(name[i].equalsIgnoreCase("���˲�����")){
								displayMid.add(hospital_wardBox, gridBagCon02);
							}
							else{
								columnValue[i]=new JTextField(16);
								displayMid.add(columnValue[i], gridBagCon02);
							}
						}
					}
					displayMid.setVisible(true);	//���ÿɼ���
					jifShow.add(displayMid,BorderLayout.CENTER);
					jplDisplay.add(jifShow);		//��Ӷ���
				}catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(jplDisplay, "���ݿ������쳣","����",0);
					//added by WaveLi,Nov 12 1st in 2014
					e1.printStackTrace();
				}catch(SQLException wrong){
					JOptionPane.showMessageDialog(jifShow, "���ݿ�����쳣","����",0);
					//added by WaveLi,Nov 12 1st in 2014
					wrong.printStackTrace();
				}
			//��ȡ�û�Ҫ�޸ĵ���Ϣ
			int pNum_t = Integer.parseInt(JOptionPane.showInputDialog("����������͵ı�ţ�",""));
			while(pNum_t < 0 && pNum_t > 23232323){
				pNum_t = Integer.parseInt(JOptionPane.showInputDialog("����������͵ı�ţ�",""));
			}
			String pNum = String.valueOf(pNum_t);
			try {
				operate = new Management();												
				operate.display(productName);
				ResultSetMetaData rsmd_t=operate.rs.getMetaData();	//�������������
				int colCount=rsmd_t.getColumnCount();		//�õ�����
				String[] name=new String[colCount];

				for(int i=1;i<=colCount;i++)	//�õ�����
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
					ResultSetMetaData rsmd=operate.rs.getMetaData();	//�������������					
					while(operate.rs.next())		//�õ����е�����ֵ
					{
						String[] value=new String[colCount];
						for(int i=1;i<=colCount;i++){
							value[i-1] = operate.rs.getString(i);
							//Doctors
							if(name[i-1].equalsIgnoreCase("ҽ��ְ��")){													
								doctorBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("��������")){
								departmentBox.setSelectedItem(value[i-1]);
							}
							//Wards
							else if(name[i-1].equalsIgnoreCase("��������")){								
								wardLevelBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("������λ��")){								
								wardBedBox.setSelectedItem(value[i-1]);
							}
							//Patients
							else if(name[i-1].equalsIgnoreCase("�����Ա�")){								
								patientSexBox.setSelectedItem(value[i-1]);
							}
							//Prescription
							else if(name[i-1].equalsIgnoreCase("����ҩƷ���")){								
								prescriptionBox.setSelectedItem(value[i-1]);
							}
							//Drugstore
							else if(name[i-1].equalsIgnoreCase("ҩ��ҩƷ���")){								
								drugstoreBox.setSelectedItem(value[i-1]);
							}
							//Treatment
							else if(name[i-1].equalsIgnoreCase("����ҽ�����")){								
								treatment_doctorBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("���ﲡ����")){								
								treatment_patientBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("���ﴦ������")){								
								treatment_prescriptionBox.setSelectedItem(value[i-1]);
							}
							//Hospital
							else if(name[i-1].equalsIgnoreCase("���˲�����")){								
								hospital_patientBox.setSelectedItem(value[i-1]);
							}
							else if(name[i-1].equalsIgnoreCase("���˲�����")){								
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
		
		//�ײ����
		JButton jbStore=new JButton("����");			//���ð�ť
		JButton jbReset=new JButton("���");
		JButton jbQuit=new JButton("�˳�");
		displayDown=new JPanel(new FlowLayout());
		displayDown.add(jbStore);
		displayDown.add(jbReset);
		displayDown.add(jbQuit);
		displayDown.setBackground(Color.BLACK);
		
		jifShow.add(displayUp,BorderLayout.NORTH);
		jifShow.add(displayDown,BorderLayout.SOUTH);
		jifShow.setVisible(true);
		jplDisplay.add(jifShow);
		//ΪjbQuit��ť��Ӽ�����
		jbQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jifShow.dispose();
			}
		});
		//ΪjbReset��ť��Ӽ�����
		jbReset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				productBox.setSelectedIndex(0);				
			}
		});
		
		//ΪjbStore��ť��Ӽ�����
		jbStore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tableName=productBox.getSelectedItem().toString();
				
				if(tableName.length()==0)//��Ʒ������ʾ��1������Ϣ����
				{
					JOptionPane.showMessageDialog(jifShow, "��ѡ���Ʒ����","��ʾ", 1);
					return;
				}																		
				int max=0;
				for(;name[max]!=null;){max++;}
				String[] tableValue=new String[max];
				int i = 0;
				for(; i < max; i++){
					//Doctors
					if(name[i].equalsIgnoreCase("ҽ��ְ��")){
						tableValue[i] = new String((String) doctorBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("��������")){
						tableValue[i] = String.valueOf(departmentBox.getSelectedItem());
					}
					//Wards
					else if(name[i].equalsIgnoreCase("��������")){
						tableValue[i] = String.valueOf((String) wardLevelBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("������λ��")){
						tableValue[i] = String.valueOf(wardBedBox.getSelectedItem());
					}
					//Patients
					else if(name[i].equalsIgnoreCase("�����Ա�")){
						tableValue[i] = String.valueOf((String) patientSexBox.getSelectedItem());
					}
					//Prescription
					else if(name[i].equalsIgnoreCase("����ҩƷ���")){
						tableValue[i] = String.valueOf(prescriptionBox.getSelectedItem());
					}
					//Drugstore
					else if(name[i].equalsIgnoreCase("ҩ��ҩƷ���")){
						tableValue[i] = String.valueOf(drugstoreBox.getSelectedItem());
					}
					//Treatment
					else if(name[i].equalsIgnoreCase("����ҽ�����")){
						tableValue[i] = String.valueOf(treatment_doctorBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("���ﲡ����")){
						tableValue[i] = String.valueOf(treatment_patientBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("���ﴦ������")){
						tableValue[i] = String.valueOf(treatment_prescriptionBox.getSelectedItem());
					}
					//Hospital
					else if(name[i].equalsIgnoreCase("���˲�����")){
						tableValue[i] = String.valueOf(hospital_patientBox.getSelectedItem());
					}
					else if(name[i].equalsIgnoreCase("���˲�����")){
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
					/*���ú����������ݿ��������*/
					if(dbTableName.equalsIgnoreCase("ҽ��")){
						Doctor doctor = new Doctor();
						doctor.addition(tableValue);
						doctor.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("סԺ��Ϣ")){
						Hospital hospital = new Hospital();
						hospital.addition(tableValue);
						hospital.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("ҩƷ")){
						Medicence medicence = new Medicence();
						medicence.addition(tableValue);
						medicence.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("ҩ��")){
						Drugstore drugupdate = new Drugstore();
						drugupdate.addition(tableValue);
						drugupdate.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("����")){
						Department department = new Department();
						department.addition(tableValue);
						department.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("������Ϣ")){
						Treatment treatment = new Treatment();
						treatment.addition(tableValue);
						treatment.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("������Ϣ")){
						Prescription prescription = new Prescription();
						prescription.addition(tableValue);
						prescription.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("����")){
						Patient patient = new Patient();
						patient.addition(tableValue);
						patient.updateDB();
					}
					if(dbTableName.equalsIgnoreCase("����")){
						Ward ward = new Ward();
						ward.addition(tableValue);
						ward.updateDB();
					}
					JOptionPane.showMessageDialog(jifShow, "������Ϣ�ɹ�","Successful", 1);
				}catch(ClassNotFoundException e2){
					JOptionPane.showMessageDialog(jifShow, "���ݿ������쳣","����", 2);
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(jifShow, "���ݿ�����쳣","����", 2);
					//added by WaveLi,Dec 1st in 2014
					e1.printStackTrace();
				}catch(Exception e3){
					JOptionPane.showMessageDialog(jifShow, "������Ϸ�������","����", 2);
				}finally{
					Management.maxId--;			//???
				}
			}
		});
	}
}