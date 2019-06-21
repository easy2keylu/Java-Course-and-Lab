//TestCalc.java
//此程序存在问题，个人认为应该将数字与运算符分开存放，
//而不是全部转化为字符串数组，否则后续int转char存在问题。
//即使将int转化为对应的ASCII码也不行，因为就算输入的数字不会超过ASCII最大值，
//程序也会因为担心数字过大而不能转换成char。
import java.util.*;
class Calculator {
	int num1, num2;
    static int add(int a, int b) {
		return a+b;
	}
	static int minus(int a, int b) {
		return a-b;
	}
	static int multiple(int a, int b) {
		return a*b;
	}
	static int divide(int a, int b) {
		return a/b;
	}
}
public class TestCalc{
	public static void main(String[] args) {
		Calculator n = new Calculator();
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		char[] c = s.toCharArray();
		int i, j = 0, k;
		//计算括号和乘除计算的数量（需要暂存多少个数字）
		for(i = 0; i < c.length; i++) {
			if(c[i] == '(' || c[i] == '*' || c[i] == '/') {
				j++;
			}
		}
		//System.out.println(j);
		int[] a = new int[j];
		j = 0;
		//System.out.println(c);
		//记录括号内容位置
		for(i = 0; i < c.length; i++) {
			if(c[i] == '(') {
				a[j] = i;
			    j++;
			}
		}
		//由内而外计算括号内数学式
		for(i = j-1; i >= 0; i--) {
			while(c[i] != ')') {
				if(c[i] == '*') {
			        n.num1 = Calculator.multiple(c[i-1]-'0', c[i+1]-'0');
		            c[i-1] = c[i] = c[i+1] = n.num1;
				}
		        if(c[i] == '/') {
			        n.num1 = Calculator.divide(c[i-1]-'0', c[i+1]-'0');
		        }
				i++;
			}
		}
		/*for(i = 0; i < c.length; i++) {
			//System.out.println(c[i]);
			if(c[i] == '*') {
			    Calculator.multiple(c[i-1]-'0', c[i+1]-'0');
		    }
		    if(c[i] == '/') {
			    Calculator.divide(c[i-1]-'0', c[i+1]-'0');
		    }
		}*/
		
		
		//num1.set(6);
		//System.out.println(num1.n);
		//System.out.println(c[i-1]);
		//System.out.println(c[i+1]);
	}
}
		