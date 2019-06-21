package lesson5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class MenuWindow {
    public void showMenu() {
    try {
			Client.Display_Doc();
			Client.Display_User();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
    	JFrame frame=new JFrame();
		frame.setTitle("菜单界面");
		frame.setSize(1600,1200);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		JMenuBar menu=new JMenuBar();
		JMenu menu1=new JMenu("用户管理");
		JMenu menu2=new JMenu("文件管理");
		JMenu menu3=new JMenu("密码管理");
		JMenuItem item1=new JMenuItem("添加用户");
		JMenuItem item2=new JMenuItem("修改用户");
		JMenuItem item3=new JMenuItem("删除用户");
		JMenuItem item4=new JMenuItem("上传文件");
		JMenuItem item5=new JMenuItem("下载文件");
		JMenuItem item6=new JMenuItem("修改密码");
		JMenuItem item7=new JMenuItem("退出");
		String role=Client.get_Role();
		String name=Client.get_Name();
		if(role.equals("administrator")) {
			menu1.setEnabled(true);
			menu2.setEnabled(true);
			menu3.setEnabled(true);
			item1.setEnabled(true);
			item2.setEnabled(true);
			item3.setEnabled(true);
			item4.setEnabled(false);
			item5.setEnabled(true);
			item6.setEnabled(true);
			item7.setEnabled(true);
		}
		else if(role.equals("operator")){
			menu1.setEnabled(false);
			menu2.setEnabled(true);
			menu3.setEnabled(true);
			item1.setEnabled(false);
			item2.setEnabled(false);
			item3.setEnabled(false);
			item4.setEnabled(true);
			item5.setEnabled(true);
			item6.setEnabled(true);
			item7.setEnabled(true);
		}
		else if(role.equals("browser")){
			menu1.setEnabled(false);
			menu2.setEnabled(true);
			menu3.setEnabled(true);
			item1.setEnabled(false);
			item2.setEnabled(false);
			item3.setEnabled(false);
			item4.setEnabled(false);
			item5.setEnabled(true);
			item6.setEnabled(true);
			item7.setEnabled(true);
		}
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		menu3.add(item6);
		menu3.add(item7);
		menu.add(menu1);
		menu.add(menu2);
		menu.add(menu3);
		menu.setSize(195,30);
		menu.setBounds(0,0,195,30);
		menu.setVisible(true);
		frame.add(menu);
		item1.addActionListener(new MenuAction(name,role));
		item2.addActionListener(new MenuAction(name,role));
		item3.addActionListener(new MenuAction(name,role));
		item4.addActionListener(new MenuAction(name,role));
		item5.addActionListener(new MenuAction(name,role));
		item6.addActionListener(new MenuAction(name,role));
		item7.addActionListener(new MenuAction(frame));
		/*frame.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				try {
					Client.closeConnection();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});*/
		frame.setVisible(true);
    }
    class MenuAction implements ActionListener{
    	String name;
    	String role;
    	JFrame frame;
    	MenuAction(JFrame frame){
    		this.frame=frame;
    	}
    	MenuAction(String name,String role){
    		this.name=name;
    		this.role=role;
    	}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="添加用户") {
				UserWindow userWindow=new UserWindow();
				userWindow.showMenu(0);
			}
			else if(e.getActionCommand()=="修改用户") {
				UserWindow userWindow=new UserWindow();
				userWindow.showMenu(1);
			}
			else if(e.getActionCommand()=="删除用户") {
				UserWindow userWindow=new UserWindow();
				userWindow.showMenu(2);
			}
			else if(e.getActionCommand()=="上传文件") {
				UpDownloadWindow updownloadWindow=new UpDownloadWindow();
				updownloadWindow.showMenu(1);
			}
			else if(e.getActionCommand()=="下载文件") {
				UpDownloadWindow updownloadWindow=new UpDownloadWindow();
				updownloadWindow.showMenu(0);
			}
			else if(e.getActionCommand()=="修改密码") {
				PasswordWindow passwordWindow=new PasswordWindow();
				passwordWindow.showMenu();
			}
			else if(e.getActionCommand()=="退出") {
				frame.dispose();
				try {
					Client.closeConnection();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
    }
}
