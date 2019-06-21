package lesson5;
import java.sql.SQLException;
import java.util.Enumeration;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public  class User {
     private String name;
     private String password;
     private String role;
     String uploadpath="D:\\OOP\\uploadfile\\";
     String downloadpath="D:\\OOP\\downloadfile\\";
     public User(String name,String password,String role){
    	 super();
    	 this.name=name;
    	 this.password=password;
    	 this.role=role;
     }
     public void showMenu(String name) {
    	 
     }
     public void showFileList() throws SQLException{
    	 /*
    	 double ranValue=Math.random();
    	 if(ranValue>0.5) throw new SQLException("Error in accessing file DB");
    	 */
    	 Enumeration<Doc> e=null;
    	 try {
    		 e=DataProcessing.getAllDocs();
    	 }
    	 catch(SQLException e1) {
    		 System.out.println(e1.getMessage());
    	 }
    	 Doc doc;
    	 while(e.hasMoreElements()) {
    		 doc=e.nextElement();
    		 System.out.println("ID:"+doc.getID()+"\tCreator:"+doc.getCreator()+"\tTime:"+
    		 doc.getTimestamp()+"\tFilename:"+doc.getFilename()+"\tDescription:"+doc.getDescription());
    	 }
     }
     public boolean downloadFile(String ID) throws IOException{
    	 /*
    	 double ranValue=Math.random();
    	 if(ranValue>0.5) throw new IOException("Error in accessing file");
    	 */
    	 byte[] buffer=new byte[1024];
    	 Doc doc=null;
		 try {
			 doc = DataProcessing.searchDoc(ID);
		 } 
		 catch (SQLException e) {
			 System.out.println(e.getMessage());
		 }
    	 if(doc==null) return false;
    	 File tempFile=new File(uploadpath+doc.getFilename());
    	 String filename=tempFile.getName();
    	 BufferedInputStream infile=new BufferedInputStream(new FileInputStream(tempFile));
    	 BufferedOutputStream targetfile=new BufferedOutputStream(new FileOutputStream(new File(downloadpath+filename)));
    	 while(true) {
    		 int byteRead=infile.read(buffer);
    		 if(byteRead==-1)
    			 break;
    		 targetfile.write(buffer,0,byteRead);
    	 }
    	 infile.close();
    	 targetfile.close();
    	 return true;
     }
     public boolean changeSelfInfo(String password) throws SQLException{
    	if(DataProcessing.update(name,password,role)) {
    		this.password=password;
    		System.out.println("修改成功！");
    		return true;
    	}
    	else {
    		System.out.println("修改失败！");
    		return false;
    	}
     }
     public void exitSystem() {
    	 System.out.println("系统退出，谢谢使用！");
    	 System.exit(0);
     }
     public void setName(String name) {
    	 this.name=name;
     }
     public String getName() {
    	 return name;
     }
     public void setPassword(String password) {
    	 this.password=password;
     }
     public String getPassword() {
    	 return password;
     }
     public void setRole(String role) {
    	 this.role=role;
     }
     public String getRole() {
    	 return role;
     }
}
