package cn.wemart.mobile;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.usermng.shopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class getNavigator {
	
	/**
	 * 对应608号接口（查询全店导航）
	 */
	@Test
	public static void getHomePageTest() {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = LoadAPIInfo.url + "/api/shopping/navigator";

		Object[][] keyVlaueList = new Object[][] {
				{ "sellerId", "23" },
				};

		System.out.println(getCurrent.Time());
		String responseEntiy = ExecuteGet.getGetMethodResponse(httpclient,url,keyVlaueList);
		System.out.println("response=" + responseEntiy);
		System.out.println(getCurrent.Time());
		
		JSONObject responseObject = JSONObject.fromObject(responseEntiy);
		Integer returnValue = Integer.parseInt(responseObject.getString("returnValue"));
		if(0 == returnValue){
			System.out.println("查询导航成功! \n");
		}
		else{
			System.out.println("查询导航失败!"+responseEntiy+"\n");
		}

	}

}
