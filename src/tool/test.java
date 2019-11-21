package tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String arge[]){
		String regex = "[0-9,]{1,}";
	    Pattern p=Pattern.compile(regex);
	    String s = "12d2,0303,";
	    Matcher m=p.matcher(s);
	    if(m.matches()){
	    	System.out.println("∆•≈‰");
	    	}
	}
}
