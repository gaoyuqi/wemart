package cn.wemart.channelManagment;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecutePost;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

public class CreateChannel {
	public String response;
	@Test
	public String test(String mobile,String password,Map<String,Object> keyValueList){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.Login(mobile, password);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		String url = LoadAPIInfo.url+"/api/usermng/channel";
		Reporter.log(getCurrent.Time());
		response = ExecutePost.getPostMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}
}
