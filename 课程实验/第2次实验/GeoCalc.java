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
		System.out.println("三角形周长为 "+p);
	}
	public void area() {
		double p = (a+b+c)/2;
		System.out.println("三角形面积为 "+Math.sqrt(p*(p-a)*(p-b)*(p-c)));
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
		System.out.println("矩形周长为 "+p*2);
	}
	public void area() {
		System.out.println("矩形面积为 "+x*y);
	}
}

public class GeoCalc {
    public static void main(String[] args) {
	    System.out.println("请输入图形种类\n1、三角形\n2、矩形");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		switch(n){
			case 1:
				double a, b, c;
				System.out.print("请输入三角形三边长\n第一条边：");
				a = scan.nextDouble();
				System.out.print("第二条边：");
				b = scan.nextDouble();
				System.out.print("第三条边：");
				c = scan.nextDouble();
				Triangle t = new Triangle(a, b, c);
				t.perimeter();
				t.area();
				break;
			case 2:
			    double x, y;
			 	System.out.print("请输入矩形的长与宽\n长：");
			   	x = scan.nextDouble();
			   	System.out.print("宽：");
			   	y = scan.nextDouble();
			   	Rectangle r = new Rectangle(x, y);
			   	r.perimeter();
			   	r.area();
			   	break;
			default:
			    System.out.println("输入错误！");
			   	break;
		    }	    
	}
}