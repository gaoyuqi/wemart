package cn.wemart.decorationmng;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteDelete;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.httppost.ExecutePut;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class setHomePage {
	
	/**
	 * 对应606号接口（设置主页）
	 */
	@Test
	public static void setHomePageTest() {

		CloseableHttpClient httpclient = shopLogin.setHttpClient();
		String url = LoadAPIInfo.url + LoadAPIInfo.setHomePageAPI;

		Object[][] keyValueList = new Object[][] {
				{ "shelfNo", "203" }
				};

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecutePut.getPutMethodResponse(httpclient,url,keyValueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("设置主页成功! \n");
		}
		else{
			System.out.println("设置主页失败!"+responseEntiy+"\n");
		}

	}
	
}
