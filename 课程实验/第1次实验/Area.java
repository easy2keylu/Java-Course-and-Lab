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
		System.out.print("\n��״����\n1.������\n2.Բ��\n3.������\n\n������ѡ�1/2/3����");
		Scanner scan = new Scanner(System.in);
		int sw = scan.nextInt();
		switch(sw){
			case 1:
			    Rect r = new Rect();
				double a, b;
				System.out.print("\n�����볤���ε�������Ϣ\n��Ϊ��");
				a = scan.nextDouble();
				System.out.print("��Ϊ��");
				b = scan.nextDouble();
				r.set(a, b);
				System.out.println("\n�ó����ε����Ϊ��"+r.put());
				break;
			case 2:
			    Cir c = new Cir();
				double ri;
				System.out.print("\n������Բ�ε�������Ϣ\n�뾶Ϊ��");
				ri = scan.nextDouble();
				c.set(ri);
				System.out.println("\n��Բ�ε����Ϊ��"+c.put());
				break;
			case 3:
			    Tri t = new Tri();
				double l, h;
				System.out.print("\n�����������ε�������Ϣ\n��Ϊ��");
				h = scan.nextDouble();
				System.out.print("�ױ�Ϊ��");
				l = scan.nextDouble();
				t.set(l, h);
				System.out.println("�������ε����Ϊ��"+t.put());
				break;
		}
	}
}