import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Scanner;



public class Operator extends User{
	String name;
	 String password;
	 String role;
	 public static Scanner in =new Scanner(System.in);
	 
	 public boolean uploadFile(String filename) throws SQLException, IOException{
			System.out.println("上传文件... ...");
			System.out.println("请输入源文件名及其地址：");
			String fileadress=in.next();
			System.out.println("请输入文件编号：");
			String ID=in.next();
			System.out.println("请输入档案描述：");
			String description=in.next();
			File input=new File(fileadress);
			if(input==null)
				return false;
			byte[] copy=new byte[1024];
			Timestamp d = new Timestamp(System.currentTimeMillis()); 
			DataProcessing.insertDoc(ID,this.getName(),d,description,filename);
			Doc doc=DataProcessing.searchDoc(ID);
		//	File input=new File(uploadadress+doc.getFilename());
			String name=doc.getFilename();
		//	Object BufferedInputStream;
			BufferedInputStream iin=new BufferedInputStream(new FileInputStream(input));
			BufferedOutputStream oout= new BufferedOutputStream(new FileOutputStream(uploadpath+doc.getFilename()));
			while(true) {
				int readresult=iin.read(copy);
			if(readresult==-1)
				break;
			oout.write(copy,0,readresult);}
			iin.close();
			oout.close();
			return true;
		}
	 
	 public void showMenu() {
		 boolean flag=true;
	 
		while(flag) {
		System.out.println("欢迎进入档案录入员菜单");
		System.out.println("————————————————————");
		System.out.println("	1.上传文件");
		System.out.println("	2.下载文件");
		System.out.println("	3.文件列表");
		System.out.println("	4.修改密码");
		System.out.println("	5.退        出");
		System.out.println("————————————————————");
	    System.out.println("请输入要选择的选项：");
		int choose=in.nextInt();
		switch(choose)
		{
		case 1:try {System.out.println("请输入要上传的文件名：");
			String a=in.next();
			boolean result;
			
				result = uploadFile(a);
			
			if(result==true)
				System.out.println("上传成功");
			break;
			} catch (IOException | SQLException e) {
					System.out.print("上传失败，失败原因：");
				e.printStackTrace();
			}
		case 2:try{
			System.out.println("请输入要下载的文件名：");
			String a=in.next();
			boolean result;
				result = downloadFile(a);
			if(result==true)
				System.out.println("下载成功");
			break;
			} catch (IOException | SQLException e) {
				System.out.print("下载失败，失败原因：");
				e.printStackTrace();
			}
		case 3:try {
			showFileList();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}break;
		case 4:try{System.out.println("请输入新密码：");
		String pwd=in.next();
		changeSelfInfo(pwd);
		
		break;
		}catch(SQLException e)
	{System.out.print("更改密码失败，失败原因：");
		e.printStackTrace();}
		case 5:{flag=false;break;}
		
	 }
	 }
	 }
	 
	Operator(String name, String password, String role) {
		super(name, password, role);
		// TODO 自动生成的构造函数存根
	}


		
	}
