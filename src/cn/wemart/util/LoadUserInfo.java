package cn.wemart.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

public class LoadUserInfo {
	
	public static String path = "./UserInfo.properties";
	public static String adminAcct;
	public static String password;
	public static String type;
	public static String objetcId;
	public static String token;
	public static String vertifycode; 
	public static String applyNo;
	public static String status; 
	
	private static Log log = LogFactory.getLog(LoadUserInfo.class);
	
	
	/**
	 * 加载测试数据
	 */
    static {
        Properties pro = readPropertiesFileObj(path); // 读取properties文件
        adminAcct = pro.getProperty("adminAcct");
        token = pro.getProperty("token");
        type = pro.getProperty("type");
        objetcId = pro.getProperty("objetcId");
        password = pro.getProperty("password");
        vertifycode = pro.getProperty("vertifycode");
        applyNo = pro.getProperty("applyNo");
        status = pro.getProperty("status");

    }


    public static Properties readPropertiesFileObj(String filename) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filename);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));//utf-8解决中文乱码问题
            properties.load(bf);
            inputStream.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    
//    @Test
//    public static void test() {
//      String[] list = adminAcct.split(",");
//      for(String x : list ){
//    	  System.out.println(x);
//      }
//	}

}
