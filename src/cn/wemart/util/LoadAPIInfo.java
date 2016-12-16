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
public class LoadAPIInfo {

	public static String path = "./URL.properties";
	public static String url;
	public static String userLoginAPI;
	
	
	
	
	/**
	 * 加载测试数据
	 */
    static {
        Properties pro = readPropertiesFileObj(path); // 读取properties文件
        url = pro.getProperty("url");
        userLoginAPI = pro.getProperty("userLoginAPI"); //店铺登录
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
