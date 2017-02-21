package cn.wemart.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 
 * @author sean
 *
 */
public class LoadDataBaseInfo {

	public static String path = "./jdbc.properties";
	public static String DBurl;
	public static String DBport;
	public static String DBuserName;
	public static String DBuserPassword;
	
	/**
	 * 加载测试数据
	 */
    static {
        Properties pro = readPropertiesFileObj(path); // 读取properties文件
        DBurl = pro.getProperty("address");
        DBport = pro.getProperty("port"); 
        DBuserName = pro.getProperty("username"); 
        DBuserPassword = pro.getProperty("password"); 
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
	
}
