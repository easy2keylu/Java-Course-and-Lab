package lesson3;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Browser extends User{
	Browser(String name, String password, String role) {
		super(name, password, role);
	}
	public void showMenu(String name) {
    	System.out.println("欢迎进入档案浏览员菜单！");
    	System.out.println("1.下载文件");
    	System.out.println("2.文件列表");
    	System.out.println("3.修改密码");
    	System.out.println("4.退出");
    	System.out.print("请选择：");
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	int i=sc.nextInt();
    	switch(i) {
    	    case 1:{
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
    	    case 2:{
    	    	try {
					showFileList();
				} 
    	    	catch (SQLException e) {
    	    		System.out.println(e.getMessage());
				}
    	    	break;
    	    }
    	    case 3:{
    	    	System.out.println("请输入新密码：");
    	    	String psd=sc.nextLine();
    	    	try {
					changeSelfInfo(psd);
				} 
    	    	catch (SQLException e) {
    	    		System.out.println(e.getMessage());
				}
    	    	break;
    	    }
    	    case 4:{
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
