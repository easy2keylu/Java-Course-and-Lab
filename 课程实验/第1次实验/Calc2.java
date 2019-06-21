import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc2 {
    private static List<String> sysboList=new ArrayList<String>(){{
        this.add("+");
        this.add("-");
        this.add("*");
        this.add("/");
        this.add("=");
    }};
    private static Pattern p1 = Pattern.compile("(^[0-9]{1,9}[.][0-9]{1,2}$)|(^[0-9]{1,11}$)");
    
    public static void main(String[] args) {
        String numstr="(2+2*(1+2))*3/2";
        System.out.println(result(numstr));
    }
    
    public static RoundingMode result(String numstr){
        //�Ȱ������е�ֵ�������Ȼ���滻���ҵ���������С��Χ��
        //���һ��'('���ֵ�λ�� �� ��һ��')'���ֵ�λ��
        StringBuffer sb=new StringBuffer(numstr);
        if(isStandard(sb.toString())){
            String rs=bracketsString(sb);
            while(rs!=null){
                RoundingMode calBracketsValue = calBracketsValue(rs+"=");
                sb.replace(sb.indexOf("@"), sb.indexOf("@")+1, calBracketsValue.toString());
                System.out.println(sb);
                rs=bracketsString(sb);
            }
            return calBracketsValue(sb.toString()+"=");
        }
        return new RoundingMode("0");
    }
    
    public static String bracketsString(StringBuffer str){
        int leftBracketsPoint=str.lastIndexOf("(");
        int rightBracketsPoint=str.indexOf(")");
        if(leftBracketsPoint==-1 || rightBracketsPoint==-1){
            return null;
        }
        String res= str.substring(leftBracketsPoint+1,rightBracketsPoint);
        str.replace(leftBracketsPoint, rightBracketsPoint+1, "@");
        return res;
    }
    
    //���������еı��ʽ��ֵ
    public static RoundingMode calBracketsValue(String numStr){
        Stack<RoundingMode> numStack=new Stack<RoundingMode>();//����ջ
        Stack<String> sysmboStack=new Stack<String>();//����ջ
        int lastSysboPoint=0;//��һ�η����ֳ��ֵ�λ��
        for(int i=0;i<numStr.length();i++){
            String ch=numStr.charAt(i)+"";
            if(sysboList.contains(ch)){
                //��ʼ������ջ
                //�Ȱ�ǰ���������ջ 1+1*1=
                    String num=numStr.substring(lastSysboPoint,i);
                    numStack.push(new RoundingMode(num));
                
                while(!sysmboStack.isEmpty() && !comparePri(ch.charAt(0),sysmboStack.peek().charAt(0))){//�������ջ��Ϊ�գ��Ͱ�ǰһ�������ó��������ڵķ��������ȼ��Ա�
                        RoundingMode num2=numStack.pop();
                        RoundingMode num1=numStack.pop();
                        String sysboTemp=sysmboStack.pop();
                        if(sysboTemp.equals("+")){
                            numStack.push(num1.add(num2));
                        }else if(sysboTemp.equals("-")){
                            numStack.push(num1.subtract(num2));
                        }else if(sysboTemp.equals("*")){
                            numStack.push(num1.multiply(num2));
                        }else if(sysboTemp.equals("/")){
                            numStack.push(num1.divide(num2,10,RoundingMode.DOWN));
                        }
                }
                sysmboStack.push(ch);
                lastSysboPoint=i+1;
            }
        }
        
        return numStack.pop();
    }
    
    
    
     private static boolean comparePri(char symbol,char top) {
            if (top == '(') {
                return true;
            }
            // �Ƚ����ȼ�
            switch (symbol) { 
            case '(': // ���ȼ����
                return true;
            case '*': {
                if (top == '+' || top == '-') // ���ȼ���+��-��
                    return true;
                else
                    return false;
            }
            case '/': {
                if (top == '+' || top == '-') // ���ȼ���+��-��
                    return true;
                else
                    return false;
            }
            case '+':
                return false;
            case '-':
                return false;
            case ')': // ���ȼ����
                return false;
            case '=': // ������
                return false;
            default:
                break;
            }
            return true;
        }
    
     private static boolean isNumber(String num){
            Matcher m1 = p1.matcher("123");   
            return m1.matches();   
     }
     private static boolean isStandard(String numStr) {
            if (numStr == null || numStr.isEmpty()) // ���ʽ����Ϊ��
                return false;
            Stack<Character> stack = new Stack<Character>(); // �����������ţ�������������Ƿ�ƥ��
            boolean b = false; // �������'='�����Ƿ���ڶ��
            for (int i = 0; i < numStr.length(); i++) {
                char n = numStr.charAt(i);
                // �ж��ַ��Ƿ�Ϸ�
                if (!(isNumber(n+"") || "(".equals(n + "") || ")".equals(n + "")
                        || "+".equals(n + "") || "-".equals(n + "")
                        || "*".equals(n + "") || "/".equals(n + "")
                        || "=".equals(n + ""))) {
                    return false;
                }
                // ��������ѹջ������������������Ž���ƥ��
                if ("(".equals(n + "")) {
                    stack.push(n);
                }
                if (")".equals(n + "")) { // ƥ������
                    if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) // �����Ƿ�ƥ��
                        return false;
                }
                // ����Ƿ��ж��'='��
                if ("=".equals(n + "")) {
                    if (b)
                        return false;
                    b = true;
                }
            }
            // ���ܻ���ȱ�������ŵ����
            if (!stack.isEmpty())
                return false;
            return true;
        }
    
}