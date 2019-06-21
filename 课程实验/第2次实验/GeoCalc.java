//GeoCalc.java

import java.util.*;
import java.lang.*;

interface Geometricinterface {
	void perimeter();
	void area();
}

class Triangle implements Geometricinterface {
	double a, b, c;
	Triangle(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public void perimeter() {
		double p = a+b+c;
		System.out.println("�������ܳ�Ϊ "+p);
	}
	public void area() {
		double p = (a+b+c)/2;
		System.out.println("���������Ϊ "+Math.sqrt(p*(p-a)*(p-b)*(p-c)));
	}
}

class Rectangle implements Geometricinterface {
	double x, y;
	Rectangle(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void perimeter() {
		double p = x+y;
		System.out.println("�����ܳ�Ϊ "+p*2);
	}
	public void area() {
		System.out.println("�������Ϊ "+x*y);
	}
}

public class GeoCalc {
    public static void main(String[] args) {
	    System.out.println("������ͼ������\n1��������\n2������");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		switch(n){
			case 1:
				double a, b, c;
				System.out.print("���������������߳�\n��һ���ߣ�");
				a = scan.nextDouble();
				System.out.print("�ڶ����ߣ�");
				b = scan.nextDouble();
				System.out.print("�������ߣ�");
				c = scan.nextDouble();
				Triangle t = new Triangle(a, b, c);
				t.perimeter();
				t.area();
				break;
			case 2:
			    double x, y;
			 	System.out.print("��������εĳ����\n����");
			   	x = scan.nextDouble();
			   	System.out.print("��");
			   	y = scan.nextDouble();
			   	Rectangle r = new Rectangle(x, y);
			   	r.perimeter();
			   	r.area();
			   	break;
			default:
			    System.out.println("�������");
			   	break;
		    }	    
	}
}