import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;

public abstract class User {
    protected String name;
    private String password;
    private String role;

    String uploadpath="D:\\OOP\\uploadfile\\";
    String downloadpath="D:\\OOP\\downloadfile\\";

    User(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }

    public boolean changeSelfInfo(String password)throws SQLException {
        //写用户信息到存储
        if (DataProcessing.updateUser(name, password, role)){
            this.password=password;
            System.out.println("修改成功");
            return true;
        }else
            return false;
    }

    public abstract void showMenu() throws IOException, SQLException;

    public boolean downloadFile(String ID)throws SQLException,IOException {
        //boolean result =false;
        byte[] buffer =new byte[1024];
        Doc doc=DataProcessing.searchDoc(ID);
        if(doc==null) return false;
        File tempFile=new File(uploadpath+doc.getFilename());
        String filename=tempFile.getName();
        BufferedInputStream infile =new BufferedInputStream(new FileInputStream(tempFile));
        BufferedOutputStream targetfile =new BufferedOutputStream((new FileOutputStream(downloadpath+filename)));

        while (true){
            int byteRead =infile.read(buffer);//从文件读数据给字节数组
            if(byteRead==-1) break; //文件尾无数据可读，退出循环
            targetfile.write(buffer,0,byteRead);
        }
        infile.close();
        targetfile.close();

        return true;
        //System.out.println("copy success!");
    }



    public void showFileList()throws SQLException {
        Enumeration<Doc> e=DataProcessing.getAllDocs();
        Doc doc;
        while(e.hasMoreElements()){
            doc=e.nextElement();
            System.out.println("ID:"+doc.getID()+"\t Creator"+doc.getCreator());
            System.out.println("Description:"+doc.getDescription());
        }

    }

    public void exitSystem(){
        System.out.println("系统退出, 谢谢使用 ! ");
        System.exit(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
