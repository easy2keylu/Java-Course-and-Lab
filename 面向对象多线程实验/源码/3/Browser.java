
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Browser extends User {

    Browser(String name, String password, String role) {
        super(name, password, role);
    }
    public void showMenu() throws IOException, SQLException {
        String infos = "****欢迎进入档案浏览员菜单***\n" +
                "\t1.下载文件\n\t" + "2.文件列表\n\t" + "3.修改密码\n\t" + "4.退  出\n" + "*************\n" + "请选择菜单:";
        while (true) {
            System.out.print(infos);
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("下载文件\n"+"请输入档案号:");
                    String filename=scanner.next();
                    downloadFile(filename);
                    System.out.print("下载成功！\n");break;
                case 2:
                    System.out.println("文件列表");
                    showFileList();break;
                case 3:
                    System.out.print("修改本人密码\n"+"请输入新口令:");
                    String password=scanner.next();
                    changeSelfInfo(password);break;
                case 4:
                    exitSystem();break;

            }
        }
    }
}

