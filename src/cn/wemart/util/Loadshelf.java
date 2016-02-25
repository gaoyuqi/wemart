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

/**
 * 
 * @author sean
 *
 */

public class Loadshelf {

	public static String path = "./shelfContent.properties";
	public static String textNavigate;
	public static String separator;
	public static String shopSign;
	public static String slide;
	public static String picNavigate4;
	public static String picNavigate8;
	public static String goodsGroup;
	public static String announcement;
	
	/**
	 * 加载测试数据
	 */
    static {
        Properties pro = readPropertiesFileObj(path); // 读取properties文件
        textNavigate = pro.getProperty("textNavigate");
        separator = pro.getProperty("separator");
        shopSign = pro.getProperty("shopSign");
        slide = pro.getProperty("slide");
        picNavigate4 = pro.getProperty("picNavigate4");
        picNavigate8 = pro.getProperty("picNavigate8");
        goodsGroup = pro.getProperty("goodsGroup");
        announcement = pro.getProperty("announcement");
  
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
