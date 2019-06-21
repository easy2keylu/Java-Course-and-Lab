package lesson5;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Vector;
public class Server extends ServerSocket {
    private static final int SERVER_PORT =12345;
 
    public Server()throws IOException {
        super(SERVER_PORT);
        int count=1;
        try {
            while (true) {
                Socket socket = accept();
                System.out.println("Connection "+ " received from: "+socket.getInetAddress().getHostName() );     
                new ServerThread(socket,"Thread"+count);
                count++;
            }
        }catch (IOException e) {
        }finally {
            close();
        }
    }
 
    class ServerThread extends Thread {
        private Socket client;
        private String client_name;
        private ObjectOutputStream output;
        private ObjectInputStream input; 
 
        public ServerThread(Socket s,String client_name)throws IOException {
            client = s;
            output=new ObjectOutputStream(client.getOutputStream());
            input=new ObjectInputStream(client.getInputStream());
            System.out.println("Got I/O streams" );
            System.out.println(client_name +" come in...");
            this.client_name=client_name;
            start();
        }
        public void run() {
        	String message="Connection successful!";
        	System.out.println(message);
        	try {
        	do {
        		Message msg =(Message) input.readObject();
                message=msg.get_message(); 
                if(message.equals("CLIENT>>> CLIENT_LOGIN")) {
         			  String name=msg.get_content().elementAt(0);
            		   String password=msg.get_content().elementAt(1);
         			   if(DataProcessing.search(name, password)!=null) {
        				    System.out.println(client_name+":");
        				    System.out.println(message);
        				    System.out.println(name);
         				    String role=DataProcessing.search(name, password).getRole();
         				    Vector<String> content=new Vector<String>();
         				    content.addElement(role);
         				    output.writeObject(new Message("LOGIN_TRUE",content));
         				    output.flush();
         				    System.out.println("SERVER>>> SERVER_LOGIN");
         			   }
         			   else {
         				   output.writeObject(new Message("LOGIN_FALSE",null)); 
         				   output.flush();
         			   }
         		}
         		else if(message.equals("CLIENT>>> CLIENT_SELF_MOD")) {
         			String name=msg.get_content().elementAt(0);
         			String password=msg.get_content().elementAt(1);
         			String role=msg.get_content().elementAt(2);
         			System.out.println(client_name+":");
         			System.out.println("CLIENT_SELF_MOD");
         			if(DataProcessing.update(name, password, role)==true) {
         				Vector<String> content=new Vector<String>();
      				    content.addElement(password);
      				    output.writeObject(new Message("SELFCHANGE_TRUE",content));
      				    output.flush();
         				System.out.println("SERVER>>> SERVER_SELF_MOD");
         			}
         			else {
         				output.writeObject(new Message("SELFCHANGE_FALSE",null)); 
      				    output.flush();
         			}
         		}
         		else if(message.equals("displayUser")) {
         			Enumeration<User> e=DataProcessing.getAllUser();
         			User user;
         			Vector<String> content=new Vector<String>();
         			while(e.hasMoreElements()) {
         				user=e.nextElement();
         				content.addElement(user.getName());
         				content.addElement(user.getPassword());
         				content.addElement(user.getRole());
         			}
         			output.writeObject(new Message("displayedUser",content));
         			output.flush();
         		}
         		else if(message.equals("displayDoc")) {
         			Enumeration<Doc> e=DataProcessing.getAllDocs();
         			Doc doc;
         			Vector<String> content=new Vector<String>();
         			while(e.hasMoreElements()) {
         				doc=e.nextElement();
         				content.addElement(doc.getID());
         				content.addElement(doc.getCreator());
         				content.addElement(doc.getTimestamp().toString());
         				content.addElement(doc.getDescription());
         				content.addElement(doc.getFilename());
         			}
         			output.writeObject(new Message("displayedDoc",content));
         			output.flush();
         		}
         		else if(message.equals("USER_DELETE")) {
         			String name=msg.get_content().elementAt(0);
         			if(DataProcessing.delete(name)) {
         				output.writeObject(new Message("DELETE_TRUE",null));
         				output.flush();
         				System.out.println(client_name+":");
         				System.out.println("SERVER>>> "+name+" USER_DELETE");
         			}
         			else {
         				output.writeUTF("DELETE_FALSE");
         				output.flush();
         			}
         		}
         		else if(message.equals("USER_ADD")) {
         			String name=msg.get_content().elementAt(0);
         			String password=msg.get_content().elementAt(1);
         			String role=msg.get_content().elementAt(2);
         			if(DataProcessing.insert(name, password, role)) {
         				output.writeObject(new Message("ADD_TRUE",null));
         				output.flush();
         				System.out.println(client_name+":");
         				System.out.println("SERVER>>> "+name+" USER_ADD");
         			}
         			else {
         				output.writeObject(new Message("ADD_FALSE",null));
         				output.flush();
         			}
         		}
         		else if(message.equals("USER_UPDATE")) {
         			String name=msg.get_content().elementAt(0);
         			String password=msg.get_content().elementAt(1);
         			String role=msg.get_content().elementAt(2);
         			if(DataProcessing.update(name, password, role)) {
         				output.writeObject(new Message("UPDATE_TRUE",null));
         				output.flush();
         				System.out.println(client_name+":");
         				System.out.println("SERVER>>> "+name+" USER_UPDATE");
         			}
         			else {
         				output.writeObject(new Message("UPDATE_FALSE",null));
         				output.flush();
         			}
         		}
         		else if(message.equals("UPLOAD")) {
         			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
         			String ID=msg.get_content().elementAt(0);
         			String Creator=msg.get_content().elementAt(1);
         			String description=msg.get_content().elementAt(2);
         			String filename=msg.get_content().elementAt(3);
         			Long fileLength=Long.parseLong(msg.get_content().elementAt(4));
         			FileOutputStream fos=new FileOutputStream(new File("D:\\OOP\\uploadfile\\"+filename));
         			byte[] sendBytes=new byte[1024];
         			int transLen=0;
         			System.out.println("----开始接收文件<"+filename+">,文件大小为<"+fileLength+">----");
         			while(true) {
         				int read=0;
         				read=input.read(sendBytes,0,sendBytes.length);
         				if(read<=0) break;
         				transLen+=read;
         				System.out.println("接收文件进度"+100*transLen*1.0/fileLength+"%...");
         				fos.write(sendBytes,0,read);
         				fos.flush();
         				if(transLen>=fileLength) break;
         			}
         			fos.close();
         			System.out.println("----接收文件<"+filename+">成功----");
         			if(DataProcessing.insertDoc(ID, Creator, timestamp, description, filename)){
         				output.writeObject(new Message("UPLOAD_TRUE",null));
         				output.flush();
         				System.out.println(client_name+":");
         				System.out.println("SERVER>>> CLIENT_FILE_UP");
         			}
         			else {
         				output.writeObject(new Message("UPLOAD_FALSE",null));
         				output.flush();
         			}
         		}
         		else if(message.equals("DOWNLOAD")) {
         			String ID=msg.get_content().elementAt(0);
         			System.out.println(client_name+":");
         			System.out.println("SERVER>>> CLIENT_FILE_DOWN");
         			String filename=DataProcessing.searchDoc(ID).getFilename();
         			String filepath="D:\\OOP\\uploadfile\\";
         			File file=new File(filepath+filename);
         			long fileLength=file.length();
         			Vector<String> content=new Vector<String>();
         			content.addElement(filename);
         			content.addElement(String.valueOf(fileLength));
         			output.writeObject(new Message("SERVER>>> CLIENT_FILE_DOWN",content));
         			output.flush();
         			FileInputStream fis=new FileInputStream(file);
         		    byte[] sendBytes=new byte[1024];
         		    int length=0;
         		    while((length=fis.read(sendBytes,0,sendBytes.length))>0) {
         			    output.write(sendBytes,0,length);
         			    output.flush();
         		    }
         		    fis.close();
         		}
         		else ; 
               } while ( !message.equals( "CLIENT>>> CLIENT_LOGOUT" ) );
                System.out.println(client_name+" LOGOUT!");
                output.close();
                input.close();
                client.close();
        	 }catch (SQLException e) {
  			    e.printStackTrace();
  		   }catch(IOException e) {
  			   e.printStackTrace();
  		   } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
}
    public static void main(String[] args)throws IOException {
    	String driverName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/document";
		String user="root";
		String password="jc13986029678jc";
		try {
			DataProcessing.connectToDatabase(driverName, url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Waiting for connection");
    	new Server(); 
    }
}
