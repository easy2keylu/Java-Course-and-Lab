package lesson4;

import java.sql.SQLException;

public class WinMain {
	public static void main(String args[]) {
		String driverName="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/document?useSSL=false";
		String user="root";
		String password="123456";
		try {
			DataProcessing.connectToDatabase(driverName, url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LoginWindow window=new LoginWindow();
		window.loginFrame();
	}
}
