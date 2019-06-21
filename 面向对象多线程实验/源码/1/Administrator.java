import java.util.*;

public class Administrator extends User{
	String name;
	 String password;
	 String role;
	 public static Scanner in =new Scanner(System.in);
	 
	 public void showMenu() {
		 boolean flag=true;
	 
		while(flag) {
		System.out.println("欢迎进入系统管理员菜单");
		System.out.println("————————————————————");
		System.out.println("	1.修改用户");
		System.out.println("	2.删除用户");
		System.out.println("	3.新增用户");
		System.out.println("	4.列出用户");
		System.out.println("	5.下载文件");
		System.out.println("	6.文件列表");
		System.out.println("	7.修改密码");
		System.out.println("	8.退        出");
		System.out.println("————————————————————");
	    System.out.println("请输入要选择的选项：");
		int choose=in.nextInt();
		switch(choose)
		{
		case 1:{
			System.out.println("输入要更改的用户名：");
			String name=in.next();
			System.out.println("输入密码：");
			String password=in.next();
			System.out.println("输入角色：");
			String role=in.next();
			DataProcessing.update(name,password,role);
			break;
		}
		case 2:{
			System.out.println("输入要新增的用户名：");
			String name=in.next();
			System.out.println("输入密码：");
			String password=in.next();
			System.out.println("输入角色：");
			String role=in.next();
			DataProcessing.insert(name,password,role);
			break;
		}
		case 3:{
			System.out.println("输入要的用户名：");
			String name=in.next();
			DataProcessing.delete(name);
			break;
		}
		case 4:{Enumeration<User> user = DataProcessing.getAllUser();
	      User e = null;
		while (user.hasMoreElements())
	    	   {e=user.nextElement();
	          System.out.println("用户名:"+e.getName()+"	密码:"+e.getPassword()+"	角色:"+e.getRole()); }
			break;
			}
		case 5:{System.out.println("请输入要下载的文件名：");
				String a=in.next();
					boolean result =downloadFile(a);
					if(result==true)
						System.out.println("下载成功");
					else
						System.out.println("下载失败");
		break;
		}
		case 6:showFileList();break;
		case 7:{System.out.println("请输入新密码：");
				String pwd=in.next();
				if(changeSelfInfo(pwd)==false);
					System.out.println("更改密码失败");
				break;
		}
		case 8:{flag=false;break;}
		
	 }
	 }
	 }
	 
	Administrator(String name, String password, String role) {
		super(name, password, role);
		// TODO 自动生成的构造函数存根
	}



}
