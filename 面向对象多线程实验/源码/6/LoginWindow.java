package lesson5;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
@SuppressWarnings("serial")
public class LoginWindow extends JFrame{
	JTabbedPane tabbedPane;
	public void loginFrame() {
		JFrame frame=new JFrame();
		frame.setTitle("系统登录");
		frame.setSize(380,300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel L1=new JLabel("账号:");
		JTextField te1=new JTextField(25);
		JLabel L2=new JLabel("密码:");
		JPasswordField te2=new JPasswordField(25);
		te2.setEchoChar('*');
		L1.setFont(new Font("黑体",Font.PLAIN,18));
		L1.setSize(50,30);
		L1.setBounds(5+30,50,50,30);
		te1.setFont(new Font("黑体",Font.PLAIN,18));
		te1.setSize(250,30);
		te1.setBounds(5+80,50,250,30);
		L2.setFont(new Font("黑体",Font.PLAIN,18));
		L2.setSize(50,30);
		L2.setBounds(5+30,100,50,30);
		te2.setFont(new Font("黑体",Font.PLAIN,18));
		te2.setSize(250,30);
		te2.setBounds(5+80,100,250,30);
		frame.add(L1);
		frame.add(te1);
		frame.add(L2);
		frame.add(te2);
		JButton B1=new JButton("登录");
		JButton B2=new JButton("退出");
		B1.setSize(100,50);
		B1.setBounds((380-100-5)/2-60,(300-50-30)/2+50,100,50);
		B2.setSize(100,50);
		B2.setBounds((380-100-5)/2+60,(300-50-30)/2+50,100,50);
		frame.add(B1);
		frame.add(B2);
		B1.addActionListener(new ButtonHandler(frame,te1,te2));
		B2.addActionListener(new ButtonHandler(frame));
		frame.setVisible(true);
	}
	public class ButtonHandler implements ActionListener{
		public JTextField te1=new JTextField();
	    public JPasswordField te2=new JPasswordField();
	    public JFrame frame=new JFrame();
	    ButtonHandler(JFrame frame){
	    	this.frame=frame;
	    }
	    ButtonHandler(JFrame frame,JTextField te1,JPasswordField te2) {
	    	this.frame=frame;
			this.te1=te1;
			this.te2=te2;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="登录") {
				String name=te1.getText();
				String password=String.valueOf(te2.getPassword());
				try {
					try {
						Client.Login(name,password,frame);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
			}
			else {
				frame.dispose();
			}	
		}
	}
}
