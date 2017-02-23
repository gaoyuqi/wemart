package cn.wemart.userManagment;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import cn.wemart.httppost.ExecutePut;
import cn.wemart.util.LoadAPIInfo;
import cn.wemart.util.getCurrent;

@Listeners({com.TestNG.AssertionListener.class})
public class ChangePassword {

	public static String response;
	@Test
	public static String test(String mobile,String password, String type, String sllerId){
		ShopLogin shopLogin = new ShopLogin();
		shopLogin.DirectEnterShop(mobile, password, type, sllerId);
		CloseableHttpClient httpClient = shopLogin.httpClient;
		String url = LoadAPIInfo.url+"/api/usermng/admin/password";
		Map<String,Object> keyValueList = new HashMap<String,Object>();
		keyValueList.put("oldPassword",password);
		keyValueList.put("password",password);
		keyValueList.put("confirmPassword",password);
		Reporter.log(getCurrent.Time());
		response = ExecutePut.getPutMethodResponse(httpClient, url, keyValueList);
		String returnValue = JSONObject.fromObject(response).getString("returnValue");
		return returnValue;
	}
}
