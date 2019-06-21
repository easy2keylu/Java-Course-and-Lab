//Area.java
import java.util.*;
import java.lang.Math;
class Rect{
	private double a;
    private double b;
	public void set(double a, double b){
		this.a = a;
		this.b = b;
	}
	public double put(){
		return a*b;
	}
}
class Cir{
	private double r;
	public void set(double r){
		this.r = r;
	}
	public double put(){
		return (Math.PI)*r*r;
	}
}
class Tri{
	private double l;
	private double h;
	public void set(double l, double h){
		this.l = l;
		this.h = h;
	}
	public double put(){
		return 0.5*l*h;
	}
}
public class Area{
	public static void main(String[] args){
		System.out.print("\n形状类型\n1.长方形\n2.圆形\n3.三角形\n\n请输入选项（1/2/3）：");
		Scanner scan = new Scanner(System.in);
		int sw = scan.nextInt();
		switch(sw){
			case 1:
			    Rect r = new Rect();
				double a, b;
				System.out.print("\n请输入长方形的详情信息\n长为：");
				a = scan.nextDouble();
				System.out.print("宽为：");
				b = scan.nextDouble();
				r.set(a, b);
				System.out.println("\n该长方形的面积为："+r.put());
				break;
			case 2:
			    Cir c = new Cir();
				double ri;
				System.out.print("\n请输入圆形的详情信息\n半径为：");
				ri = scan.nextDouble();
				c.set(ri);
				System.out.println("\n该圆形的面积为："+c.put());
				break;
			case 3:
			    Tri t = new Tri();
				double l, h;
				System.out.print("\n请输入三角形的详情信息\n高为：");
				h = scan.nextDouble();
				System.out.print("底边为：");
				l = scan.nextDouble();
				t.set(l, h);
				System.out.println("该三角形的面积为："+t.put());
				break;
		}
	}
}