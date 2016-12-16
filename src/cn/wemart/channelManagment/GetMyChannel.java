package cn.wemart.channelManagment;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Test;
import cn.wemart.httppost.ExecuteGet;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class GetMyChannel {
	
	public  String response;
	@Test
	public  String test(String mobile,String password){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.Login(mobile, password);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		String url = LoadAPIInfo.url+"/api/usermng/channel/mychannel";
		Object[][] keyValueList = new Object[][]{};
		Reporter.log(getCurrent.Time());
		response = ExecuteGet.getGetMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}

}
