package lesson3;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;
public class Administrator extends User{
	private Scanner sc;
    Administrator(String name, String password, String role) {
		super(name, password, role);
	}
	public void changeUserInfo() {
		System.out.println("请输入要修改的用户姓名:");
    	String cn=sc.next();
    	System.out.println("请输入新的用户密码:");
    	String cm=sc.next();
    	System.out.println("请输入新的用户角色:");
    	String cr=sc.next();
    	try {
			if(DataProcessing.update(cn, cm, cr)) {
				System.out.println("修改用户信息成功！");
			}
			else System.out.println("修改失败！");
		} 
    	catch (SQLException e) {
    		System.out.println(e.getMessage());
		}
     }
    public void delUser() {
    	System.out.println("请输入要删除的用户姓名:");
    	String dn=sc.next();
    	try {
			if(DataProcessing.delete(dn)) {
				System.out.println("删除成功！");
			}
			else System.out.println("删除失败！该用户不存在");
		} 
    	catch (SQLException e) {
    		System.out.println(e.getMessage());
		}
    }
    public void addUser() {
    	System.out.println("请输入要增加的用户姓名:");
    	String an=sc.next();
    	System.out.println("请输入要增加的用户密码:");
    	String am=sc.next();
    	System.out.println("请输入要增加的用户角色:");
    	String ar=sc.next();
    	try {
			if(DataProcessing.insert(an, am, ar)) {
				System.out.println("增加用户"+an+"成功！");
			}
			else System.out.println("增加用户"+an+"失败！");
		} 
    	catch (SQLException e) {
    		System.out.println(e.getMessage());
		}
    	
    }
    public void listUser() {
    	 Enumeration<User> e = null;
		try {
			e = DataProcessing.getAllUser();
		} 
		catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
    	 User user;
    	 while(e.hasMoreElements()) {
    		 user=e.nextElement();
    		 System.out.println("姓名："+user.getName()+"密码："+user.getPassword()+"角色："+user.getRole());
    	 }
    }
	public void showMenu(String name) {
		System.out.println("欢迎进入档案操作员菜单！");
    	System.out.println("1.修改用户");
    	System.out.println("2.删除用户");
    	System.out.println("3.新增用户");
    	System.out.println("4.列出用户");
    	System.out.println("5.下载文件");
    	System.out.println("6.文件列表");
    	System.out.println("7.修改(本人)密码");
    	System.out.println("8.退出");
    	sc=new Scanner(System.in);
    	int i=sc.nextInt();
    	switch(i) {
    	    case 1:{
    	    	changeUserInfo();
	    	    break;
    	    }
    	    case 2:{
	            delUser();
	            break;
    	    }
    	    case 3:{
    	        addUser();
    	    	break;
        	}
    	    case 4:{
    	        listUser();
    	        break;
        	}
    	    case 5:{
    	    	System.out.println("请输入档案编号:");
    	    	String ID=sc.next();
    	    	try {
					if(downloadFile(ID)) {
						System.out.println("下载成功！");
					}
					else System.out.println("下载失败！");
				} 
    	    	catch (IOException e) {
    	    		System.out.println(e.getMessage());
				}
    	    	break;
    	    }
    	    case 6:{
    	    	try {
					showFileList();
				} 
    	    	catch (SQLException e) {
    	    		System.out.println(e.getMessage());
				}
    	    	break;
    	    }
    	    case 7:{
    	    	System.out.println("请输入新密码：");
    	    	String psd=sc.next();
    	    	try {
					changeSelfInfo(psd);
				} 
    	    	catch (SQLException e) {
    	    		System.out.println("文件访问错误"+e.getMessage());
				}
    	    	break;
    	    }
    	    case 8:{
    	    	exitSystem();
    	    }
    	    default:{
    	    	System.out.println("输入非法！请重新输入！");
    	    	break;
    	    }
    	}
    	showMenu(name);
	}
}
