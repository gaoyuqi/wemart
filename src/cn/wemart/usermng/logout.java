package cn.wemart.usermng;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePost;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadGoodsmngGroupInfo;
import cn.wemart.util.getCurrent;

public class logout {
	
	/**
	 * 对应20514号接口（退出登录）
	 */
	@Test
	public static void logoutTest(){
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.logoutAPI;
			
		Object[][] keyValueList = new Object[][]{};
			
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecutePost.getPostMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy);
		System.out.println(getCurrent.Time()); 
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			System.out.println("退出登录成功！\n");
		}
		else{
			System.out.println("退出登录失败！"+responseEntiy+"\n");
		}
			
	}

}
