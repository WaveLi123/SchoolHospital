
package SwingPackage;  
  
import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Dimension;  
import java.awt.FlowLayout;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JCheckBox;  
import javax.swing.JComboBox;  
import javax.swing.JFrame;  
import javax.swing.JInternalFrame;
import javax.swing.JLabel;  
import javax.swing.JOptionPane;
import javax.swing.JPanel;  
import javax.swing.JPasswordField;  

import CategoryPackage.Login;
import CategoryPackage.Prescription;
  
public class Login_r extends JFrame {  
  
    private static final long serialVersionUID = 1L;  
    JFrame jframe = new JFrame();
    JButton login;
    JLabel labelFindpwd;
    JComboBox<String> username;
    JPasswordField  password;    
    boolean success = false;
    public boolean init() {      	
        // 设置窗体大小  
        jframe.setSize(new Dimension(385, 300));  
          
        // 设置窗体为不可调整大小的状态  
        jframe.setResizable(false);  
          
        // 设置窗体的布局  
        jframe.setLayout(new BorderLayout());  
          
        // 设置窗体的显示位置  
        jframe.setLocationRelativeTo(null);  
          
        // 设置船体的标题  
        jframe.setTitle("MUC医院信息管理系统");  
          
        // 设置窗体的关闭按钮点击后的响应方式  
        jframe.setDefaultCloseOperation(3);  
          
        //为了设置背景图片并且显示出来不被遮住所以不得不设置JFrame的内容窗格设置为透明  
        ((JPanel) jframe.getContentPane()).setOpaque(false);  
        //实例化JLabel对象用于显示背景图片  
        ImageIcon bg = new ImageIcon("image/MUC.jpg");  
        JLabel label = new JLabel(bg);  
          
        //添加背景图片标签  
        jframe.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  
        //设置背景标签的位置  
        label.setBounds(0, 0, 382, 275);  
  
        // 调用显示北边的方法，用于初始化北边要显示的组件  
        createNorth();  
  
        // 调用显示南边的方法，用于初始化北边要显示的组件  
        createSouth();  
  
        // 调用显示西边的方法，用于初始化北边要显示的组件  
        createWest();  
  
        // 调用显示中间的方法，用于初始化北边要显示的组件  
        createCenter();
        // 设置窗体为可见状态  
        jframe.setVisible(true);
        
        //添加监听器        
        login.addMouseListener(new MouseListener(){			
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				try {
					//登录成功
					if(login.Define((String) username.getSelectedItem(),password.getText())){
						jframe.dispose();
						new MainWindow().setVisible(true);						
					}
					//登录失败
					else{
						JInternalFrame jifShow = null;
						JOptionPane.showMessageDialog(jifShow,"您输入的密码有误，请重新输入!","提示",1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}

			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {				
			}
			public void mouseEntered(MouseEvent e) {	
			}
			public void mouseExited(MouseEvent e) {	
			}
			}        
        );           
        return success;
    }  
  
    // 定义初始化北边的方法，对北边的组件初始化  
    private void createNorth() {  
        //添加这个标签不让中间的组件跑到最上面来  
        JLabel label = new JLabel();  
        label.setPreferredSize(new Dimension(378, 120));  
        label.setOpaque(false);  
        jframe.add(label, BorderLayout.NORTH);  
    }  
  
    // 定义初始化南边的方法，对南边的组件初始化  
    private void createSouth() {  
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));  
        panel.setOpaque(false);  
        // 添加多账号登陆图片  
        ImageIcon icon = new ImageIcon("res/a.jpg");  
        JLabel labelM = new JLabel(icon);  
        panel.add(labelM);  
  
        //因为使用的是流式布局所以添加的组件必定会挨在一起所以添加这个以达到不让组件挨着的目的  
        JLabel jl1 = new JLabel();  
        jl1.setPreferredSize(new Dimension(65, 30));  
        panel.add(jl1);  
  
        // 添加登陆按钮  
        login = new JButton("登陆");  
        login.setPreferredSize(new Dimension(156, 35));  
        panel.add(login);  
  
        //因为使用的是流式布局所以添加的组件必定会挨在一起所以添加这个以达到不让组件挨着的目的  
        JLabel jl2 = new JLabel();  
        jl2.setPreferredSize(new Dimension(55, 30));  
        panel.add(jl2);  
  
        // 添加二维码登陆图片  
        ImageIcon image = new ImageIcon("res/b.jpg");  
        JLabel label = new JLabel(image);  
        panel.add(label);  
  
        jframe.add(panel, BorderLayout.SOUTH);  
    }  
  
    // 定义初始化西边的方法，对西边的组件初始化  
    private void createWest() {  
        // 实例化一个JPanel容器用于放置头像图片  
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));  
        panel.setOpaque(false);  
  
        // 实例化一个ImageIcon对象用于实例化一个JLabel对象放置头像图片  
        ImageIcon icon = new ImageIcon("res/head.jpg");  
        JLabel label = new JLabel(icon);  
        panel.add(label);  
  
        // 设置panel的大小  
        panel.setPreferredSize(new Dimension(100, 0));  
  
        jframe.add(panel, BorderLayout.WEST);  
    }  
  
    // 定义初始化中间的方法，对中间的组件初始化  
    private void createCenter() {  
        // 实例化一个JPanel容器用于放置中间要显示的元素组件  
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));  
        panel.setOpaque(false);  
  
        // 定义JChomboBox所需的下拉列表的内容
        String [] name = null;
		try {
			name = (new Login()).getkeyinfo();			
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		}          
        username = new JComboBox<String>(name);  
  
        // 设置用户名输入框的大小  
        username.setPreferredSize(new Dimension(187, 25));  
  
        // 设置JComboBox为可编辑状态  
        username.setEditable(true);  
        panel.add(username);  
  
        // 添加注册密码文本  
        JLabel labelReg = new JLabel("注册账号");  
        //设置文字的颜色  
        labelReg.setForeground(Color.BLUE);  
        panel.add(labelReg);  
  
        // 添加密码输入框  
        password = new JPasswordField();  
        password.setPreferredSize(new Dimension(187, 25));  
        panel.add(password);  
  
        // 添加找回密码文本  
        labelFindpwd = new JLabel("找回密码");  
        //设置文字的颜色  
        labelFindpwd.setForeground(Color.BLUE);  
        panel.add(labelFindpwd);  
  
        // 添加记住密码复选框  
        JCheckBox remPwd = new JCheckBox("记住密码");  
        //设置文字的颜色  
        remPwd.setForeground(Color.BLUE);  
        //设置该组件透明不让其遮住背景图片  
        remPwd.setOpaque(false);  
        panel.add(remPwd);  
  
        // 添加自动登陆复选框  
        JCheckBox autoLogin = new JCheckBox("自动登陆");  
        //设置文字的颜色  
        autoLogin.setForeground(Color.BLUE);  
        //设置该组件透明不让其遮住背景图片  
        autoLogin.setOpaque(false);  
        panel.add(autoLogin);  
  
        jframe.add(panel, BorderLayout.CENTER); 
    }  
}  