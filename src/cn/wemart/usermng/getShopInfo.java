package cn.wemart.usermng;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.AssertJUnit;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadUserInfo;

public class getShopInfo {
	
	private static String url;
	private static String[][] keyVlaueList; 
	private static Log log = LogFactory.getLog(getShopInfo.class);
	
	/**
	 * 获取用户信息
	 * @param args
	 */
	public static void main(String[] args) {
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		
		String[] adminAcct = LoadUserInfo.adminAcct.split(",");
		for (String x :adminAcct) {
			keyVlaueList = new String[][] { { "adminAcct", x }};
			url = LoadAPIInfo.url+LoadAPIInfo.checkUserRegisterAPI;
			String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url,keyVlaueList);
			JSONObject object = JSONObject.fromObject(responseEntiy);
			Integer returnValue = Integer.parseInt(object.getString("returnValue"));
			if (returnValue != 0){
				System.out.println(x+" :获取信息失败！ response="+responseEntiy);
			}
			else{
				System.out.println(x+" :获取信息成功！response="+responseEntiy);
			}
		}
	}
}
