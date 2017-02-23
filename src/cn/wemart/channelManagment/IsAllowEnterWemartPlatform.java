package cn.wemart.channelManagment;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecuteGet;
import cn.wemart.userManagment.ShopLogin;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;


@Listeners({ com.TestNG.AssertionListener.class })
public class IsAllowEnterWemartPlatform {
	public  String response;
	@Test
	public  String test(String mobile,String password){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.Login(mobile, password);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		String url = LoadAPIInfo.url+"/api/usermng/platform";
		String adminNo = JSONObject.fromObject(shopLogin.response).getJSONObject("data").getString("adminNo");
		
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("adminNo",adminNo);
		Reporter.log(getCurrent.Time());
		response = ExecuteGet.getGetMethodResponse(httpClient, url, keyValueList);
		return response;
	}

}
