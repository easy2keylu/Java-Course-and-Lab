package lesson0;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Operator extends User{

    Operator(String name, String password, String role) {
        super(name, password, role);
    }
    public void upLoadFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("上传文件\n"+"请输入源文件名\n");
        String name=scanner.next();
        System.out.print("请输入档案号：\n");
        String number=scanner.next();
        System.out.print("请输入档案描述：\n");
        String descrip=scanner.next();
        System.out.print("上传文件... ...\n"+"上传成功！\n");
    }
        public void showMenu() throws IOException, SQLException {
            String infos = "****欢迎进入档案录入员菜单***\n" +
                    "\t1.上传文件\n\t" + "2.下载文件\n\t" + "3.文件列表\n\t" + "4.修改密码\n\t" + "5.退  出\n" + "*************\n" + "请选择菜单:";
            while(true){
                System.out.print(infos);
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        upLoadFile();
                        break;
                    case 2:
                        System.out.print("下载文件\n"+"请输入档案号:");
                        String filename=scanner.next();
                        downloadFile(filename);
                        System.out.print("下载成功！\n");break;
                    case 3:
                        showFileList();break;
                    case 4:
                        System.out.print("修改本人密码\n"+"请输入新口令:");
                        String password=scanner.next();
                        changeSelfInfo(password);break;
                    case 5:
                        exitSystem();break;


                }
            }

    }
}

