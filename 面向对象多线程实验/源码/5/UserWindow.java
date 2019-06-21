package lesson4;


import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class UserWindow{
	String admin_name;
	public void showMenu(String name,int index) {
		admin_name=name;
		JFrame frame=new JFrame();
		frame.setTitle("用户管理界面");
		frame.setSize(380,300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JPanel panel_add=new JPanel();
		panel_add.setLayout(null);
		tabbedPane.addTab("添加用户", panel_add);
		JLabel L1=new JLabel("用户名:");
		JTextField te1=new JTextField(25);
		JLabel L2=new JLabel("密码:");
		JPasswordField te2=new JPasswordField(25);
		JLabel L3=new JLabel("角色:");
		String role[]= {"administrator","operator","browser"};
		JComboBox<String> box=new JComboBox<String>(role);
		te2.setEchoChar('*');
		L1.setFont(new Font("黑体",Font.PLAIN,18));
		L1.setSize(50,30);
		L1.setBounds(5+30,30,50,30);
		te1.setFont(new Font("黑体",Font.PLAIN,18));
		te1.setSize(250,30);
		te1.setBounds(5+80,30,250,30);
		L2.setFont(new Font("黑体",Font.PLAIN,18));
		L2.setSize(50,30);
		L2.setBounds(5+30,80,50,30);
		te2.setFont(new Font("黑体",Font.PLAIN,18));
		te2.setSize(250,30);
		te2.setBounds(5+80,80,250,30);
		L3.setFont(new Font("黑体",Font.PLAIN,18));
		L3.setSize(50,30);
		L3.setBounds(5+30,130,50,30);
		box.setFont(new Font("黑体",Font.PLAIN,18));
		box.setSize(200,30);
		box.setBounds(5+80,130,200,30);
		panel_add.add(L1);
		panel_add.add(te1);
		panel_add.add(L2);
		panel_add.add(te2);
		panel_add.add(L3);
		panel_add.add(box);
		JButton B1=new JButton("添加");
		JButton B2=new JButton("退出");
		B1.setSize(80,40);
		B1.setBounds((380-80-5)/2-60,(300-40-30)/2+50,80,40);
		B2.setSize(80,40);
		B2.setBounds((380-80-5)/2+60,(300-40-30)/2+50,80,40);
		panel_add.add(B1);
		panel_add.add(B2);
		B1.addActionListener(new ButtonHandler(frame,te1,te2,box));
		B2.addActionListener(new ButtonHandler(frame));
	
		String[] columnName={"用户名","密码","角色"};
		String[][] rowData=new String[50][3];
		Enumeration<User> e=null;
		try {
			e = DataProcessing.getAllUser();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();;
		}
		User user;
		String[] nameData=new String[50];
		int i=0;
		while(e.hasMoreElements()) {
			user=e.nextElement();
			nameData[i]=rowData[i][0]=user.getName();
			rowData[i][1]=user.getPassword();
			rowData[i][2]=user.getRole();
			i++;
		}
		
		JPanel panel_mod=new JPanel();
		panel_mod.setLayout(null);
		tabbedPane.addTab("修改用户", panel_mod);
		JLabel L4=new JLabel("用户名:");
		JComboBox<String> box_name=new JComboBox<String>(nameData);
		JLabel L5=new JLabel("密码:");
		JPasswordField te4=new JPasswordField(25);
		JLabel L6=new JLabel("角色:");
		JComboBox<String> box2=new JComboBox<String>(role);
		te4.setEchoChar('*');
		L4.setFont(new Font("黑体",Font.PLAIN,18));
		L4.setSize(50,30);
		L4.setBounds(5+30,30,50,30);
		box_name.setFont(new Font("黑体",Font.PLAIN,18));
		box_name.setSize(250,30);
		box_name.setBounds(5+80,30,250,30);
		L5.setFont(new Font("黑体",Font.PLAIN,18));
		L5.setSize(50,30);
		L5.setBounds(5+30,80,50,30);
		te4.setFont(new Font("黑体",Font.PLAIN,18));
		te4.setSize(250,30);
		te4.setBounds(5+80,80,250,30);
		L6.setFont(new Font("黑体",Font.PLAIN,18));
		L6.setSize(50,30);
		L6.setBounds(5+30,130,50,30);
		box2.setFont(new Font("黑体",Font.PLAIN,18));
		box2.setSize(200,30);
		box2.setBounds(5+80,130,200,30);
		panel_mod.add(L4);
		panel_mod.add(box_name);
		panel_mod.add(L5);
		panel_mod.add(te4);
		panel_mod.add(L6);
		panel_mod.add(box2);
		JButton B3=new JButton("修改");
		JButton B4=new JButton("退出");
		B3.setSize(80,40);
		B3.setBounds((380-80-5)/2-60,(300-40-30)/2+50,80,40);
		B4.setSize(80,40);
		B4.setBounds((380-80-5)/2+60,(300-40-30)/2+50,80,40);
		panel_mod.add(B3);
		panel_mod.add(B4);
		B3.addActionListener(new ButtonHandler(frame,box_name,te4,box2));
		B4.addActionListener(new ButtonHandler(frame));
		
		JPanel panel_del=new JPanel();
		panel_del.setLayout(null);
		tabbedPane.addTab("删除用户", panel_del);
		@SuppressWarnings("serial")
		JTable table=new JTable(rowData,columnName) {
			public boolean isCellEditable(int rowIndex,int ColIndex) {
				return false;
			}
		};
		table.setFont(new Font("黑体",Font.PLAIN,18));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setVisible(true);
		scroll.setSize(380,160);
		scroll.setBounds(0,0,380,160);
		panel_del.add(scroll);
		JButton B5=new JButton("删除");
		JButton B6=new JButton("退出");
		B5.setSize(80,40);
		B5.setBounds((380-80-5)/2-60,(300-40-30)/2+50,80,40);
		B6.setSize(80,40);
		B6.setBounds((380-80-5)/2+60,(300-40-30)/2+50,80,40);
		panel_del.add(B5);
		panel_del.add(B6);
		B5.addActionListener(new ButtonHandler(frame,table));
		B6.addActionListener(new ButtonHandler(frame));
		tabbedPane.setVisible(true);
		tabbedPane.setSelectedIndex(index);
		frame.add(tabbedPane);
		frame.setVisible(true);		
	}
	public class ButtonHandler implements ActionListener{
		public JTextField te1=new JTextField();
	    public JPasswordField te2=new JPasswordField();
	    public JFrame frame=new JFrame();
	    public JComboBox<String> box=new JComboBox<String>();
	    public JComboBox<String> box_name=new JComboBox<String>();
	    public JTable table;
	    public String role;
	    ButtonHandler(JFrame frame){
	    	this.frame=frame;
	    }
	    ButtonHandler(JFrame frame,JTextField te1,JPasswordField te2,JComboBox<String> box){
	    	this.frame=frame;
			this.te1=te1;
			this.te2=te2;
			this.box=box;
	    }
	    ButtonHandler(JFrame frame,JComboBox<String> box_name,JPasswordField te2,JComboBox<String> box) {
	    	this.frame=frame;
			this.box_name=box_name;
			this.te2=te2;
			this.box=box;
		}
	    ButtonHandler(JFrame frame,JTable table) {
	    	this.frame=frame;
			this.table=table;
		}
		public void actionPerformed(ActionEvent e) {
			String name=te1.getText();
			String password=String.valueOf(te2.getPassword());
			if(e.getActionCommand().equals("修改")) {
				try {
					name=(String)box_name.getSelectedItem();
					if(DataProcessing.searchUser(name)!=null) {
						role=(String)box.getSelectedItem();
						DataProcessing.update(name, password, role);
						JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
						frame.dispose();
						UserWindow userWindow=new UserWindow();
						userWindow.showMenu(admin_name,1);
					}
					else {
						JOptionPane.showMessageDialog(null, "账号不存在", "温馨提示", JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getActionCommand().equals("添加")){
				role=(String)box.getSelectedItem();
				try {
					DataProcessing.insert(name, password, role);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "添加成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
				frame.dispose();
				UserWindow userWindow=new UserWindow();
				userWindow.showMenu(admin_name,0);
			}	
			else if(e.getActionCommand().equals("删除")){
				if(table.getSelectedRow()<0) ; 
				else {
					String name_del=(String) table.getValueAt(table.getSelectedRow(), 0);
					if(name_del.equals(admin_name)) {
						JOptionPane.showMessageDialog(null, "无法删除", "温馨提示", JOptionPane.ERROR_MESSAGE);
					}
					else {
						try {
							if(DataProcessing.delete(name_del)) {
								JOptionPane.showMessageDialog(null, "删除成功", "温馨提示", JOptionPane.PLAIN_MESSAGE);
					
							}
							else {
								JOptionPane.showMessageDialog(null, "账号不存在", "温馨提示", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						frame.dispose();
						UserWindow userWindow=new UserWindow();
						userWindow.showMenu(admin_name,2);
					}
					
				}
			}	
			else if(e.getActionCommand().equals("退出")) {
				frame.dispose();
			}
		}
	}
}
