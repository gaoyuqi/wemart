package cn.wemart.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class getCurrent {
	public static String Time (){
		Date date = new Date();  
	    String s = null;  
		DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
	    s = sdf2.format(date);  
	    return s;
	}

}
