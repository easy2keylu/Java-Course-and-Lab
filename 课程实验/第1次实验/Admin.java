//Admin.java
import java.util.*;
public class Admin{
	String name = new String();
	int pwd;
	void show(){
		System.out.println("ĞÕÃû£º"+name+"\nÃÜÂë£º"+pwd);
	}
	public static void main(String[] args){
		Admin ad1 = new Admin();
		Admin ad2 = new Admin();
		ad1.name = "admin1";
		ad2.name = "admin2";
		ad1.pwd = 111111;
		ad2.pwd = 222222;
		ad1.show();
		ad2.show();
	}
}