package cn.wemart.usermng;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getSellerApplyList {
	
	/**
	 * 对应20304号接口（获取开店审核列表）
	 */
	@Test
	public static void getSellerApplyListTest(){
		
		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url+LoadAPIInfo.getSellerApplyListAPI;
			
		Object[][] keyValueList = new Object[][]{
//				{ "page", "0"},
//				{ "status", "2"}
				};
			
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
			System.out.println("获取开店审核列表成功！\n");
		}
		else{
			System.out.println("获取开店审核列表失败！"+responseEntiy+"\n");
		}
			
	}

}
