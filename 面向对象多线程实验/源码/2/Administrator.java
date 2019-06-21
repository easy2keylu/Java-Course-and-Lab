package lesson0;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.*;

public class Administrator extends User{

    Administrator(String name, String password, String role) {
        super(name, password, role);
    }
//修改用户信息
    public void changeUserInfo() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("修改用户\n"+"请输入用户名:");
        String name=scanner.next();
        System.out.print("请输入口令:");
        String password=scanner.next();
        System.out.print("请输入角色:");
        String role=scanner.next();
        DataProcessing.update(name,password,role);
        System.out.println("修改成功！");
 }
// 删除用户
    public void delUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("删除用户\n"+"请输入用户名：\n");
        String name=scanner.next();
        DataProcessing.delete(name);
        System.out.println("删除成功！");
    }
// 添加用户
    public void addUsers() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名:\n");
        String name=scanner.next();
        System.out.print("请输入口令:\n");
        String password=scanner.next();
        System.out.print("请输入角色:\n");
        String role=scanner.next();
        DataProcessing.insert(name,password,role);
        System.out.println("新增成功！\n");
    }
//    列出用户
    public void listUser() throws SQLException {
        Enumeration<User> user = DataProcessing.getAllUser();
            User e = null;
            while (user.hasMoreElements())
            {e=user.nextElement();
                System.out.println("用户名:"+e.getName()+"	密码:"+e.getPassword()+"	角色:"+e.getRole()); }

    }
    @Override
    public void showMenu() throws SQLException {
        String infos = "****欢迎进入系统管理员菜单***\n" +
                "\t1.修改用户\n\t" + "2.删除用户\n\t" + "3.新增用户\n\t" + "4.列出用户\n\t" + "5.下载文件\n\t" + "6.文件列表\n\t"+"7.修改（本人）密码\n\t"+"8.退  出\n"+"*************\n" + "请选择菜单:";
        while (true){
            System.out.print(infos);
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    changeUserInfo();break;
                case 2:
                    delUser();break;
                case 3:
                    addUsers();break;
                case 4:
                    listUser();break;
                case 8:
                    exitSystem();break;

            }
        }
    }

}
