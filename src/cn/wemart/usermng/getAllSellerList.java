package cn.wemart.usermng;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.LoadGoodsmngGroupInfo;
import cn.wemart.util.getCurrent;

public class getAllSellerList {
	
	/**
	 * 对应20302号接口（获取全部店铺列表）
	 */
	@Test
	public static void getAllShopListTest(){
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.getAllSellerAPI;
			
		Object[][] keyValueList = new Object[][]{};
			
		System.out.println(getCurrent.Time()); 
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url, keyValueList);
		System.out.println("response="+responseEntiy.trim());
		System.out.println(getCurrent.Time()); 
		
		JSONObject responsejson = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responsejson.getString("returnValue"));
		if (0 == returnValue) {
			JSONArray data = JSONArray.fromObject(responsejson.getString("data"));
			for(Object o:data){
				System.out.println(o.toString());
			}
			System.out.println("获取全部店铺列表成功！\n");
		}
		else{
			System.out.println("获取全部店铺列表失败！"+responseEntiy+"\n");
		}
			
	}

}
