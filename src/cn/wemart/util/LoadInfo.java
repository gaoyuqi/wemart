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

public class LoadInfo {

	public static String path = "./API.properties";
	public static String catNo;
	public static String catName;
	public static String parentNo;
	public static String catForm;
	public static String valueMin;
	public static String valueMax;
	private static Log log = LogFactory.getLog(LoadInfo.class);
	
	/**
	 * 加载测试数据
	 */
    static {
        Properties pro = readPropertiesFileObj(path); // 读取properties文件
        catName = pro.getProperty("catName");
        catNo = pro.getProperty("catNo");
  
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
    public static void main(String[] args) {
      String[] list = catName.split(",");
      for(String x : list ){
    	  log.error(x);
      }
	}
}
