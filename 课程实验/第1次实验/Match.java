//Match.java
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Match{
    public static void main(String[] args)
    {
        String str = "<html><body><a href=\"http://www.baidu.com\">�ٶ�</a>��<a href='www.webmeteor.cn/course/java' target='_blank'>����</a>��<a href='/news/13432.html' target=\"_blank\">�ڲ���ַ</a></body></html>";
        String reg = "href=\\'[a-z.0-9A-Z//]{6,60}|href=\"http:\\/+(w+\\..{3,5}\\.com)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
		while(m.find()) {
			System.out.println(m.group().substring(6));
		}
    }
}