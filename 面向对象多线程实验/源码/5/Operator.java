package lesson4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Operator extends User{
	Operator(String name, String password, String role) {
		super(name, password, role);
	}
    public void showMenu(String name) {
    	System.out.println("欢迎进入档案操作员菜单！");
    	System.out.println("1.上传文件");
    	System.out.println("2.下载文件");
    	System.out.println("3.文件列表");
    	System.out.println("4.修改密码");
    	System.out.println("5.退出");
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	int i=sc.nextInt();
    	switch(i) {
    	    case 1:{
    	        try {
					uploadFile(name);
				} 
    	        catch (IOException e) {
					System.out.println(e.getMessage());
				}
    	        System.out.println("文件上传成功！");
    	        break;
        	}
    	    case 2:{
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
    	    case 3:{
    	    	try {
					showFileList();
				} 
    	    	catch (SQLException e) {
    	    		System.out.println(e.getMessage());
				}
    	    	break;
    	    }
    	    case 4:{
    	    	System.out.println("请输入新密码：");
    	    	String psd=sc.next();
    	    	try {
					changeSelfInfo(psd);
				} 
    	    	catch (SQLException e) {
    	    		System.out.println(e.getMessage());
				}
    	    	break;
    	    }
    	    case 5:{
    	    	exitSystem();
    	    }
    	    default:{
    	    	System.out.println("输入非法！请重新输入！");
    	    	break;
    	    }
    	}
    	showMenu(name);
    }
    public void uploadFile(String name) throws IOException{
    	System.out.println("请输入源文件名:");
    	@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
    	String filename=sc.next();
    	System.out.println("请输入档案号");
    	String ID=sc.next();
    	System.out.println("请输入档案描述");
    	String description=sc.next();
    	byte[] buffer=new byte[1024];
   	    Timestamp timestamp=new Timestamp(System.currentTimeMillis());
   	    File tempFile=new File(filename.trim());
        String fileName=tempFile.getName();
   	    try {
			if(DataProcessing.insertDoc(ID,name,timestamp,description,fileName)) ;
			else System.out.println("存入数据库失败！");
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
   	    BufferedInputStream infile = null;
	    infile = new BufferedInputStream(new FileInputStream(filename));
   	    BufferedOutputStream targetfile = null;
		targetfile = new BufferedOutputStream(new FileOutputStream(new File(uploadpath+fileName)));
   	    while(true) {
   		    int byteRead = 0;
		    byteRead = infile.read(buffer);
   		    if(byteRead==-1)
   		        break;
		    targetfile.write(buffer,0,byteRead);
   	    }
		infile.close();
		targetfile.close();
    }
}
