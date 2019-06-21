package lesson3;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
    	while(true) {
        	System.out.println("欢迎进入档案管理系统！");
        	System.out.println("1.登录");
        	System.out.println("2.退出");
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
        	int i=sc.nextInt();
        	if(i==1) {
        		System.out.println("请输入用户名：");
        		String name=sc.next();
        		try {
					if(DataProcessing.searchUser(name)!=null) {
						System.out.println("请输入密码：");
						String password=sc.next();
						try {
						    if(DataProcessing.search(name ,password)!=null)
						        DataProcessing.search(name, password).showMenu(name);
						    else System.out.println("密码错误！");  
						}
						catch(SQLException e) {
							System.out.println(e.getMessage());
						}
					}
					else {
					   System.out.println("用户不存在！");   
					}
				} 
        		catch (SQLException e) {
        			System.out.println(e.getMessage());
				}
            }
        	else if(i==2) {
        		System.exit(0);
        	}
        	else {
        		System.out.println("输入非法！请重新输入。");
        	}
    	}
    }
}
