//TestCalc.java
//�˳���������⣬������ΪӦ�ý�������������ֿ���ţ�
//������ȫ��ת��Ϊ�ַ������飬�������intתchar�������⡣
//��ʹ��intת��Ϊ��Ӧ��ASCII��Ҳ���У���Ϊ������������ֲ��ᳬ��ASCII���ֵ��
//����Ҳ����Ϊ�������ֹ��������ת����char��
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
		//�������źͳ˳��������������Ҫ�ݴ���ٸ����֣�
		for(i = 0; i < c.length; i++) {
			if(c[i] == '(' || c[i] == '*' || c[i] == '/') {
				j++;
			}
		}
		//System.out.println(j);
		int[] a = new int[j];
		j = 0;
		//System.out.println(c);
		//��¼��������λ��
		for(i = 0; i < c.length; i++) {
			if(c[i] == '(') {
				a[j] = i;
			    j++;
			}
		}
		//���ڶ��������������ѧʽ
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
		