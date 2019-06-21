//Student.java
import java.util.Scanner;
public class Student
{
  String name;
  int age;
  String emailAdd;
  public static int emailType(String a)
  {
    String str1=new String("@qq.com");
    String str2=new String("@163.com");
    String str3=new String("@126.com");
    String str4=new String("@yeah.net");
    int idx=a.indexOf('@');
    a=a.substring(idx);
    if(a.compareTo(str1)==0)
    {
      return 1;
    }
    if(a.compareTo(str2)==0||a.compareTo(str3)==0||a.compareTo(str4)==0)
    {
      return 2;
    }
    else
    {
      return 0;
    }
  }
  public static void main(String[] args)
  {
    Student s=new Student();
    Scanner scan=new Scanner(System.in);
    System.out.println("Please enter student's name:");
    s.name=scan.nextLine();
    System.out.println("Please enter student's age:");
    s.age=scan.nextInt();
    System.out.println("Please enter student's email address:");
    s.emailAdd=scan.next();
    switch(s.emailType(s.emailAdd))
    {
      case 1:
        System.out.println("This student use QQ mail.");
        break;
      case 2:
        System.out.println("This student use NetEase mail.");
        break;
      default:
        System.out.println("Error.");
        break;
    }
  }
}      