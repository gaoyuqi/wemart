package cn.wemart.userManagment;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;


@Listeners({com.TestNG.AssertionListener.class})
public class GetMenuList {
	public static String response;

	@Test
	public static String test(String mobile,String password, String type, String sllerId) {
		
		ShopLogin.DirectEnterShop(mobile, password, type, sllerId);
		CloseableHttpClient httpClient = ShopLogin.httpClient;
		String url = LoadAPIInfo.url+"/api/usermng/admin/authdata";
		Object[][] keyValueList = new Object[][]{};
		Reporter.log(getCurrent.Time());
		response = ExecuteGet.getGetMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}

}
