
import java.util.*;
import java.util.Scanner;


public class Browser extends User{
	 String name;
	 String password;
	 String role;
	 public static Scanner in =new Scanner(System.in);
	 
	 public void showMenu() {
		 boolean flag=true;
	 
		while(flag) {
		System.out.println("欢迎进入档案浏览员菜单");
		System.out.println("————————————————————");
		System.out.println("	1.下载文件");
		System.out.println("	2.文件列表");
		System.out.println("	3.修改密码");
		System.out.println("	4.退        出");
		System.out.println("————————————————————");
	    System.out.println("请输入要选择的选项：");
		int choose=in.nextInt();
		switch(choose)
		{
		case 1:{System.out.println("请输入要下载的文件名：");
			String a=in.next();
			boolean result =downloadFile(a);
			if(result==true)
				System.out.println("下载成功");
			else
				System.out.println("下载失败");
			break;
			}
		case 2:showFileList();break;
		case 3:{System.out.println("请输入新密码：");
			String pwd=in.next();
			if(changeSelfInfo(pwd)==false);
			System.out.println("更改密码失败");
			break;
			}
		case 4:{flag=false;break;}
		}
	 }
	 }
	 
	Browser(String name, String password, String role) {
		super(name, password, role);
		// TODO 自动生成的构造函数存根
	}


		}


		
	
		
		
	

