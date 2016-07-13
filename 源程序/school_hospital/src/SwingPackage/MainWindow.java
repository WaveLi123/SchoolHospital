
package SwingPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class MainWindow extends JFrame
{
	
	final int MIN_WIDTH=900;
	final int MIN_HIGHT=600;
    // 获取屏幕大小  
    private static int WINDOW_WIDTH = Toolkit.getDefaultToolkit()  
            .getScreenSize().width;  
    private static int WINDOW_HEIGHT = Toolkit.getDefaultToolkit()  
            .getScreenSize().height; 
    // 设置窗口位置  
	Point point=new Point(WINDOW_WIDTH/2,WINDOW_HEIGHT/2);		//窗口的当前坐标
	
	//菜单设计
	JMenuBar myMenubar;
	JMenu menuFile,menuEdit;
	JMenuItem menuitemExit, menuitemAbout;
	
	//按钮
	Icon iconTitle;
	JButton jbnButtons[];
	JPanel jplTitle,jplDisplay,jplButton;
	
	//文本信息设置
	Font f12 = new Font("Times New Roman", 0, 10);
	Font f121 = new Font("Times New Roman", 1,10);
	Font fbutton=new Font("华文行楷", 1, 20);
	
	String[] name={"显示信息","添加信息","更改信息","查询信息","删除信息"};
	/** Creates new form MainWindow */
	public MainWindow()
	{
		this.initComponents();
	}
	private void initComponents()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//添加菜单栏
		menuFile=new JMenu("File");
		menuFile.setFont(f121);			
		menuitemExit=new JMenuItem("Exit");
		menuitemExit.setFont(f12);
		menuFile.add(menuitemExit);
		
		menuEdit=new JMenu("Help");
		menuEdit.setFont(f121);
		menuitemAbout=new JMenuItem("About...");
		menuitemAbout.setFont(f12);
		menuEdit.add(menuitemAbout);
		
		myMenubar=new JMenuBar();
		myMenubar.add(menuFile);
		myMenubar.add(menuEdit);		
		this.setJMenuBar(myMenubar);
		
		//设置主窗口的位置
		this.setMinimumSize(new Dimension(MIN_WIDTH,MIN_HIGHT));
		this.setSize(900,700);
		this.setTitle("MUC医院信息管理系统");
		this.setLocationRelativeTo(null);//在屏幕正中间显示

		final JFrame jfmain=this;
		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){}	//鼠标点击
			public void mouseEntered(MouseEvent e){}	//鼠标进入
			public void mouseExited(MouseEvent e){}		//鼠标退出
			public void mouseReleased(MouseEvent e){}	//鼠标松开
			public void mousePressed(MouseEvent e){		//鼠标按下
				point=e.getPoint();//获取当前鼠标坐标
			}
		});
		
		//添加按钮面板
		jbnButtons=new JButton[5];		
		for(int i=0;i<jbnButtons.length;i++)
		{
			// set the style of each Jbutton label
			jbnButtons[i]=new JButton(name[i]);
			jbnButtons[i].setFont(fbutton);
			jbnButtons[i].setForeground(Color.BLACK);
		}
		
		jplButton=new JPanel();		//create the panel to store Button
		jplButton.setBackground(new Color(250,250,240));
		jplButton.setLayout(new GridLayout(9,1));
		for(int i=0;i<9;i++)
		{
			if((i%2)==0)
				jplButton.add(jbnButtons[i/2],BorderLayout.CENTER);
			else
				jplButton.add(new JLabel());
		}
		this.add(jplButton,BorderLayout.WEST);
		
		//添加显示面板		
//		jplDisplay=new JPanel();
//		jplDisplay.setLayout(new BorderLayout());
//		jplDisplay.setBackground(new Color(230,230,230));
//		this.add(jplDisplay,BorderLayout.CENTER);
		
		//添加背景图片
		  // 利用JPanel添加背景图片
		jplDisplay = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("image\\MUC_1.jpg");
				Image img = icon.getImage();
				g.drawImage(img, 0, 0, icon.getIconWidth(),
						icon.getIconHeight(), icon.getImageObserver());
				this.setSize(icon.getIconWidth(), icon.getIconHeight());
			}
		};
		jplDisplay.setLayout(new BorderLayout());
		jplDisplay.setBackground(new Color(230,230,230));		
		this.add(jplDisplay,BorderLayout.CENTER);
		this.setResizable(false);
		this.pack();
		
		//为组件添加监听器
		jbnButtons[0].addActionListener(new DisplayListener(jplDisplay));
		jbnButtons[1].addActionListener(new AddListener(jplDisplay));
		jbnButtons[2].addActionListener(new AdjustListener(jplDisplay));
		jbnButtons[3].addActionListener(new SearchListener(jplDisplay));	
		jbnButtons[4].addActionListener(new DeleteListener(jplDisplay));
		menuitemExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
	}
}