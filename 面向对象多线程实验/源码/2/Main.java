package lesson0;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class Main {

    public static void main(String [] args) throws SQLException, IOException {
        String tip_system="档案系统";
        String tip_menu="请选择菜单";
        String tip_exit="系统退出";
        String infos="***欢迎进入"+tip_system+"***\n\t"+
                "1.登陆\n   \t2.退出\n"+
                "*****************************";
        String name,password;

        System.out.println(infos);
        System.out.print(tip_menu);
        //用户登录

        Scanner scanner=new Scanner(System.in);
        String input=null;
        while(true){
            input=scanner.next().trim();
            if(!(input).matches ("1|2")) System.err.print(tip_menu);
            else{
                int nextInt=Integer.parseInt(input);
                switch(nextInt){
                    case 1://登录
                        System.out.print("输入用户名:");
                        name=scanner.next();
                        System.out.print("输入口令:");
                        password=scanner.next();
                        try {
                            User user = DataProcessing.search(name, password);

                            if (user == null) System.out.println("用户名口令错误");
                            else user.showMenu();
                        }catch(SQLException e){
                            System.out.println(e.getMessage());
                            System.out.println(infos);
                            System.out.print(tip_menu);
                            continue;
                        }

                    case 2:
                        exitSystem();
                        break;

                }
            }


        break;}
    }

    private static void exitSystem() {
        System.out.println("系统退出, 谢谢使用 ! ");
        System.exit(0);
    }
}

