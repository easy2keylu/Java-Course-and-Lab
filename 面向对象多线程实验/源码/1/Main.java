

import java.util.Scanner;
public class Main {
public static Scanner in =new Scanner(System.in);
public static void main(String args[]){
	String tip_system="档案惯例系统";
	String tip_menu="请选择菜单";
	String tip_exit="系统退出，谢谢使用";
	String infos="***欢迎进入"+	tip_system+"***\n\t  "+"1.登录 \n\t"+"   2.退出系统\n"+"****************************\n"+"请输入菜单：";
	System.out.println(infos);
	 int  number1=in.nextInt();
	if(number1==1)
	{
		System.out.println("请输入用户名：");
		String name=in.next();
		if(DataProcessing.searchUser(name)!=null)
		{
			System.out.println("请输入口令：");
			String password=in.next();
			if(DataProcessing.search( name,password)!=null)
			{
				System.out.println("密码输入正确");
    			User u=DataProcessing.search(name,password);
    			u.showMenu();
    			}
			}
			else
			System.out.println("口令错误，请检查");
			}
		else
    		System.out.println("口令错误，请检查");
	if(number1==2)
		System.out.println("系统退出, 谢谢使用 ! ");
	     System.exit(0);
}
}
