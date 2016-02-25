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

public class LoadGoodsmngGroupInfo {
	public static String path = "./API.properties";
	public static String groupName;
	public static String sellerId;
	private static Log log = LogFactory.getLog(LoadUserInfo.class);
	
	/**
	 * 加载测试数据
	 */
    static {
        Properties pro = readPropertiesFileObj(path); // 读取properties文件
        groupName = pro.getProperty("groupName");
        sellerId = pro.getProperty("sellerId");

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
    
    @Test
    public static void test() {
    	System.out.println(groupName+"++++++"+sellerId);
	}

}
